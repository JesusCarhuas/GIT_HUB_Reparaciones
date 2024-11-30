package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.app.dto.PagoDto;

@Service
public class PagoService {

	@Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PagoDto registrarAdelanto(PagoDto pago) {
        // Validar datos del pago
        validarDatosPago(pago);
        validarUnicoAdelanto(pago);
        validarAdelanto(pago);
        validarEstado(pago);
        // Insertar el adelanto en la tabla PAGO_TEST
        String sqlInsert = "INSERT INTO PAGO (SERIERegistro, MontoPago,Tipo) VALUES (?, ?,'ADELANTO')";
        jdbcTemplate.update(sqlInsert, pago.getSerieRegistro(), pago.getMontoPago());

        return pago;
    }

    private void validarEstado(PagoDto pago) {
    	String sql = "select IDEstado from REGISTRO where SERIERegistro=?";
        int estado = jdbcTemplate.queryForObject(sql, Integer.class, pago.getSerieRegistro());

        if (!(estado == 1 && estado == 2)) {
            throw new RuntimeException("El estado no se encuentra en el proceso (1-EN PROCESO) o (2-REPARADO), para proceder.");
        }
	}

	private void validarUnicoAdelanto(PagoDto pago) {
		String sql = "select count(IDPago) contador from pago where SERIERegistro = ? AND tipo = 'ADELANTO'";
		int unico = jdbcTemplate.queryForObject(sql, int.class, pago.getSerieRegistro());
		
		if (unico >= 1) {
			throw new RuntimeException("Ya esta cancelado el adelanto para esta SERIE");
		}
	}

	private void validarAdelanto(PagoDto pago) {
    	 // Consultar el valor de Adelanto en la tabla REGISTRO para el SERIERegistro proporcionado
        String sql = "SELECT Adelanto FROM REGISTRO WHERE SERIERegistro = ?";
        Double adelanto = jdbcTemplate.queryForObject(sql, Double.class, pago.getSerieRegistro());
        
        if (!adelanto.equals(pago.getMontoPago())) {
            throw new RuntimeException("El monto Proporcionado no coincide con el valor de Adelanto en el registro. El monto es: " + adelanto);
        }
        
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PagoDto registrarPagoFinal(PagoDto pago) {
        // Validar datos del pago
        validarDatosPago(pago);
        validarExistenciaTotal(pago);
        validarUnicoPagoFinal(pago);
        validarPagoFinal(pago);
        

        // Insertar el pago final en la tabla PAGO_TEST
        String sqlInsert = "INSERT INTO PAGO (SERIERegistro, MontoPago,Tipo) VALUES (?, ?,'TOTAL')";
        jdbcTemplate.update(sqlInsert, pago.getSerieRegistro(), pago.getMontoPago());

        // Actualizar el campo Adelanto en la tabla REGISTRO al total
        String sqlUpdate = "UPDATE REGISTRO SET Adelanto = TOTAL WHERE SERIERegistro = ?";
        jdbcTemplate.update(sqlUpdate, pago.getSerieRegistro());

        return pago;
    }

    private void validarExistenciaTotal(PagoDto pago) {
    	String sql = "select Total from REGISTRO where SERIERegistro = ?";
		double Total = jdbcTemplate.queryForObject(sql, double.class, pago.getSerieRegistro());
		
		if (Total == 0.0) {
			throw new RuntimeException("El pago Total aun no existe.");
		}
		
	}

	private void validarUnicoPagoFinal(PagoDto pago) {
		String sql = "select count(IDPago) contador from pago where SERIERegistro = ? AND tipo = 'TOTAL'";
		int unico = jdbcTemplate.queryForObject(sql, int.class, pago.getSerieRegistro());
		
		if (unico >= 1) {
			throw new RuntimeException("El pago Total de esta SERIE ya esta registrada");
		}
	}

	private void validarPagoFinal(PagoDto pago) {
    	// Consultar el valor de Adelanto en la tabla REGISTRO para el SERIERegistro proporcionado
        String sql = "select TOTAL - Adelanto AS Saldo from REGISTRO where SERIERegistro = ? ";
        Double saldo = jdbcTemplate.queryForObject(sql, Double.class, pago.getSerieRegistro());
        
        if (!saldo.equals(pago.getMontoPago())) {
            throw new RuntimeException("Su deuda restante es" + saldo);
        }
	}

	// Validar datos del pago
    private void validarDatosPago(PagoDto pago) {
        validarSerieRegistro(pago.getSerieRegistro());
        validarMontoPago(pago.getMontoPago());
    }

    private void validarSerieRegistro(String serieRegistro) {
        if (serieRegistro == null || serieRegistro.trim().isEmpty()) {
            throw new RuntimeException("La serie de registro es obligatoria.");
        }

        String sql = "SELECT COUNT(1) FROM REGISTRO WHERE SERIERegistro = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, serieRegistro);
        if (count == null || count == 0) {
            throw new RuntimeException("La serie de registro no existe.");
        }
        String sqlIngresos = "SELECT COUNT(1) FROM USOITEMS WHERE SERIERegistro = ?";
        Integer countIngresos = jdbcTemplate.queryForObject(sqlIngresos, Integer.class, serieRegistro);
        if (countIngresos != null && countIngresos >= 2) {
            throw new RuntimeException("La serie de registro ya tiene el límite máximo de dos ingresos.");
        }
    }

    private void validarMontoPago(double montoPago) {
        if (montoPago <= 0) {
            throw new RuntimeException("El monto del pago debe ser mayor a cero.");
        }
    }
}

package pe.edu.uni.app.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.app.dto.ActualizacionRegistroDto;

@Service

public class ActualizacionRegistroService {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ActualizacionRegistroDto Registro2 (ActualizacionRegistroDto bean) {
        // Validar que el registro exista
        String Problema = validarRegistroExistente(bean);
        bean.setProblema1(Problema);
        //validar estado
        validarestado(bean);
     // Validar la fecha real de entrega
        validartipofechaEntrega(bean);
        validarDiferenciaFechaRealEntrega(bean);
        // Obtener el importe desde la tabla relacionada
        double total = obtenerImportePorSerie(bean);
        
        bean.setTotal(total);
        double impuesto = total * 0.18;
        double importe = total - impuesto;
        // Calcular el impuesto y el total
        bean.setImpuesto(impuesto);
        bean.setImporte(importe);

        // Actualizar el estado y los valores en la tabla REGISTRO
        actualizarRegistro(bean);

        return bean;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ActualizacionRegistroDto Registro3 (ActualizacionRegistroDto bean) {
        // Validar que el registro exista
        int idTecnico = validarRegistroExistente2(bean);
        bean.setIdTecnico(idTecnico);
        //validar estado
        validarestado(bean);
        // Validar la fecha real de entrega
        validarFecha2(bean);
        

        // Actualizar el estado y los valores en la tabla REGISTRO
        actualizarRegistro2(bean);

        return bean;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ActualizacionRegistroDto Registro4 (ActualizacionRegistroDto bean) {
        // Validar que el registro exista
        double total = validarRegistroExistente3(bean);
        bean.setTotal(total);
        //validar estado
        validarestado2(bean);
        // Validar pago completo en 
        validarpago(bean);
        
        // Actualizar el estado y los valores en la tabla REGISTRO
        actualizarRegistro3(bean);

        return bean;
    }
    
    private void actualizarRegistro3(ActualizacionRegistroDto bean) {
    	String sql = "update REGISTRO SET IDEstado = 3 WHERE SERIERegistro = ?";
    	jdbcTemplate.update(sql, bean.getSerieRegistro());
	}

	private void validarpago(ActualizacionRegistroDto bean) {
    	String sql = "select SUM(MontoPago) FROM PAGO where SERIERegistro = ?";
    	double SumaPago = jdbcTemplate.queryForObject(sql, Double.class, bean.getSerieRegistro());
    	double Faltante = bean.getTotal()-SumaPago;
    	if(!(Faltante == 0.0)) {
    		 throw new RuntimeException("El Pago final no esta completo le falta pagar: " + Faltante);
    	}
    }

	private void validarestado2(ActualizacionRegistroDto bean) {
    	String sql = "select IDEstado from REGISTRO where SERIERegistro=?";
        int estado = jdbcTemplate.queryForObject(sql, Integer.class, bean.getSerieRegistro());

        if (estado != 2) {
            throw new RuntimeException("El estado no se encuentra en el proceso (2-REPARADO), para proceder.");
        }
	}

	private double validarRegistroExistente3(ActualizacionRegistroDto bean) {
    	String sql = "SELECT COUNT(1) FROM REGISTRO WHERE SERIERegistro = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, bean.getSerieRegistro());

        if (count == 0) {
            throw new RuntimeException("El registro con la serie proporcionada no existe: " + bean.getSerieRegistro());
        }
        
        String sql1 = "select TOTAL from REGISTRO WHERE SERIERegistro = ?";
        double total = jdbcTemplate.queryForObject(sql1, Double.class, bean.getSerieRegistro());
        return total; 
       
	}

	private void actualizarRegistro2(ActualizacionRegistroDto bean) {
    	String sql = "update REGISTRO SET IDEstado = 2 WHERE SERIERegistro = ?"
    			+ "update TECNICO set Activo = 0 where IDTecnico = ?";
        jdbcTemplate.update(sql, bean.getSerieRegistro(), bean.getIdTecnico());
    
	}

	private void validarFecha2(ActualizacionRegistroDto bean) {
        String sql = "SELECT DATEDIFF(DAY, GETDATE(), FechaRealDeEntrega) " +
                     "FROM REGISTRO " +
                     "WHERE SERIERegistro = ?";
        
        int dias = jdbcTemplate.queryForObject(sql, Integer.class, bean.getSerieRegistro());

        if (dias > 0) {
            throw new RuntimeException("Faltan " + dias + " días para la fecha real de entrega. No se puede culminar el proceso aún.");
        }
    }

	private int validarRegistroExistente2(ActualizacionRegistroDto bean) {
    	String sql = "SELECT COUNT(1) FROM REGISTRO WHERE SERIERegistro = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, bean.getSerieRegistro());

        if (count == 0) {
            throw new RuntimeException("El registro con la serie proporcionada no existe: " + bean.getSerieRegistro());
        }
        String sql1 = "SELECT IDTecnico FROM REGISTRO WHERE SERIERegistro = ?";
        Integer idTecnico = jdbcTemplate.queryForObject(sql1, Integer.class, bean.getSerieRegistro());
       return idTecnico; 
	}

	private void validarestado(ActualizacionRegistroDto bean) {
    	String sql = "select IDEstado from REGISTRO where SERIERegistro=?";
        int estado = jdbcTemplate.queryForObject(sql, Integer.class, bean.getSerieRegistro());

        if (estado != 1) {
            throw new RuntimeException("El estado no se encuentra en proceso (1-EN PROCESO) para proceder.");
        }
		
	}

	private void validartipofechaEntrega(ActualizacionRegistroDto bean) {
        if (bean.getFechaRealEntrega() == null || bean.getFechaRealEntrega().trim().isEmpty()) {
            throw new RuntimeException("La Fecha Real de Entrega es obligatoria.");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); 

        try {
            // Valida el formato estricto de la fecha
            dateFormat.parse(bean.getFechaRealEntrega());
        } catch (ParseException e) {
            throw new RuntimeException("La Fecha Real de Entrega '" + bean.getFechaRealEntrega() + "' debe estar en el formato 'yyyy-MM-dd'.");
        }
    }


    private String validarRegistroExistente(ActualizacionRegistroDto bean) {
        if (bean.getSerieRegistro() == null || bean.getSerieRegistro().trim().isEmpty()) {
            throw new RuntimeException("El campo SERIERegistro es nulo o vacío.");
        }
        System.out.println("SERIERegistro recibido: " + bean.getSerieRegistro());

        String sql = "SELECT COUNT(1) FROM REGISTRO WHERE SERIERegistro = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, bean.getSerieRegistro());

        if (count == 0) {
            throw new RuntimeException("El registro con la serie proporcionada no existe: " + bean.getSerieRegistro());
        }
        String sql1 = "SELECT ProblemaReportado FROM REGISTRO WHERE SERIERegistro = ?";
        String problema = jdbcTemplate.queryForObject(sql1, String.class, bean.getSerieRegistro());
        return problema;
    }

    private double obtenerImportePorSerie(ActualizacionRegistroDto bean) {
        String sql = "SELECT SUM(CostoTotal) Importe FROM USOITEMS WHERE SERIERegistro = ?";
        Double total = jdbcTemplate.queryForObject(sql, Double.class, bean.getSerieRegistro());

        if (total == null || total == 0) {
            throw new RuntimeException("No se encontró un item agregado para la serie de registro: " + bean.getSerieRegistro());
        }

        return total;
    }


    private void validarDiferenciaFechaRealEntrega(ActualizacionRegistroDto bean) {
        String sql = "SELECT DATEDIFF(DAY, FechaDeRegistro, ?) FROM REGISTRO WHERE SERIERegistro = ?";
        int dias = jdbcTemplate.queryForObject(sql, Integer.class, bean.getFechaRealEntrega(), bean.getSerieRegistro());
        if (dias < 0) {
            throw new RuntimeException("LA FECHA REAL ES MENOR A LA FECHA DE REGISTRO.");
        }
    }

    private void actualizarRegistro(ActualizacionRegistroDto bean) {
        String sql = "UPDATE REGISTRO SET Importe = ?,Impuesto = ? ,TOTAL = ?,FechaRealDeEntrega=?,ProblemaReportado = ? where SERIERegistro = ?";
        jdbcTemplate.update(sql, bean.getImporte(), bean.getImpuesto(), bean.getTotal(), bean.getFechaRealEntrega(),bean.getProblema1()+bean.getProblema2() ,bean.getSerieRegistro());
    }
}
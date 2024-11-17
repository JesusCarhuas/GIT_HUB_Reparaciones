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

        // Insertar el adelanto en la tabla PAGO
        String sqlInsert = "INSERT INTO PAGO (SERIERegistro, MontoPago) VALUES (?, ?)";
        jdbcTemplate.update(sqlInsert, pago.getSerieRegistro(), pago.getMontoPago());

        // Actualizar el campo Adelanto en la tabla REGISTRO
        String sqlUpdate = "UPDATE REGISTRO SET Adelanto = Adelanto + ? WHERE SERIERegistro = ?";
        jdbcTemplate.update(sqlUpdate, pago.getMontoPago(), pago.getSerieRegistro());

        return pago;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PagoDto registrarPagoFinal(PagoDto pago) {
        // Validar datos del pago
        validarDatosPago(pago);

        // Insertar el pago final en la tabla PAGO
        String sqlInsert = "INSERT INTO PAGO (SERIERegistro, MontoPago) VALUES (?, ?)";
        jdbcTemplate.update(sqlInsert, pago.getSerieRegistro(), pago.getMontoPago());

        // Actualizar el campo Adelanto en la tabla REGISTRO al total
        String sqlUpdate = "UPDATE REGISTRO SET Adelanto = TOTAL WHERE SERIERegistro = ?";
        jdbcTemplate.update(sqlUpdate, pago.getSerieRegistro());

        return pago;
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
    }

    private void validarMontoPago(double montoPago) {
        if (montoPago <= 0) {
            throw new RuntimeException("El monto del pago debe ser mayor a cero.");
        }
    }
}
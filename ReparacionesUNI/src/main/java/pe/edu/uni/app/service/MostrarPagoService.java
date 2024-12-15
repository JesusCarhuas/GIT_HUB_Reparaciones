package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

import pe.edu.uni.app.dto.MostrarPagoDto;

@Service
public class MostrarPagoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MostrarPagoDto> getPago(String serie) {
        if (serie == null || serie.trim().isEmpty()) {
            throw new IllegalArgumentException("El parámetro Serie no puede ser nulo o vacío.");
        }

        String sql = "SELECT IDpago, SERIERegistro, MontoPago, Tipo FROM PAGO WHERE SERIERegistro = ?";
        List<MostrarPagoDto> pagos = jdbcTemplate.query(
            sql,
            new BeanPropertyRowMapper<>(MostrarPagoDto.class),
            serie
        );

        if (pagos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron pagos para la serie proporcionada.");
        }

        return pagos;
    }
    
    
    
}

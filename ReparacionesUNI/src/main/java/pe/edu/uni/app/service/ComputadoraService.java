package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.app.dto.ComputadoraDto;

@Service
public class ComputadoraService {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ComputadoraDto crearComputadora(ComputadoraDto bean) {
        // Validar los datos de la computadora
        validarDatosComputadora(bean);

        // Insertar la computadora en la base de datos
        String sql = "INSERT INTO COMPUTADORA (IDCliente, Marca, NumeroSerie, AnioFabricacion) " +
                     "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, bean.getIdCliente(), bean.getMarca(), bean.getNumeroSerie(), bean.getAnioFabricacion());

        return bean;
    }
    
    private void validarDatosComputadora(ComputadoraDto bean) {
        // Validar ID del Cliente
        validarIdCliente(bean.getIdCliente());
        // Validar Marca
        validarCampoTexto(bean.getMarca(), "Marca", 1, 50);
        // Validar Número de Serie
        validarNumeroSerie(bean.getNumeroSerie());
        validarUnicoNumeroSerie(bean.getNumeroSerie());
        // Validar Año de Fabricación
        validarAnioFabricacion(bean.getAnioFabricacion());
    }
    
    private void validarIdCliente(int idCliente) {
        if (idCliente < 0) {
            throw new RuntimeException("El ID del cliente debe ser un número positivo.");
        }
        String sql = "SELECT COUNT(1) FROM CLIENTE WHERE IDCliente = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idCliente);
        if (count == null || count == 0) {
            throw new RuntimeException("El cliente con el ID proporcionado no existe.");
        }
    }
    
    private void validarCampoTexto(String campo, String nombreCampo, int minLength, int maxLength) {
        if (campo == null || campo.trim().isEmpty()) {
            throw new RuntimeException(nombreCampo + " es obligatorio.");
        }
        if (campo.length() < minLength || campo.length() > maxLength) {
            throw new RuntimeException(nombreCampo + " debe tener entre " + minLength + " y " + maxLength + " caracteres.");
        }
        if (!campo.matches("^[a-zA-ZÁÉÍÓÚáéíóúñÑ0-9 ]+$")) {
            throw new RuntimeException(nombreCampo + " solo debe contener letras, números y espacios.");
        }
    }

    private void validarNumeroSerie(String numeroSerie) {
        if (numeroSerie == null || numeroSerie.trim().isEmpty()) {
            throw new RuntimeException("El número de serie es obligatorio.");
        }
        if (!numeroSerie.matches("^[a-zA-Z0-9-]+$")) {
            throw new RuntimeException("El número de serie solo puede contener letras, números y guiones.");
        }
    }

    private void validarUnicoNumeroSerie(String numeroSerie) {
        String sql = "SELECT COUNT(1) FROM COMPUTADORA WHERE NumeroSerie = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, numeroSerie);
        if (count != null && count > 0) {
            throw new RuntimeException("Ya existe una computadora con el número de serie proporcionado.");
        }
    }

    private void validarAnioFabricacion(int anioFabricacion) {
        int anioActual = java.time.Year.now().getValue();
        if (anioFabricacion < 1980 || anioFabricacion > anioActual) {
            throw new RuntimeException("El año de fabricación debe ser un valor entre 1980 y el año actual.");
        }
    }
}

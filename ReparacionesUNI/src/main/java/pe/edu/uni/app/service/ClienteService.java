package pe.edu.uni.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.app.dto.ClienteDto;

@Service
public class ClienteService {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ClienteDto crearCliente(ClienteDto bean) {
		
		// Validar datos del cliente
        validarDatosCliente(bean);

        // Insertar cliente en la base de datos
        String sql = "INSERT INTO CLIENTE (Nombre, ApellidoPaternoC, ApellidoMaternoC, ClienteDni, Direccion, Telefono, Email) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, bean.getNombre(), bean.getApellidoPaterno(), bean.getApellidoMaterno(),
                            bean.getDni(), bean.getDireccion(), bean.getTelefono(), bean.getEmail());

        return bean;
    }
	
	public List<Map<String, Object>> obtenerTodosLosClientes() {
        String sql = "SELECT IDCliente,Nombre,ApellidoPaternoC,ApellidoMaternoC,ClienteDni FROM CLIENTE";
        return jdbcTemplate.queryForList(sql);
    }
	
	public ClienteDto validar(String dni) {
		String sql = """
				select ClienteDni from cliente where ClienteDni = ?
			""";
		ClienteDto bean;
		try {
			bean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ClienteDto.class),dni);
		} catch (EmptyResultDataAccessException e) {
			bean = null;
		}
		return bean;
	}
	
	
	private void validarDatosCliente(ClienteDto bean) {
        // Validar nombre
		validarCampoTexto(bean.getNombre(), "Nombre", 1, 50);
        // Validar apellido paterno
        validarCampoTexto(bean.getApellidoPaterno(), "Apellido Paterno", 1, 25);
        // Validar apellido materno
        validarCampoTexto(bean.getApellidoMaterno(), "Apellido Materno", 1, 25);
        // Validar DNI
        validarDni(bean.getDni());
        validarUnicoDni(bean.getDni());
        // Validar dirección
        validarDireccion(bean.getDireccion(), "Dirección", 1, 50);
        // Validar teléfono
        validarTelefono(bean.getTelefono());
        // Validar email
        validarEmail(bean.getEmail());
    }
	
	

	private void validarDireccion(String direccion, String NombreDireccion, int minLength, int maxLength) {
		if (direccion == null || direccion.trim().isEmpty()) {
	        throw new RuntimeException(NombreDireccion + " es obligatorio.");
	    }
	    if (direccion.length() < minLength || direccion.length() > maxLength) {
	        throw new RuntimeException(NombreDireccion + " debe tener entre " + minLength + " y " + maxLength + " caracteres.");
	    }
	}


	private void validarCampoTexto(String campo, String nombreCampo, int minLength, int maxLength) {
	    if (campo == null || campo.trim().isEmpty()) {
	        throw new RuntimeException(nombreCampo + " es obligatorio.");
	    }
	    if (campo.length() < minLength || campo.length() > maxLength) {
	        throw new RuntimeException(nombreCampo + " debe tener entre " + minLength + " y " + maxLength + " caracteres.");
	    }
	    if (!campo.matches("^[a-zA-ZÁÉÍÓÚáéíóúñÑ ]+$")) {
	        throw new RuntimeException(nombreCampo + " solo debe contener letras y espacios.");
	    }
	}
	 
	 

	    private void validarDni(String dni) {
	        if (dni == null || dni.length() != 8 || !dni.matches("\\d{8}")) {
	            throw new RuntimeException("El DNI debe tener exactamente 8 dígitos numéricos.");
	        }
	    }

	    private void validarUnicoDni(String dni) {
	        String sql = "SELECT COUNT(1) FROM CLIENTE WHERE ClienteDni = ?";
	        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, dni);
	        if (count != null && count > 0) {
	            throw new RuntimeException("El cliente ya existe con el DNI proporcionado.");
	        }
	    }

	    private void validarTelefono(String telefono) {
	        if (telefono != null && !telefono.isEmpty()) {
	            if (!telefono.matches("\\d{9,15}")) {
	                throw new RuntimeException("El teléfono debe contener entre 9 y 15 dígitos numéricos.");
	            }
	        }
	    }

	    private void validarEmail(String email) {
	        if (email == null || email.trim().isEmpty()) {
	            throw new RuntimeException("El correo electrónico es obligatorio.");
	        }
	        if (!email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
	            throw new RuntimeException("El correo electrónico tiene un formato inválido.");
	        }
	    }
	}

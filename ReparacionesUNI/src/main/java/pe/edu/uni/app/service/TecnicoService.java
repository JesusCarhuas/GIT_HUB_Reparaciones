package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.app.dto.TecnicoDto;
@Service
public class TecnicoService {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public TecnicoDto crearTecnico(TecnicoDto bean) {
		
		// Validar datos del tecnico
        validarDatosTecnico(bean);

        // Insertar tecnico en la base de datos
        String sql = "INSERT INTO TECNICO (Nombre, ApellidoPaternoT, ApellidoMaternoT, TecnicoDni, Direccion, Telefono, Email, Contrasena, Activo) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0)";
        jdbcTemplate.update(sql, bean.getNombre(), bean.getApellidoPaterno(), bean.getApellidoMaterno(), bean.getDni(),
                            bean.getDireccion(), bean.getTelefono(), bean.getEmail(), bean.getContrasena());

        return bean;
    }
	
	private void validarDatosTecnico(TecnicoDto bean) {
        // Validar nombre
		validarCampoTexto(bean.getNombre(), "Nombre", 1, 50);
        // Validar apellido paterno
        validarCampoTexto(bean.getApellidoPaterno(), "Apellido Paterno", 1, 25);
        // Validar apellido materno
        validarCampoTexto(bean.getApellidoMaterno(), "Apellido Materno", 1, 25);
        //Validar DNI
        validarDni(bean.getDni());
        validarUnicoDni(bean.getDni());
        // Validar dirección
        validarDireccion(bean.getDireccion(), "Dirección", 1, 50);
        // Validar teléfono
        validarTelefono(bean.getTelefono());
        // Validar email
        validarEmail(bean.getEmail());
        // Validar contrasena (y que sea segura)
        validarContrasena(bean.getContrasena(),10,50);
    }

    private void validarDni(String dni) {
        if (dni == null || dni.length() != 8 || !dni.matches("\\d{8}")) {
            throw new RuntimeException("El DNI debe tener exactamente 8 dígitos numéricos.");
        }
    }
    private void validarContrasena(String contrasena, int minLength, int maxLength) {
    	if(contrasena == null || contrasena.trim().isEmpty()) {
    		throw new RuntimeException("Se requiere una contraseña sin espacios, con un minimo de "+minLength+" carácteres y un máximo de "+maxLength+ "carácteres");
    	}
    	if(contrasena.length() < minLength || contrasena.length() > maxLength) {
    		throw new RuntimeException("Se requiere una contraseña sin espacios, con un minimo de "+minLength+" carácteres y un máximo de "+maxLength+ "carácteres");
    	}
    	validarContrasenaSegura(contrasena,minLength);
    }
    private void validarContrasenaSegura(String contrasena, int l) {
    	if(!contrasena.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[.,]).{"+l+",}$")){
    		throw new RuntimeException("La contraseña debe tener al menos 1 dígito, una mayúscula y un punto o coma");
    	}
    }
    private void validarUnicoDni(String dni) {
        String sql = "SELECT COUNT(1) FROM TECNICO WHERE TecnicoDni = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, dni);
        if (count != null && count > 0) {
            throw new RuntimeException("El técnico con el DNI proporcionado ya existe.");
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
	private void validarDireccion(String direccion, String NombreDireccion, int minLength, int maxLength) {
		if (direccion == null || direccion.trim().isEmpty()) {
	        throw new RuntimeException(NombreDireccion + " es obligatorio.");
	    }
	    if (direccion.length() < minLength || direccion.length() > maxLength) {
	        throw new RuntimeException(NombreDireccion + " debe tener entre " + minLength + " y " + maxLength + " caracteres.");
	    }
	}

    private void validarTelefono(String telefono) {
        if (telefono != null && !telefono.isEmpty()) {
            if (!telefono.matches("\\d{9,15}")) {
                throw new RuntimeException("El número telefónico debe contener entre 9 y 15 dígitos numéricos.");
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


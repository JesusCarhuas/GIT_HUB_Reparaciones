package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.SeguridadTecnicoDto;

@Service
public class SeguridadTecnicoService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public SeguridadTecnicoDto validar(String dni, String clave) {
	    String sql = """
	            SELECT Contrasena AS contraseña,
	                   TecnicoDni AS dni,
	                   CONCAT(Nombre, ' ', ApellidoPaternoT, ' ', ApellidoMaternoT) AS nombre
	            FROM TECNICO
	            WHERE TecnicoDni = ? AND Contrasena = ?
	        """;
	    SeguridadTecnicoDto bean;
	    try {
	        bean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(SeguridadTecnicoDto.class), dni, clave);
	    } catch (EmptyResultDataAccessException e) {
	        bean = null;
	    }
	    return bean;
	}
	
	public SeguridadTecnicoDto validarCliente(String dni) {
	    String sql = """
	            SELECT CONCAT(Nombre, ' ', ApellidoPaternoC, ' ', ApellidoMaternoC) AS nombre 
	            FROM CLIENTE 
	            WHERE ClienteDni = ?
	        """;
	    SeguridadTecnicoDto bean;
	    try {
	        bean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(SeguridadTecnicoDto.class), dni);
	    } catch (EmptyResultDataAccessException e) {
	        bean = null;
	    }
	    return bean;
	}
	
}

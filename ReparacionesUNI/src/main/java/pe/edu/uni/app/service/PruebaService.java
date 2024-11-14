package pe.edu.uni.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PruebaService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SELECT_BASE = """
			select * from TIPOITEM
		""";
	
	public List<Map<String,Object>> consultarTodos(){
		List<Map<String,Object>> lista = null;
		lista = jdbcTemplate.queryForList(SELECT_BASE);
		return lista;
	}
}

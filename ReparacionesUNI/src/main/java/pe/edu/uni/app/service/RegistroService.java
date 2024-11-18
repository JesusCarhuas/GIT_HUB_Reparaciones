package pe.edu.uni.app.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.app.dto.RegistroDto;

@Service
public class RegistroService {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public RegistroDto crearRegistro(RegistroDto bean) {
		// validar datos de registro
		validarDatosRegistro(bean);
		// generar serie de registro;
		bean.setSERIERegistro(construirSerieRegistro("ru",bean.getMarca()));
		//estado comienza en proceso
		bean.setIdEstado(1);
		//valores iniciales
		bean.setImporte(0);
		bean.setImpuesto(0);
		bean.setTotal(0);
		//problema no especificado
		problemaNoEspecificado(bean);
		// crear registro
		String sql = "INSERT INTO dbo.REGISTRO( "
				+ "SERIERegistro,IDCliente,IDComputadora, "
				+ "IDTecnico,IDEstado,Adelanto, "
				+ "Importe,Impuesto,TOTAL, "
				+ "FechaDeRegistro,FechaEstimadaDeEntrega,ProblemaReportado "
				+ ") VALUES ( "
				+ "?,?,?,?, "
				+ "?,?,?,?,?, "
				+ "GETDATE(),DATEADD(day, ?, GETDATE()), ?)";
		jdbcTemplate.update(sql, bean.getSERIERegistro(), bean.getIdCliente(), bean.getIdComputadora(), 
				bean.getIdTecnico(), bean.getIdEstado(), bean.getAdelanto(), 
				bean.getImporte(), bean.getImpuesto(), bean.getTotal(), 
				bean.getDuracionEstimada(), bean.getProblemaReportado());
		
		//actualizar el stock en la tabla stock
				actualizarTecnico(bean.getIdTecnico());
				
		return bean;
	}
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void actualizarTotal(String serie, double add, String ph) {
		RegistroDto bean = new RegistroDto();
		//existe registro
		if(!existeRegistro(serie)) {
			throw new RuntimeException("El registro con la serie especificada no existe.");
		}
		bean.setSERIERegistro(serie);
		//el aumento debe ser positivo
		if(add<=0) {
			throw new RuntimeException("El "+ph+" debe ser positivo");
		}
		String sql = "SELECT Importe, Impuesto, TOTAL FROM REGISTRO WHERE SERIERegistro = ?";
		List<Map<String,Object>> result = jdbcTemplate.queryForList(sql, serie);
		if(result.isEmpty()) {
			throw new RuntimeException("No se obtuvieron resultados.");
		}
		Map<String,Object> row = result.get(0);
		bean.setImporte((double) row.get("Importe"));
		bean.setImpuesto((double) row.get("Impuesto"));
		bean.setTotal((double) row.get("Total"));
		addTotal(bean, add);
		sql = "UPDATE REGISTRO SET Importe = ?, Impuesto = ?, TOTAL = ? WHERE SERIERegistro = ?";
		jdbcTemplate.update(sql, bean.getImporte(), bean.getImpuesto(), bean.getTotal(), bean.getSERIERegistro());
	}
	private void actualizarTecnico(int idTecnico) {
		String sqlUpdate = "UPDATE TECNICO SET Activo = 1 WHERE IDTecnico = ?";
        jdbcTemplate.update(sqlUpdate, idTecnico);
	}
	private void validarDatosRegistro(RegistroDto bean) {
		//validar computadora y obtener cliente
		validarComputadora(bean);
		//validar cliente obtenido
		validarCliente(bean.getIdCliente());
		//validar técnico
		validarTecnico(bean.getIdTecnico());
		// validar adelanto
		validarAdelanto(bean.getAdelanto(),100);
		//validar duración estimada
		validarDuracionEstimada(bean.getDuracionEstimada(),15);
	}
	private void problemaNoEspecificado(RegistroDto bean) {
		if(bean.getProblemaReportado() == null) {
			bean.setProblemaReportado("No especificado");
		}
	}
	private void validarAdelanto(Double adelanto, double minValue) {
		if(adelanto == null || adelanto < minValue) {
			throw new RuntimeException("Un monto de adelanto mayor a "+minValue+" es obligatorio");
		}
	}
	private void validarDuracionEstimada(int duracion, int maxValue) {
		Integer durac = Integer.valueOf(duracion);
		if(durac == null) {
			throw new RuntimeException("La duración estimada es obligatoria.");
		}
		if(durac <= 0 || durac > maxValue) {
			throw new RuntimeException("La duración estimada debe ser positiva y menor que "+maxValue+" segundos. Tiempo: "+duracion);
		}
	}
	private void validarCliente(int id) {
        String sql = "SELECT COUNT(1) FROM CLIENTE WHERE IDCliente = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        if (count == null || count == 0) {
        	throw new RuntimeException("El cliente asociado con el ID de computadora proporcionado no existe.");
        }
	}
	private void validarComputadora(RegistroDto bean) {
		if(Integer.valueOf(bean.getIdComputadora()) == null) {
			throw new RuntimeException("El ID de computadora es obligatorio.");
		}
		String sql = "SELECT COUNT(1) FROM REGISTRO WHERE IDComputadora = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, bean.getIdComputadora());
		if(!(count == null || count == 0)) {
			throw new RuntimeException("La computadora con el ID proporcionado ya se encuentra en reparación.");
		}
	    sql = "SELECT COMPUTADORA.IDComputadora, COMPUTADORA.Marca, COMPUTADORA.IDCliente "
	    		+ "FROM COMPUTADORA "
	    		+ "INNER JOIN CLIENTE ON COMPUTADORA.IDCliente = CLIENTE.IDCliente "
	    		+ "WHERE IDComputadora = ?";

	    // Usamos query y obtenemos un único resultado en un mapa
	    List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, bean.getIdComputadora());
	    // Si el resultado está vacío, lanzar una excepción o manejar el caso
	    if (result.isEmpty()) {
	        throw new RuntimeException("No se encontró la computadora con el ID proporcionado.");
	    }
	    //Obtener mapa único de la lista
	    Map<String, Object> row = result.get(0);
	    // Obtener el IDCliente desde el mapa
	    bean.setIdCliente((int) row.get("IDCliente")) ;
	    // Obtener la marca desde el mapa
	    bean.setMarca((String) row.get("Marca"));
	}
	private void validarTecnico(Integer id) {
		if(id == null) {
			throw new RuntimeException("El ID de técnico es obligatorio.");
		}
		String sql = "SELECT Activo FROM TECNICO WHERE IDTecnico = ?";
		Integer activo;
		try {
			activo = jdbcTemplate.queryForObject(sql, Integer.class, id);
			if(activo == null) {
				throw new RuntimeException("No se encontró al técnico con el ID proporcionado.");
			}
		} catch(EmptyResultDataAccessException e) {
			throw new RuntimeException("No se encontró al técnico con el ID proporcionado.");
		}
		
		if(activo != 0) {
			throw new RuntimeException("El técnico no se encuentra disponible en este momento.");
		}
	}
	private String construirSerieRegistro(String prefix, String marca) {
		while(marca.length() < 3) {
			marca+="0";
		}
		String serie = (prefix.substring(0,2)+marca.trim().substring(0,3)).toUpperCase();
		String sql = "SELECT COUNT(1)+1 FROM REGISTRO WHERE SUBSTRING(SERIERegistro,1,5) = ?";
		String count = jdbcTemplate.queryForObject(sql, Integer.class, serie).toString();
		serie += ("00000"+count).substring(count.length());
		return serie;
	}
	private void addTotal(RegistroDto bean, double add) {
		double impuesto = add * 0.18;
		double total = add + impuesto;
		bean.setTotal(bean.getTotal()+total);
		bean.setImporte(bean.getImporte()+add);
		bean.setImpuesto(bean.getImpuesto()+impuesto);
	}
	public boolean existeRegistro(String serie) {
		String sql = "SELECT COUNT(1) FROM REGISTRO WHERE SERIERegistro = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, serie);
		return !(count == null || count == 0);
	}
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void avanzarEstado(String serie) {
		//validar registro
		if(!existeRegistro(serie)) {
			throw new RuntimeException("El registro con la serie proporcionada no existe.");
		}
		String sql = "SELECT IDEstado from ESTADO";
		Integer lastEstado = jdbcTemplate.queryForList(sql, Integer.class).getLast();
		sql = "SELECT IDEstado from REGISTRO WHERE SERIERegistro = ?";
		Integer estado = jdbcTemplate.queryForObject(sql, Integer.class, serie);
		if(estado == lastEstado) {
			throw new RuntimeException("El registro ya está en su fase final.");
		}
		estado += 1;
		sql = "UPDATE REGISTRO SET IDEstado = ? WHERE SERIERegistro = ?";
		jdbcTemplate.update(sql, estado, serie);
	}
}




package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.uni.app.dto.UsoItemsDto;

@Service
public class UsoItemsService {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UsoItemsDto registrarUsoItems(UsoItemsDto bean) {
		// Validar datos del uso de items
		validarExistenciaItem(bean.getIdItem());
        validarSerieRegistro(bean.getSerieRegistro());
        double costoUnitario = obtenerCostoUnitario(bean.getIdItem());
        bean.setCostoUnitario(costoUnitario);
        validarCantidadDisponible(bean.getIdItem(), bean.getCantidad());
		//Insercion de datos en la tabla
        String sqlInsert = "INSERT INTO USOITEMS (idItem, serieRegistro, costoUnitario, cantidad) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsert, bean.getIdItem(), bean.getSerieRegistro(), bean.getCostoUnitario(), bean.getCantidad());

		
		//actualizar el stock en la tabla stock
		actualizarStock(bean.getIdItem(), bean.getCantidad());
		
		
		return bean;
	}
	 private void validarExistenciaItem(int idItem) {
	        String sql = "SELECT COUNT(1) FROM ITEM WHERE idItem = ?";
	        int count = jdbcTemplate.queryForObject(sql, Integer.class, idItem);
	        if (count == 0) {
	            throw new RuntimeException("El ID del item no existe.");
	        }
	    }
	 
	 private void validarSerieRegistro(String serieRegistro) {
		    // Validar que la serie de registro no sea nula o vacía
		    if (serieRegistro == null || serieRegistro.trim().isEmpty()) {
		        throw new RuntimeException("La serie de registro es obligatoria.");
		    }

		    // Validar que la serie de registro exista en la tabla REGISTRO
		    String sql = "SELECT COUNT(1) contador FROM REGISTRO WHERE SERIERegistro = ?";
		    int count = jdbcTemplate.queryForObject(sql, Integer.class, serieRegistro);

		    if (count == 0) {
		        throw new RuntimeException("La serie de registro proporcionada no existe en la base de datos.");
		    }
		}
	 
	 private double obtenerCostoUnitario(int idItem) {
	        String sql = "SELECT CostoUnitario FROM ITEM WHERE idItem = ?";
	        Double costoUnitario = jdbcTemplate.queryForObject(sql, Double.class, idItem);
	        if (costoUnitario == null) {
	            throw new RuntimeException("No se pudo obtener el costo unitario para el ID del item proporcionado.");
	        }
	        return costoUnitario;
	    }
	 
	 private void validarCantidadDisponible(int idItem, int cantidadSolicitada) {
	        String sql = "SELECT Stock FROM ITEM WHERE idItem = ?";
	        Integer stockDisponible = jdbcTemplate.queryForObject(sql, Integer.class, idItem);
	        if (stockDisponible == null || stockDisponible < cantidadSolicitada) {
	            throw new RuntimeException("No hay suficiente stock disponible. Stock actual: " + stockDisponible);
	        }
	    }
	 
	 private void actualizarStock(int idItem, int cantidad) {
	        String sqlUpdate = "UPDATE ITEM SET Stock = Stock - ? WHERE idItem = ?";
	        jdbcTemplate.update(sqlUpdate, cantidad, idItem);
	    }
}

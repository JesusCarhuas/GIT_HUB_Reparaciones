package pe.edu.uni.app.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.ComputadoraDto;
import pe.edu.uni.app.service.ComputadoraService;

@RestController
@RequestMapping("/computadora")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComputadoraRest {
	@Autowired
	private ComputadoraService computadoraService ;
	
	@PostMapping("/crearComputadora")
	public ResponseEntity<?> crearComputadora(@RequestBody ComputadoraDto bean) {
	    try {
	        bean = computadoraService.crearComputadora(bean);
	        return ResponseEntity.ok(bean);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Error: " + e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error interno: " + e.getMessage());
	    }
	}
	@GetMapping("/VisualizarComputadora")
	public ResponseEntity<?> obtenerTodosLasComputadoras() {
	    try {
	        List<Map<String, Object>> tiposDeItems = computadoraService.obtenerTodosLasComputadoras();
	        return new ResponseEntity<>(tiposDeItems, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
	
}

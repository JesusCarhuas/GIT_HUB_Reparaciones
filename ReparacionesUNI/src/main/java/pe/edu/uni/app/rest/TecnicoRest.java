package pe.edu.uni.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.TecnicoDto;
import pe.edu.uni.app.service.TecnicoService;

@RestController
@RequestMapping("/tecnico")
public class TecnicoRest {
	@Autowired
	private TecnicoService tecnicoService;
	
	@PostMapping("/crearTecnico")
	public ResponseEntity<?> crearTecnico(@RequestBody TecnicoDto bean) {
	    try {
	        bean = tecnicoService.crearTecnico(bean);
	        return ResponseEntity.ok(bean);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Error: " + e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error interno: " + e.getMessage());
	    }
	}
	
}

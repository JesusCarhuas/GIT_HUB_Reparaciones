package pe.edu.uni.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.RegistroDto;
import pe.edu.uni.app.service.RegistroService;

@RestController
@RequestMapping("/registro")
public class RegistroRest {
	@Autowired
	private RegistroService registroService;
	
	@PostMapping("/crearRegistro")
	public ResponseEntity<?> crearRegistro(@RequestBody RegistroDto bean) {
	    try {
	        bean = registroService.crearRegistro(bean);
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
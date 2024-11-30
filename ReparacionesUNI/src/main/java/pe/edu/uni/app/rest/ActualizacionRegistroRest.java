package pe.edu.uni.app.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.ActualizacionRegistroDto;
import pe.edu.uni.app.service.ActualizacionRegistroService;


@RestController
@RequestMapping("/actualizacion_registro")
public class ActualizacionRegistroRest {
	@Autowired
	private ActualizacionRegistroService actualizacionService;
	
	@PutMapping("/proceso_1")
	public ResponseEntity<?> Registro2(@RequestBody ActualizacionRegistroDto bean){
		try {
			bean = actualizacionService.Registro2(bean);
			return ResponseEntity.ok(bean);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Error: " + e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error interno: " + e.getMessage());
	    }
	}
	
	@PutMapping("/proceso_2")
	public ResponseEntity<?> Registro3(@RequestBody ActualizacionRegistroDto bean){
		try {
			bean = actualizacionService.Registro3(bean);
			return ResponseEntity.ok(bean);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Error: " + e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error interno: " + e.getMessage());
	    }
	}
	
	@PutMapping("/proceso_3")
	public ResponseEntity<?> Registro4(@RequestBody ActualizacionRegistroDto bean){
		try {
			bean = actualizacionService.Registro4(bean);
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
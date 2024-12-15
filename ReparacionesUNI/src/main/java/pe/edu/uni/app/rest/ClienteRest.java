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

import pe.edu.uni.app.dto.ClienteDto;
import pe.edu.uni.app.service.ClienteService;


@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteRest {
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/seguridad")
	public ResponseEntity<?> validarCliente(@RequestBody ClienteDto bean){
		System.out.println(bean);
		try {
			bean = clienteService.validar(bean.getDni());
			if(bean == null) {
				throw new RuntimeException("Datos incorrectos.");
			}
			return new ResponseEntity<>(bean, HttpStatus.OK);
		} catch (Exception e) {
			String mensaje = "Error en el proceso. " + e.getMessage();
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PostMapping("/crearcliente")
	public ResponseEntity<?> crearCliente(@RequestBody ClienteDto bean) {
	    try {
	        bean = clienteService.crearCliente(bean);
	        return ResponseEntity.ok(bean);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Error: " + e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error interno: " + e.getMessage());
	    }
	}
	@GetMapping("/VisualizarClientes")
	public ResponseEntity<?> obtenerTodosLosClientes() {
	    try {
	        List<Map<String, Object>> tiposDeItems = clienteService.obtenerTodosLosClientes();
	        return new ResponseEntity<>(tiposDeItems, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
}

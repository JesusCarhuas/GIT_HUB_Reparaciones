package pe.edu.uni.app.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.service.PruebaService;

@RestController
@RequestMapping("/prueba")
public class PruebaRest {
	@Autowired
	private PruebaService pruebaService;
	
	@GetMapping
	public List<Map<String, Object>> consultarTodos() {
		return pruebaService.consultarTodos();
	}
}

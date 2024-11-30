package pe.edu.uni.app.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.uni.app.dto.UsoItemsDto;
import pe.edu.uni.app.service.UsoItemsService;

@RestController
@RequestMapping("/items")
public class UsoItemsRest {
	
	@Autowired
    private UsoItemsService usoItemsService;
	
	@PostMapping("/ingreso-uso-items")
	public ResponseEntity<?> registrarUsoItems(@RequestBody UsoItemsDto bean) {
        try {
            bean = usoItemsService.registrarUsoItems(bean);
            return new ResponseEntity<>(bean, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping("/TipoItems")
    public ResponseEntity<?> obtenerTodosLosTiposDeItems() {
        try {
            List<Map<String, Object>> tiposDeItems = usoItemsService.obtenerTodosLosTiposDeItems();
            return new ResponseEntity<>(tiposDeItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping("/TipoItems/{idTipoItem}")
    public ResponseEntity<?> obtenerItemsPorTipo(@PathVariable int idTipoItem) {
        try {
            List<Map<String, Object>> items = usoItemsService.obtenerItemsPorTipo(idTipoItem);
            
            if (items != null && !items.isEmpty()) {
                return new ResponseEntity<>(items, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontraron items para el tipo especificado.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error en el servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


package pe.edu.uni.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("/uso-items")
	public ResponseEntity<?> registrarUsoItems(@RequestBody UsoItemsDto bean) {
        try {
            bean = usoItemsService.registrarUsoItems(bean);
            return new ResponseEntity<>(bean, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	
}

package pe.edu.uni.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pe.edu.uni.app.dto.MostrarPagoDto;
import pe.edu.uni.app.service.MostrarPagoService;
import java.util.List;

@RestController
@RequestMapping("/monto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MostrarPagoRest {
    @Autowired
    private MostrarPagoService service;

    @GetMapping("/{serie}")
    public ResponseEntity<?> getPagoPorSerie(@PathVariable("serie") String serie) {
        try {
            List<MostrarPagoDto> pagos = service.getPago(serie);
            return ResponseEntity.ok(pagos);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }
        
    
}



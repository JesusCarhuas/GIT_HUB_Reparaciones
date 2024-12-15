package pe.edu.uni.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.app.dto.MostrarRegistroDto;
import pe.edu.uni.app.service.MostrarRegistroService;

import java.util.List;

@RestController
@RequestMapping("/registro")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MostrarRegistroRest {

    @Autowired
    private MostrarRegistroService service;

    @GetMapping("/{serie}")
    public ResponseEntity<?> obtenerRegistroPorSerie(@PathVariable("serie") String serie) {
        try {
            MostrarRegistroDto registro = service.obtenerRegistroPorSerie(serie);
            return ResponseEntity.ok(registro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/cliente/{dni}")
    public ResponseEntity<?> obtenerRegistrosPorCliente(@PathVariable("dni") String dni) {
        try {
            List<MostrarRegistroDto> registros = service.obtenerRegistrosPorDni(dni);
            return ResponseEntity.ok(registros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> obtenerTodosLosRegistros() {
        try {
            List<MostrarRegistroDto> registros = service.obtenerTodosLosRegistros();
            return ResponseEntity.ok(registros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }
}




package pe.edu.uni.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.uni.app.dto.PagoDto;
import pe.edu.uni.app.service.PagoService;



@RestController
@RequestMapping("/pago")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PagoRest {

    @Autowired
    private PagoService pagoService;

    @PostMapping("/adelanto")
    public ResponseEntity<?> registrarAdelanto(@RequestBody PagoDto pago) {
        try {
            PagoDto resultado = pagoService.registrarAdelanto(pago);
            return new ResponseEntity<>(resultado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    // Endpoint para registrar el pago final (simulado)
    @PostMapping("/pago-final")
    public ResponseEntity<?> registrarPagoFinal(@RequestBody PagoDto pago) {
        try {
            PagoDto resultado = pagoService.registrarPagoFinal(pago);
            return new ResponseEntity<>(resultado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
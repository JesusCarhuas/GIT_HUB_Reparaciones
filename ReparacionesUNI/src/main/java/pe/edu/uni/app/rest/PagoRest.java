package pe.edu.uni.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.uni.app.dto.PagoDto;
import pe.edu.uni.app.service.PagoService;

@RestController
@RequestMapping("/pago")
public class PagoRest {

	@Autowired
    private PagoService pagoService;

    // Endpoint para registrar un adelanto
    @PostMapping("/adelanto")
    public ResponseEntity<?> registrarAdelanto(@RequestBody PagoDto pago) {
        try {
            PagoDto resultado = pagoService.registrarAdelanto(pago);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    // Endpoint para registrar el pago final
    @PostMapping("/pago-final")
    public ResponseEntity<?> registrarPagoFinal(@RequestBody PagoDto pago) {
        try {
            PagoDto resultado = pagoService.registrarPagoFinal(pago);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }
}
package pe.edu.uni.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDto {
    private String serieRegistro; // Relación con REGISTRO
    private double montoPago;     // Monto pagado
}
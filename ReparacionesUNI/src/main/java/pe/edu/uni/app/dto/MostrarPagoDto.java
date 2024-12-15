package pe.edu.uni.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostrarPagoDto {
    private int idPago;
    private String serieRegistro;
    private double montoPago;
    private String tipo;
}

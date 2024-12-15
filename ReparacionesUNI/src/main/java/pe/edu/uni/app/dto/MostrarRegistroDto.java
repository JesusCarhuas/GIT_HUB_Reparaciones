package pe.edu.uni.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostrarRegistroDto {
    private String serieRegistro;
    private int idComputadora;
    private int idTecnico;
    private String descripcionEstado;
    private double adelanto;
    private double total;
    private String fechaEstimadaDeEntrega;
    private String fechaRealDeEntrega;
    private String problemaReportado;
}




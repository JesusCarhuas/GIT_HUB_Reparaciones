package pe.edu.uni.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizacionRegistroDto {
	
	private String serieRegistro;
	private double importe ;
	private double impuesto ;
	private double total;
	private String fechaRealEntrega;
	private String Problema1;
	private String Problema2;
	private int idTecnico;
}

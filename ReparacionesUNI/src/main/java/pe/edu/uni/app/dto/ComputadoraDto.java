package pe.edu.uni.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputadoraDto {
	private int idCliente;
	private String marca;
	private String numeroSerie;
	private int anioFabricacion;
}

package pe.edu.uni.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsoItemsDto {
	private int idItem;
	private String serieRegistro;
	private double costoUnitario;
	private int cantidad;
	private double monto;
}

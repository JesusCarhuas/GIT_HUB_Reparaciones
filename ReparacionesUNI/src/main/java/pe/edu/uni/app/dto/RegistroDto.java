package pe.edu.uni.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDto {
	private String SERIERegistro;
	private int idCliente;
	private int idComputadora;
	private int idTecnico;
	private int idEstado;
	private String marca;
	private double adelanto = 0;
	private double importe = 0;
	private double impuesto = 0;
	private double total = 0;
	private int duracionEstimada;
	private String fechaRealEntrega;
	private String problemaReportado;
}
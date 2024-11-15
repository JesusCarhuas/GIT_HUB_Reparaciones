package pe.edu.uni.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String dni;
	private String direccion;
	private String telefono;
	private String email;
}

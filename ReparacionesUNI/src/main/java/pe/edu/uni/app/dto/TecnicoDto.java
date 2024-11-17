package pe.edu.uni.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoDto {

	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String dni;
	private String direccion;
	private String telefono;
	private String email;
	private String contrasena;
}

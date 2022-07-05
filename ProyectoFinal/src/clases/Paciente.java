package clases;

import java.time.LocalDate;
import java.util.Date;

public class Paciente extends Persona {

	public Paciente(int dni, String nombre, String apellido, LocalDate fechanacimiento, String numero, String correo) {
		super(dni, nombre, apellido, fechanacimiento, numero, correo);
		
	}
	
	public void iniciarSesion() {}
	
	public void registrarse() {}
	
	public void crearTurno() {}
	
	public void eliminarTurno() {}
	
	public void verFicha() {}
	
}

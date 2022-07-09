package clases;

import java.time.LocalDate;

public class Paciente extends Persona {

	public Paciente(int dni, String nombre, String apellido, LocalDate fechanacimiento, String numero, String correo) {
		super(dni, nombre, apellido, fechanacimiento, numero, correo);
		
	}
	
	public Paciente(Persona persona) {
		super(persona.getDni(),
				persona.getNombre(),
				persona.getApellido(),
				persona.getFechaNacimiento(),
				persona.getNumero(),
				persona.getCorreo());
	}
	
	public void iniciarSesion() {}
	
	public void registrarse() {}
	
	public void crearTurno() {}
	
	public void eliminarTurno() {}
	
	public void verFicha() {}
	
}

package clases;

import java.time.LocalDate;

public class Administrativo extends Persona {

	public Administrativo(int dni, String nombre, String apellido, LocalDate date, String numero, String correo) {
		super(dni, nombre, apellido, date, numero, correo);
		
	}

	public void verFichas() {}
	
	public void cargarPacienteEmergencia() {}
	
	public void getInformeMensual() {}
}

package clases;

import java.time.LocalDate;

public class Doctor extends Persona {
	
	private String especialidad;
	private String sector;
	

	public Doctor(int dni, String nombre, String apellido, LocalDate fechanacimiento, String numero, String correo, String especialidad, String sector) {
		super(dni, nombre, apellido, fechanacimiento, numero, correo);
		this.especialidad = especialidad;
		this.sector = sector;
	}
	

	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}


	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}

	
}

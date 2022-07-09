package clases;

import java.time.LocalDate;
import java.time.Period;

public class Persona {
	
	private int dni;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String numero;
	private String correo;
//	private String password;
	
	public Persona(int dni, String nombre, String apellido, LocalDate fechanacimiento, String numero, String correo) {
		this.setDni(dni);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setFechaNacimiento(fechanacimiento);
		this.setNumero(numero);
		this.setCorreo(correo);
	}
	
	public Persona() {
		
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public int getEdad() {
		return Period.between(fechaNacimiento, LocalDate.now()).getYears();
	}

	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
}

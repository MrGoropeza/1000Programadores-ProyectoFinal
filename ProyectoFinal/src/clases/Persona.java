package clases;

import java.util.Date;

public class Persona {
	
	private String dni;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String numero;
	private String correo;
//	private String password;
	
	public Persona(String dni, String nombre, String apellido, Date fechanacimiento, String numero, String correo) {
		this.setDni(dni);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setFechaNacimiento(fechanacimiento);
		this.setNumero(numero);
		this.setCorreo(correo);
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
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

	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

package clases;

import java.util.Date;

public class Tratamiento {
	
	private String descripcion;
	private Date fecha;
	private Doctor doctor;
	private Paciente paciente;
	private Boolean esEmergencia;
	
	public Tratamiento(String descripcion, Date fecha, Doctor doctor, Paciente paciente, Boolean esEmergencia) {
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.doctor = doctor;
		this.paciente = paciente;
		this.esEmergencia = esEmergencia;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
	public Boolean getEsEmergencia() {
		return esEmergencia;
	}
	public void setEsEmergencia(Boolean esEmergencia) {
		this.esEmergencia = esEmergencia;
	}
}

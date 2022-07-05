package clases;

import java.time.LocalDate;

public class Turno {
	private LocalDate fechaInicio;
	private Integer duracionMinutos;
	private Paciente paciente;
	private Doctor doctor;
	
	public Turno(LocalDate fechaInicio, Integer duracionMinutos, Paciente paciente, Doctor doctor) {
		this.setFechaInicio(fechaInicio);
		this.setDuracionMinutos(duracionMinutos);
		this.setPaciente(paciente);
		this.setDoctor(doctor);
	}
	
	public Turno() {
		
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	
	public Integer getDuracionMinutos() {
		return duracionMinutos;
	}
	public void setDuracionMinutos(Integer duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}

package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Turno {
	private int id;
	private LocalDate fechaInicio;
	private int slot;
	private Paciente paciente;
	private Doctor doctor;
	
	public Turno(LocalDate fechaInicio, int slot, Paciente paciente, Doctor doctor, int id) {
		this.setId(id);
		this.setFechaInicio(fechaInicio);
		this.setSlot(slot);
		this.setPaciente(paciente);
		this.setDoctor(doctor);
	}
	
	public Turno(LocalDate fechaInicio, int slot, Paciente paciente, Doctor doctor) {
		this.setFechaInicio(fechaInicio);
		this.setSlot(slot);
		this.setPaciente(paciente);
		this.setDoctor(doctor);
	}
	
	public Turno() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public static String horaFinFromSlot(int slot) {
		String resultado = "";
		switch (slot) {
		case 0:
			resultado = "09:30";
			break;

		case 1:
			resultado = "10:00";
			break;

		case 2:
			resultado = "10:30";
			break;

		case 3:
			resultado = "11:00";
			break;

		case 4:
			resultado = "11:30";
			break;

		case 5:
			resultado = "12:00";
			break;

		case 6:
			resultado = "12:30";
			break;

		case 7:
			resultado = "13:00";
			break;

		case 8:
			resultado = "14:30";
			break;

		case 9:
			resultado = "15:00";
			break;

		case 10:
			resultado = "15:30";
			break;
			
		case 11:
			resultado = "16:00";
			break;
			
		case 12:
			resultado = "16:30";
			break;
			
		case 13:
			resultado = "17:00";
			break;
			
		case 14:
			resultado = "17:30";
			break;
			
		case 15:
			resultado = "18:00";
			break;
		}
		return resultado;
	}
	public static String horaInicioFromSlot(int slot) {
		String resultado = "";
		switch (slot) {
		case 0:
			resultado = "09:00";
			break;

		case 1:
			resultado = "09:30";
			break;

		case 2:
			resultado = "10:00";
			break;

		case 3:
			resultado = "10:30";
			break;

		case 4:
			resultado = "11:00";
			break;

		case 5:
			resultado = "11:30";
			break;

		case 6:
			resultado = "12:00";
			break;

		case 7:
			resultado = "12:30";
			break;

		case 8:
			resultado = "14:00";
			break;

		case 9:
			resultado = "14:30";
			break;

		case 10:
			resultado = "15:00";
			break;
			
		case 11:
			resultado = "15:30";
			break;
			
		case 12:
			resultado = "16:00";
			break;
			
		case 13:
			resultado = "16:30";
			break;
			
		case 14:
			resultado = "17:00";
			break;
			
		case 15:
			resultado = "17:30";
			break;
		}
		return resultado;
	}

	public static int getSlotFromDateTime(LocalDateTime tiempo) {
		if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 9, 0))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 9, 30))) {
			return 0;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 9, 30))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 10, 0))) {
			return 1;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 10, 0))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 10, 30))) {
			return 2;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 10, 30))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 11, 0))) {
			return 3;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 11, 0))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 11, 30))) {
			return 4;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 11, 30))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 12, 0))) {
			return 5;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 12, 0))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 12, 30))) {
			return 6;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 12, 30))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 13, 0))) {
			return 7;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 14, 0))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 14, 30))) {
			return 8;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 14, 30))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 15, 0))) {
			return 9;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 15, 0))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 15, 30))) {
			return 10;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 15, 30))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 16, 0))) {
			return 11;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 16, 0))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 16, 30))) {
			return 12;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 16, 30))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 17, 0))) {
			return 13;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 17, 0))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 17, 30))) {
			return 14;
		}else if(tiempo.isAfter(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 17, 30))
			&& tiempo.isBefore(LocalDateTime.of(tiempo.getYear(), tiempo.getMonth(), tiempo.getDayOfMonth(), 18, 0))) {
			return 15;
		}else {
			return -1;
		}
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	
	public int getSlot() {
		return slot;
	}
	public void setSlot(int slot) {
		this.slot = slot;
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

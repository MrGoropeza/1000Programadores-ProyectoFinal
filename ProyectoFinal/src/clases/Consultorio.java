package clases;

import java.util.ArrayList;

public class Consultorio {
	public ArrayList<Doctor> doctores;
	public ArrayList<String> sectores;
	public ArrayList<Administrativo> administrativos;
	public ArrayList<Paciente> pacientes;
	
	public Consultorio() {
		new ConexionBDD();
		this.administrativos = ConexionBDD.getAdministrativos();
		this.doctores = ConexionBDD.getDoctores();
		this.sectores = ConexionBDD.getSectores();
	}
	
	
	public void listarAdministrativos() {
		int numero = 1;
		for (Administrativo administrativo : administrativos) {
			System.out.println("Administrativo #" + numero + ":");
			numero++;
			System.out.println(
					"DNI:" + administrativo.getDni() + ", " +
					"Nombre: " + administrativo.getNombre() + ", " +
					"Apellido: " + administrativo.getApellido()
			);
		}
	}
	
	public void listarDoctores() {
		int numero = 1;
		for (Doctor doctor : doctores) {
			System.out.println("Doctor #" + numero + ":");
			numero++;
			System.out.println(
					"DNI:" + doctor.getDni() + ", " +
					"Nombre: " + doctor.getNombre() + ", " +
					"Apellido: " + doctor.getApellido() + ", " +
					"Especialidad: " + doctor.getEspecialidad() + ", " +
					"Sector: " + doctor.getSector()
			);
			
		}
	}
	
	public void listarSectores() {
		int numero = 1;
		for (String sector : sectores) {
			System.out.println("Sector #" + numero + ":");
			numero++;
			System.out.println("Sector: " + sector);
		}
	}
	
	public static void main(String[] args) {
		Consultorio drMuelas = new Consultorio();
		drMuelas.listarAdministrativos();
		drMuelas.listarDoctores();
		drMuelas.listarSectores();
	}
}

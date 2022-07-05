package interfaz_usuario;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;

import clases.Consultorio;
import clases.Doctor;
import clases.Paciente;
import clases.Persona;
import clases.Turno;

public class Menu {
	
	public static Scanner input = new Scanner(System.in);
	
	public static Consultorio consultorio;
	
	public static Persona logeado;
	
	public static int bienvenidaInicial() {
		int rta = -1;

		System.out.println(UIStrings.inicio());
		System.out.print("Opción: ");
		try {
			rta = input.nextInt();
			switch (rta) {
			case 1:
				System.out.println("Inicio sesión");
				return rta;
				
			case 2:
				System.out.println("Registrarse");
				return rta;
				
			case 0:
				System.out.println("Fin");
				return rta;
				
			default:
				System.out.println("Opción no válida.");
				return -1;
			}
		} catch (Exception e) {
			System.out.println("Opción no válida.");
			return -1;
		}
	}
	
	public static void inicioSesion() {
		// TODO: inicio de sesion
	}
	public static void registrarse() {
		// TODO: registro
	}
	
	public static int menuPacienteLogeado() {
		int rta = -1;

		System.out.println(UIStrings.menuPaciente(logeado.getNombre() + " " + logeado.getApellido()));
		System.out.print("Opción: ");
		try {
			rta = input.nextInt();
			switch (rta) {
			case 1:
				return rta;
				
			case 2:
				return rta;
			
			case 3:
				return rta;
				
			default:
				System.out.println("Opción no válida.");
				return -1;
			}
		} catch (Exception e) {
			System.out.println("Opción no válida.");
			return -1;
		}
	}
	
	// funciones paciente
	public static boolean reservarTurno() {
		boolean rta = false;
		
		// Seleccion de Fecha del Turno
		LocalDate fecha = null;
		boolean primeraVez = true;
		while (fecha == null) {
			if(!primeraVez) {
				System.out.print("Ingrese fecha para el turno (dd/MM/yyyy): ");
				fecha = ingresoFecha();
				if(fecha != null) {
					if(fecha.isBefore(LocalDate.now())) {
						fecha = null;
						System.out.println("No podés elegir una fecha antes que hoy.");
					}else if(fecha.isAfter(LocalDate.now().plusMonths(3))) {
						fecha = null;
						System.out.println("No podés reservar un turno con más de 3 meses de anticipación.");
					}
				}
				
			}else {
				input.nextLine();
				primeraVez = false;
			}
		}
		// Fin Seleccion de fecha
		
		// Seleccion de Doctor
		String sector = "";
		if(logeado.getEdad() >= 3 && logeado.getEdad() <= 12) {
			sector = "menores";
		}else if(logeado.getEdad() >= 13) {
			sector = "mayores";
		}
		
		ArrayList<Doctor> doctoresDisponibles = new ArrayList<Doctor>();
		for (Doctor doctor : consultorio.doctores) {
			if(doctor.getSector().equalsIgnoreCase(sector)) {
				doctoresDisponibles.add(doctor);
			}
		}
		
		Doctor doctorElegido = null;
		
		while(doctorElegido == null) {
			int indexElegido = selectDoctor(doctoresDisponibles);
			if(indexElegido == -1) {
				System.out.println("Opción no válida.");
			}else {
				doctorElegido = doctoresDisponibles.get(indexElegido);
			}
		}
		// Fin Seleccion de Doctor
		System.out.println("Fecha ingresada: " + fecha.toString());
		System.out.println("Doctor elegido: "
				+ doctorElegido.getApellido() + ", "
				+ doctorElegido.getNombre());
		return rta;
	}
	
	public static LocalDate ingresoFecha() {
		LocalDate ingresada = null;
		String entrada = input.nextLine();
		try {
			String dateFormat = "dd/MM/yyyy";
		    ingresada = new SimpleDateFormat(dateFormat)
		    		.parse(entrada)
		    		.toInstant()
		    		.atZone(ZoneId.systemDefault())
		    		.toLocalDate();

		} catch (Exception e) {
			System.out.println("Fecha no válida.");

		}
		return ingresada;
	}
	public static int selectDoctor(ArrayList<Doctor> doctores) {
		int rta = -1;
		System.out.println(UIStrings.doctoresDisponibles(doctores));
		System.out.print("Opción: ");
		try {
			rta = input.nextInt();
			doctores.get(rta-1);
			rta -= 1;
		} catch (Exception e) {
			System.out.println("Opción no válida.");
			input.nextLine();
			return -1;
		}
		return rta;
	}
	
	public static void startApp() {
		
		// iniciar consultorio
		consultorio = new Consultorio();
		logeado = new Paciente(43337890,
				"Gonzalo",
				"Oropeza",
				LocalDate.of(2001, 3, 8), 
				"3874047262", 
				"goropeza8@gmail.com");
		
		// pantalla inicial
		int seleccion;
		do {
			seleccion  = bienvenidaInicial();
		} while (seleccion == -1);
		
		switch (seleccion) {
		case 1:
			inicioSesion();
			break;
			
		case 2:
			registrarse();
			break;
		}
		
		boolean isAdmin = false;
		// menu paciente
		if(!isAdmin) {
			
			do {
				seleccion  = menuPacienteLogeado();
			} while (seleccion == -1);
			
			switch (seleccion) {
			case 1:
				reservarTurno();
				break;
				
			case 2:
				
				break;
				
			case 3:
				
				break;
			}
		}
	}

	public static void main(String[] args) {
		
		startApp();
	}

}

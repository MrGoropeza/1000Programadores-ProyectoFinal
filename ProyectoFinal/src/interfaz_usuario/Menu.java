package interfaz_usuario;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import clases.ConexionBDD;
import clases.Consultorio;
import clases.Doctor;
import clases.Paciente;
import clases.Persona;
import clases.Turno;

public class Menu {
	
	public static Scanner input = new Scanner(System.in);
	
	public static Consultorio consultorio;
	
	public static Persona logeado;
	
	private static boolean isAdmin;
	
	public static int bienvenidaInicial() {
		int rta = -1;

		System.out.println(UIStrings.inicio());
		System.out.print("Opción: ");
		try {
			rta = input.nextInt();
			switch (rta) {
			case 1:
				return rta;
				
			case 2:
				System.out.println("Registrarse: Función no implementada");
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
		int rta = -1;
		
		while(rta == -1) {
			System.out.println(UIStrings.inicioSesion());
			System.out.print("Opción: ");
			try {
				rta = input.nextInt();
				if(rta != 1 && rta != 2 && rta != 0) {
					System.out.println("Opción no válida.");
					rta = -1;
				}
			} catch (Exception e) {
				System.out.println("Opción no válida.");
				rta = -1;
			}
		}
		
		if(rta == 0) {
			return;
		}
		
		
		int dni = -1;
		while (dni == -1) {
			System.out.print("Número de DNI (0 para volver): ");
			try {
				dni = input.nextInt();
				if(dni == 0) {
					return;
				}
				
				
				Persona usuario;
				if(rta == 1) {
					usuario = ConexionBDD.getAdministrativoFromDNI(dni);
					isAdmin = true;
				}else if (rta == 2) {
					usuario = ConexionBDD.getPacienteFromDNI(dni);
					isAdmin = false;
				}else {
					usuario = null;
					isAdmin = false;
				}
				
				if(usuario != null) {
					logeado = usuario;
				}else {
					dni = -1;
					System.out.println("DNI no registrado en la base de datos.");
					
				}
				
			}catch (InputMismatchException e) {
				System.out.println("DNI no válido.");
				input.nextLine();
				dni = -1;
			}
		}
		
		
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
				
			case 0:
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
		
		
		boolean turnoConfirmado = false;
		
		while (!turnoConfirmado) {
		
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
					}else if(fecha.getYear() == LocalDate.now().getYear()
						&& fecha.getDayOfMonth() == LocalDate.now().getDayOfMonth()
						&& fecha.getMonthValue() == LocalDate.now().getMonthValue()) {
						LocalDateTime ahora = LocalDateTime.now();
						if(ahora.isAfter(LocalDateTime.of(ahora.getYear(), ahora.getMonthValue(), ahora.getDayOfMonth(), 17, 30))) {
							fecha = null;
							System.out.println("No hay turnos disponibles para hoy.");
						}
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
		
		
		
		// Inicio seleccion turnos disponibles
		
		ArrayList<Turno> turnosOcupados = ConexionBDD.getTurnosDoctor(doctorElegido, fecha);
		
		ArrayList<Integer> slotsLibres = new ArrayList<Integer>();
		for (Integer i = 0; i < 16; i++) {
			slotsLibres.add(i);
		}
		for (Turno turno : turnosOcupados) {
			slotsLibres.remove(slotsLibres.indexOf(turno.getSlot()));
		}
		
		Integer slotOcurriendoAhora = Turno.getSlotFromDateTime(LocalDateTime.now());
		
		if(slotOcurriendoAhora != -1
			&& fecha.getYear() == LocalDate.now().getYear()
			&& fecha.getDayOfMonth() == LocalDate.now().getDayOfMonth()
			&& fecha.getMonthValue() == LocalDate.now().getMonthValue()
			) {
			slotsLibres = new ArrayList<Integer>(
					slotsLibres.subList(slotsLibres.indexOf(slotOcurriendoAhora)+1,
					slotsLibres.size())
				);
		}
		
		Turno turnoElegido = null;
		
		while(turnoElegido == null) {
			int indexElegido = selectTurno(slotsLibres);
			if(indexElegido == -1) {
				System.out.println("Opción no válida.");
			}else {
				System.out.println("Slot elegido: " + slotsLibres.get(indexElegido));
				turnoElegido = new Turno(
						fecha,
						slotsLibres.get(indexElegido),
						new Paciente(logeado),
						doctorElegido);
			}
		}
				
		// Fin seleccion turnos disponibles
		
		// Inicio confirmacion

		int eleccionFinal = -1;
		
		while(eleccionFinal == -1) {
			eleccionFinal = confirmTurno(turnoElegido);
		}
		
		if(eleccionFinal == 0) {
			rta = false;
			turnoConfirmado = true;
		}else if(eleccionFinal == 1) {
			
			ConexionBDD.addTurno(turnoElegido);
			
			rta = true;
			turnoConfirmado = true;
		}else {
			rta = false;
			turnoConfirmado = false;
		}
		
		
		// Fin confirmacion
		
		}
		
		
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
	public static int selectTurno(ArrayList<Integer> turnosLibres) {
		int rta = -1;
		System.out.println(UIStrings.turnosDisponibles(turnosLibres));
		System.out.print("Opción: ");
		try {
			rta = input.nextInt();
			turnosLibres.get(rta-1);
			rta -= 1;
		} catch (Exception e) {
			System.out.println("Opción no válida.");
			input.nextLine();
			return -1;
		}
		return rta;
	}
	public static int confirmTurno(Turno turno) {
		int rta = -1;
		System.out.println(UIStrings.confirmarTurno(turno));
		System.out.print("Opción: ");
		try {
			rta = input.nextInt();
			switch (rta) {
			case 1:
				return rta;
				
			case 2:
				return rta;
			
			case 0:
				return rta;
				
			default:
				System.out.println("Opción no válida.");
				return -1;
			}
		} catch (Exception e) {
			System.out.println("Opción no válida.");
			input.nextLine();
			return -1;
		}
	}
	
	public static void eliminarTurno() {
		ArrayList<Turno> turnos = ConexionBDD.getTurnosPaciente(new Paciente(logeado));
		
		ArrayList<Turno> aux = new ArrayList<>(turnos);
		
		for (Turno turno : aux) {
			//System.out.println(turno.getFechaInicio().toString() + " vs " + LocalDate.now().plusDays(2));
			if(turno.getFechaInicio().isBefore(LocalDate.now().plusDays(1))) {
				/*System.out.println("Turno eliminado: " 
					+ turno.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
					+ ", " + Turno.horaInicioFromSlot(turno.getSlot())
					+ " - " + Turno.horaFinFromSlot(turno.getSlot())
					+ ", Doctor: " + turno.getDoctor().getApellido()
				    + ", " + turno.getDoctor().getNombre());*/
				Turno basura = turno;
				turnos.remove(basura);
			}
		}
		
		if(turnos.size() == 0) {
			System.out.println("No tenés turnos reservados para eliminar\n"
					+ "(recordá que solo se pueden eliminar con un día de anticipación). ");
			return;
		}else {
			int rta = -1;
			while(rta == -1) {
				System.out.println(UIStrings.turnosReservados(turnos));
				System.out.print("Opción: ");
				try {
					rta = input.nextInt();
					if(rta == turnos.size()+1) {
						return;
					}
					Turno elegido = turnos.get(rta-1);
					
					// Inicio confirmacion

					int eleccionFinal = -1;
					
					while(eleccionFinal == -1) {
						System.out.println(UIStrings.confirmarEliminarTurno(elegido));
						System.out.print("Opción: ");
						try {
							eleccionFinal = input.nextInt();
							switch (eleccionFinal) {
							case 1:
								ConexionBDD.removeTurno(elegido);
								
							case 2:
								rta = -1;
							
							case 0:
								return;
								
							default:
								System.out.println("Opción no válida.");
								eleccionFinal = -1;
							}
						} catch (Exception e) {
							System.out.println("Opción no válida.");
							input.nextLine();
							eleccionFinal = -1;
						}
					}
					
					// Fin confirmacion
					
					
					rta -= 1;
				} catch (Exception e) {
					System.out.println("Opción no válida.");
					input.nextLine();
					rta = -1;
				}
			}
		}
	}
	
	public static int menuAdminLogeado() {
		int rta = -1;

		System.out.println(
				UIStrings.menuAdmin(logeado.getNombre() + " " + logeado.getApellido()));
		System.out.print("Opción: ");
		try {
			rta = input.nextInt();
			System.out.println("opcion elegida: " + rta);
			if(rta >= 0 && rta <= 3) {
				return rta;
			}else {
				System.out.println("Opción no válida.");
				return -1;
			}
		} catch (Exception e) {
			System.out.println("Opción no válida.");
			return -1;
		}
	}
	
	public static void startApp() {
		
		// iniciar consultorio
		consultorio = new Consultorio();
		isAdmin = false;
		
		boolean ejecutandose = true;
		
		// inicio ciclo de todo el programa
		while (ejecutandose) {
			// pantalla inicial
			while (logeado == null) {
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
					
				case 0:
					return;
				}
			}
			// fin pantalla inicial
			
			
			int seleccion = -1;
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
					eliminarTurno();
					break;
					
				case 3:
					System.out.println("Ver mis tratamientos: función no implementada");
					break;
					
				case 0:
					logeado = null;
					break;
				}
			}else {
				do {
					seleccion  = menuAdminLogeado();
				} while (seleccion == -1);
				
				switch (seleccion) {
				case 1:
					System.out.println("Cargar paciente en emergencia: función no implementada");
					break;
					
				case 2:
					System.out.println("Ver ficha de paciente: función no implementada");
					break;
					
				case 3:
					System.out.println("Ver informe de pacientes atendidos función no implementada");
					break;
					
				case 0:
					logeado = null;
					break;
				}
			}
		}
		
	}


	public static void main(String[] args) {
		
		startApp();
	}

}

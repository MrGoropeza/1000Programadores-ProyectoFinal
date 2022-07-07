package interfaz_usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.Label;
import com.indvd00m.ascii.render.elements.Rectangle;
import com.indvd00m.ascii.render.elements.Table;
import com.indvd00m.ascii.render.elements.Text;

import clases.*;

public class UIStrings {
	
	public static String inicio() {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		builder.width(80).height(8);
		builder.element(new Rectangle());
		builder.element(new Text("Bienvenido al Sistema de Turnos de Dr. Muelas", 4, 0, 50, 1));
		builder.element(new Text("Seleccione una opción: ", 4, 2, 50, 1));
		builder.element(new Text("1) Iniciar Sesión",6,3,50,1));
		builder.element(new Text("2) Registrarse",6,4,50,1));
		builder.element(new Text("0) Salir",6,5,50,1));
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	
	public static String inicioSesion() {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		builder.width(80).height(8);
		builder.element(new Rectangle());
		builder.element(new Text("Bienvenido al Sistema de Turnos de Dr. Muelas", 4, 0, 50, 1));
		builder.element(new Text("Seleccione una opción: ", 4, 2, 50, 1));
		builder.element(new Text("1) Como Administrador",6,3,50,1));
		builder.element(new Text("2) Como Paciente",6,4,50,1));
		builder.element(new Text("0) Volver",6,5,50,1));
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	
	public static String menuPaciente(String nombrePaciente) {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		builder.width(80).height(9);
		builder.element(new Rectangle());
		builder.element(new Text("Bienvenido, " + nombrePaciente, 4, 0, 50, 1));
		builder.element(new Text("Seleccione una opción: ", 4, 2, 50, 1));
		builder.element(new Text("1) Reservar un Turno",6,3,50,1));
		builder.element(new Text("2) Eliminar un Turno",6,4,50,1));
		builder.element(new Text("3) Ver mis tratamientos",6,5,50,1));
		builder.element(new Text("0) Salir",6,6,50,1));
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}

	public static String tablaTratamientos(ArrayList<Tratamiento> tratamientos) {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		int height = 3 + tratamientos.size()*5;
		builder.width(85).height(height);
		
		Collections.sort(tratamientos, Comparator.comparing(Tratamiento::getFecha));
		
		Table tabla = new Table(3, tratamientos.size()+1);
		tabla.setElement(1, 1, new Label("Fecha"), true);
		tabla.setElement(2, 1, new Label("Doctor"), true);
		tabla.setElement(3, 1, new Label("Descripción"), true);
		
		int fila = 2;
		
		for (Tratamiento tratamiento : tratamientos) {
			tabla.setElement(1, fila, new Label(tratamiento.getFecha().toString()));
			tabla.setElement(2, fila, new Label(tratamiento.getDoctor().getApellido()));
			tabla.setElement(3, fila, new Text(tratamiento.getDescripcion(), 0, 0, 26, 3));
			fila++;
		}
		
		builder.element(tabla);
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	
	public static String doctoresDisponibles(ArrayList<Doctor> doctores) {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		int height = 5 + doctores.size();
		builder.width(80).height(height);
		builder.element(new Rectangle());
		builder.element(new Text("Doctores Disponibles", 4, 0, 50, 1));
		builder.element(new Text("Seleccione una opción: ", 4, 2, 50, 1));
		
		int opcion = 1;
		for (Doctor doctor : doctores) {
			builder.element(new Text(opcion + ") " + doctor.getApellido() + ", " + doctor.getNombre(),6,opcion+2,50,1));
			opcion++;
		}
		
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	
	public static String turnosDisponibles(ArrayList<Integer> turnosLibres) {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		int tamanioLineas = turnosLibres.size()/2;
		int height = 5;
		if(turnosLibres.size()%2 == 0) {
			height += tamanioLineas;
		}else {
			height += tamanioLineas + 1;
		}
		builder.width(80).height(height);
		builder.element(new Rectangle());
		builder.element(new Text("Turnos Disponibles", 4, 0, 50, 1));
		builder.element(new Text("Seleccione una opción: ", 4, 2, 50, 1));
		
		if(turnosLibres.size()%2 == 0) {
			for (int i = 0; i < turnosLibres.size(); i++) {
				if(i < tamanioLineas) {
					builder.element(new Text((i + 1) + ") " + Turno.horaInicioFromSlot(turnosLibres.get(i)),
							6, i+3, 50, 1));
				}else {
					builder.element(new Text((i + 1) + ") " + Turno.horaInicioFromSlot(turnosLibres.get(i)),
							25, i+3-tamanioLineas, 50, 1));
					
				}
			}
		}else {
			for (int i = 0; i < turnosLibres.size(); i++) {
				if(i <= tamanioLineas) {
					builder.element(new Text((i + 1) + ") " + Turno.horaInicioFromSlot(turnosLibres.get(i)),
							6, i+3, 50, 1));
				}else {
					builder.element(new Text((i + 1) + ") " + Turno.horaInicioFromSlot(turnosLibres.get(i)),
							25, i+2-tamanioLineas, 50, 1));
					
				}
			}
		}
		
		
		
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	
	public static String confirmarTurno(Turno turno) {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		builder.width(80).height(9);
		builder.element(new Rectangle());
		builder.element(new Text("¿Estás seguro de tu elección?", 4, 0, 50, 1));
		
		builder.element(new Text("Fecha: " + turno.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				, 4, 2, 50, 1));
		builder.element(new Text("Doctor: " + turno.getDoctor().getApellido() 
				+ ", " + turno.getDoctor().getNombre(),
				4, 3, 50, 1));
		builder.element(new Text("Horario: " + Turno.horaInicioFromSlot(turno.getSlot())
				+ " - " + Turno.horaFinFromSlot(turno.getSlot()),
				4, 4, 50, 1));
		
		builder.element(new Text("Seleccione una opción: ", 4, 6, 50, 1));
		builder.element(new Text("1) Sí",6,7,50,1));
		builder.element(new Text("2) No",25,7,50,1));
		builder.element(new Text("0) Cancelar",44,7,50,1));
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	
	public static String turnosReservados(ArrayList<Turno> turnos) {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		int height = 5 + turnos.size();
		builder.width(80).height(height);
		builder.element(new Rectangle());
		builder.element(new Text("Tus turnos reservados", 4, 0, 50, 1));
		builder.element(new Text("Seleccione una opción: ", 4, 2, 50, 1));
		
		int opcion = 1;
		for (Turno turno : turnos) {
			builder.element(new Text(opcion + ") "
					+ turno.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
					+ ", " + Turno.horaInicioFromSlot(turno.getSlot())
					+ " - " + Turno.horaFinFromSlot(turno.getSlot())
					+ ", Doctor: " + turno.getDoctor().getApellido()
				    + ", " + turno.getDoctor().getNombre()
					,6,opcion+2,72,1));
			opcion++;
		}
		builder.element(new Text(opcion + ") Volver",6,opcion+2,74,1));
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	
	public static String confirmarEliminarTurno(Turno turno) {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		builder.width(80).height(9);
		builder.element(new Rectangle());
		builder.element(new Text("¿Estás de eliminar este turno?", 4, 0, 50, 1));
		
		builder.element(new Text("Fecha: " + turno.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				, 4, 2, 50, 1));
		builder.element(new Text("Doctor: " + turno.getDoctor().getApellido() 
				+ ", " + turno.getDoctor().getNombre(),
				4, 3, 50, 1));
		builder.element(new Text("Horario: " + Turno.horaInicioFromSlot(turno.getSlot())
				+ " - " + Turno.horaFinFromSlot(turno.getSlot()),
				4, 4, 50, 1));
		
		builder.element(new Text("Seleccione una opción: ", 4, 6, 50, 1));
		builder.element(new Text("1) Sí",6,7,50,1));
		builder.element(new Text("2) No",25,7,50,1));
		builder.element(new Text("0) Cancelar",44,7,50,1));
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}

	public static String menuAdmin(String nombreAdmin) {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		builder.width(80).height(9);
		builder.element(new Rectangle());
		builder.element(new Text("Bienvenido, " + nombreAdmin, 4, 0, 50, 1));
		builder.element(new Text("Seleccione una opción: ", 4, 2, 50, 1));
		builder.element(new Text("1) Cargar paciente para emergencia",6,3,50,1));
		builder.element(new Text("2) Ver ficha de un paciente",6,4,50,1));
		builder.element(new Text("3) Ver informe de pacientes atendidos",6,5,50,1));
		builder.element(new Text("0) Salir",6,6,50,1));
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	
	public static void main(String[] args) {
		System.out.println(inicio());
		System.out.println(menuPaciente("Gonzalo Oropeza"));
		
		Paciente yo = new Paciente(43337890,
				"Gonzalo", 
				"Oropeza",
				LocalDate.of(2001, 3, 8), 
				"3874047262",
				"goropeza8@gmail.com");
		
		Doctor doctor = new Doctor(22333444,
				"Juan",
				"Perez",
				LocalDate.of(1990, 5, 6),
				"3874047262",
				"doctor@correo.com", 
				"Odontólogo",
				"Mayores");
		
		Tratamiento t1 = new Tratamiento("Muela de Juicio,t1, una descripcion re contra larga",
				LocalDate.of(2022, 6, 30), doctor, yo, false);
		Tratamiento t2 = new Tratamiento("Muela de Juicio,t2",
				LocalDate.of(2022, 6, 30), doctor, yo, false);
		Tratamiento t3 = new Tratamiento("Muela de Juicio,t3",
				LocalDate.of(2022, 6, 30), doctor, yo, false);
		//Tratamiento t4 = new Tratamiento("Muela de Juicio,t4",
		//		LocalDate.of(2022, 6, 30), doctor, yo, false);
		
		ArrayList<Tratamiento> tratamientos = new ArrayList<Tratamiento>();
		tratamientos.add(t3);
		tratamientos.add(t2);
		tratamientos.add(t1);
		//tratamientos.add(t4);
		
		//System.out.println(tablaTratamientos(tratamientos));
		
		System.out.println(menuAdmin("Guillermo Mogro"));
		
	}
	
	

}

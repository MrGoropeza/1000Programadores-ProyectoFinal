package interfaz_usuario;

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
		builder.element(new Text("3) Como Doctor",6,5,50,1));
		builder.element(new Text("0) Volver",6,6,50,1));
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
		builder.width(76).height(height);
		builder.element(new Rectangle());
		
		Collections.sort(tratamientos, Comparator.comparing(Tratamiento::getFecha));
		
		Table tabla = new Table(3, tratamientos.size()+1);
		tabla.setElement(1, 1, new Label("Fecha"), true);
		tabla.setElement(2, 1, new Label("Doctor"), true);
		tabla.setElement(3, 1, new Label("Descripción"), true);
		
		int fila = 2;
		
		for (Tratamiento tratamiento : tratamientos) {
			tabla.setElement(1, fila,
					new Label(tratamiento.getFecha().toString()));
			tabla.setElement(2, fila,
					new Text(tratamiento.getDoctor().getApellido()
							+ ", " 
							+ tratamiento.getDoctor().getNombre()
							, 0, 0, 26, 3));
			tabla.setElement(3, fila,
					new Text(tratamiento.getDescripcion(), 0, 0, 26, 3));
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
		builder.element(new Text("¿Estás seguro de eliminar este turno?", 4, 0, 50, 1));
		
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
	
	public static String menuDoctor(String nombreDoctor) {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		builder.width(80).height(9);
		builder.element(new Rectangle());
		builder.element(new Text("Bienvenido, " + nombreDoctor, 4, 0, 50, 1));
		builder.element(new Text("Seleccione una opción: ", 4, 2, 50, 1));
		builder.element(new Text("1) Cargar tratamiento de un paciente",6,3,50,1));
		builder.element(new Text("2) Ver ficha de un paciente",6,4,50,1));
		builder.element(new Text("3) Ver agenda de hoy",6,5,50,1));
		builder.element(new Text("0) Salir",6,6,50,1));
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	
	public static String pacientesDisponibles(ArrayList<Paciente> pacientes) {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		int height = 5 + pacientes.size();
		builder.width(80).height(height);
		builder.element(new Rectangle());
		builder.element(new Text("Tus Pacientes", 4, 0, 50, 1));
		builder.element(new Text("Seleccione una opción: ", 4, 2, 50, 1));
		
		int opcion = 1;
		for (Paciente paciente : pacientes) {
			builder.element(new Text(opcion + ") "
					+ paciente.getApellido() + ", "
					+ paciente.getNombre() + ", DNI: "
					+ paciente.getDni()
					,6,opcion+2,50,1));
			opcion++;
		}
		
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	
	public static String selectEmergencia() {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		int height = 5;
		builder.width(80).height(height);
		builder.element(new Rectangle());
		builder.element(new Text("¿Fue una emergencia?", 4, 0, 50, 1));

		builder.element(new Text("Seleccione una opción: ", 4, 2, 50, 1));
		builder.element(new Text("1) Sí",6,3,50,1));
		builder.element(new Text("2) No",25,3,50,1));
		
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	
	public static String confirmarTratamiento(Tratamiento tratamiento) {
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		
		String descripcion = "Descripción: " + tratamiento.getDescripcion();
		int height = 10 + descripcion.length()/72;
		
		builder.width(80).height(height);
		builder.element(new Rectangle());
		builder.element(new Text("¿Estás seguro de tu elección?", 4, 0, 50, 1));
		
		builder.element(new Text("Fecha: " + tratamiento.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				, 4, 2, 50, 1));
		builder.element(new Text("Paciente: " + tratamiento.getPaciente().getApellido() 
				+ ", " + tratamiento.getPaciente().getNombre(),
				4, 3, 50, 1));
		
		if(tratamiento.getEsEmergencia()) {
			builder.element(new Text("¿Fue emergencia?: Sí"
			, 4, 4, 50, 1));
		}else {
			builder.element(new Text("¿Fue emergencia?: No"
			, 4, 4, 50, 1));
		}
		
		if(descripcion.length() < 72) {
			builder.element(new Text("Descripción: " + tratamiento.getDescripcion(),
					4, 5, 50, 1));
			builder.element(new Text("Seleccione una opción: ", 4, 7, 50, 1));
			builder.element(new Text("1) Sí",6,8,50,1));
			builder.element(new Text("2) No",25,8,50,1));
			builder.element(new Text("0) Cancelar",44,8,50,1));
		}else {
			int filasDesc = descripcion.length()/72;
			builder.element(new Text("Descripción: " + tratamiento.getDescripcion(),
					4, 4, 50, filasDesc));
			builder.element(new Text("Seleccione una opción: ", 4, 7 + filasDesc, 50, 1));
			builder.element(new Text("1) Sí",6,8 + filasDesc,50,1));
			builder.element(new Text("2) No",25,8 + filasDesc,50,1));
			builder.element(new Text("0) Cancelar",44,8 + filasDesc,50,1));
		}
		
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		return s;
	}
	

}

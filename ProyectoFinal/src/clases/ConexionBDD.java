package clases;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConexionBDD {
	
	private static final String JBDC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/muelas";
	
	private static final String USER = "basedatos";
	private static final String PASS = "Contra123#";
	
	private static Connection conn;
	
	public ConexionBDD() {
		try {
			Class.forName(JBDC_DRIVER);
			ConexionBDD.conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
		}catch (Exception e) {
			System.out.println("Hubo un error al conectarse a la base de datos:");
			System.out.println(e.getMessage());
		}
		
	}
	
	public static ArrayList<Administrativo> getAdministrativos(){
		ArrayList<Administrativo> resultados = new ArrayList<Administrativo>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from administrativos t1\n"
					+ "inner join personas t2\n"
					+ "where t1.persona_dni = t2.persona_dni;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resultados.add(new Administrativo(
						rs.getInt("persona_dni"),
						rs.getString("persona_nombre"),
						rs.getString("persona_apellido"),
						rs.getDate("persona_fecha_nac").toLocalDate(),
						rs.getString("persona_numero"),
						rs.getString("persona_correo")));
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener administrativos de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultados;		
	}
	
	public static ArrayList<Doctor> getDoctores(){
		ArrayList<Doctor> resultados = new ArrayList<Doctor>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from doctores t1\n"
					+ "inner join personas t2, sectores t3\n"
					+ "where t1.persona_dni = t2.persona_dni\n"
					+ "and t1.sector_id = t3.sector_id;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resultados.add(new Doctor(
						rs.getInt("persona_dni"),
						rs.getString("persona_nombre"),
						rs.getString("persona_apellido"),
						rs.getDate("persona_fecha_nac").toLocalDate(),
						rs.getString("persona_numero"),
						rs.getString("persona_correo"),
						rs.getString("doctor_especialidad"),
						rs.getString("sector_nombre")));
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener doctores de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultados;		
	}
	
	public static ArrayList<String> getSectores(){
		ArrayList<String> resultados = new ArrayList<String>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from sectores";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resultados.add(rs.getString("sector_nombre"));
			}
		} catch (Exception e) {
			System.out.println("Hubo un error al obtener los sectores de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultados;
	}
	
	public static Administrativo getAdministrativoFromDNI(int dni) {
		Administrativo resultado = null;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from administrativos t1\n"
					+ "inner join personas t2\n"
					+ "where t1.persona_dni = t2.persona_dni\n"
					+ "and t2.persona_dni = " + dni + ";";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				resultado = new Administrativo(
						rs.getInt("persona_dni"),
						rs.getString("persona_nombre"),
						rs.getString("persona_apellido"),
						rs.getDate("persona_fecha_nac").toLocalDate(),
						rs.getString("persona_numero"),
						rs.getString("persona_correo"));
			} else {
				System.out.println("Administrativo no encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al buscar la persona en la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	public static Paciente getPacienteFromDNI(int dni) {
		Paciente resultado = null;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from pacientes t1\n"
					+ "inner join personas t2\n"
					+ "where t1.persona_dni = t2.persona_dni\n"
					+ "and t2.persona_dni = " + dni;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resultado = new Paciente(
						rs.getInt("persona_dni"),
						rs.getString("persona_nombre"),
						rs.getString("persona_apellido"),
						rs.getDate("persona_fecha_nac").toLocalDate(),
						rs.getString("persona_numero"),
						rs.getString("persona_correo"));
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener el paciente de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultado;	
	}
	
	public static Paciente getPacienteFromID(int paciente_id) {
		Paciente resultado = null;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from pacientes t1\n"
					+ "inner join personas t2\n"
					+ "where t1.persona_dni = t2.persona_dni\n"
					+ "and t1.paciente_id = " + paciente_id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resultado = new Paciente(
						rs.getInt("persona_dni"),
						rs.getString("persona_nombre"),
						rs.getString("persona_apellido"),
						rs.getDate("persona_fecha_nac").toLocalDate(),
						rs.getString("persona_numero"),
						rs.getString("persona_correo"));
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener el paciente de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultado;	
	}
	
	public static Doctor getDoctorFromID(int doctor_id) {
		Doctor resultado = null;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from doctores t1\n"
					+ "inner join personas t2, sectores t3\n"
					+ "where t1.persona_dni = t2.persona_dni\n"
					+ "and t3.sector_id = t1.sector_id\n"
					+ "and t1.doctor_id = " + doctor_id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resultado = new Doctor(
						rs.getInt("persona_dni"),
						rs.getString("persona_nombre"),
						rs.getString("persona_apellido"),
						rs.getDate("persona_fecha_nac").toLocalDate(),
						rs.getString("persona_numero"),
						rs.getString("persona_correo"),
						rs.getString("doctor_especialidad"),
						rs.getString("sector_nombre"));
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener el paciente de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultado;	
	}
	
	public static ArrayList<Turno> getTurnosDoctor(Doctor doctor, LocalDate fecha){
		ArrayList<Turno> resultados = new ArrayList<Turno>();
		try {
			String sql = "select * from turnos t1 \n"
					+ "inner join doctores t2, personas t3 \n"
					+ "where t3.persona_dni = t2.persona_dni \n"
					+ "and t1.doctor_id = t2.doctor_id \n"
					+ "and t1.turno_fecha = '" + Date.valueOf(fecha).toString() + "'\n"
					+ "and t3.persona_dni = '" + doctor.getDni() + "';";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Paciente paciente = getPacienteFromID(rs.getInt("paciente_id"));
				resultados.add(new Turno(
						fecha,
						rs.getInt("slot"),
						paciente,
						doctor,
						rs.getInt("turno_id")));
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener doctores de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultados;	
	}
	
	public static ArrayList<Turno> getTurnosPaciente(Paciente paciente){
		ArrayList<Turno> resultados = new ArrayList<Turno>();
		try {
			String sql = "select * from turnos t1 \n"
					+ "inner join pacientes t2, personas t3 \n"
					+ "where t3.persona_dni = t2.persona_dni \n"
					+ "and t1.paciente_id = t2.paciente_id \n"
					+ "and t3.persona_dni = '" + paciente.getDni() + "';";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Doctor doctor = getDoctorFromID(rs.getInt("doctor_id"));
				resultados.add(new Turno(
						rs.getDate("turno_fecha").toLocalDate(),
						rs.getInt("slot"),
						paciente,
						doctor,
						rs.getInt("turno_id")));
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener doctores de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultados;	
	}
	
	public static void removeTurno(Turno turno) {
		try {
			PreparedStatement stmt = conn.prepareStatement(
					"delete from muelas.turnos "
					+ "where turno_id = " + turno.getId()
					);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("No se pudo eliminar el turno: ");
			System.out.println(e.getMessage());
		}
	}
	
	public static int getPacienteID(int paciente_dni) {
		int resultado = -1;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from pacientes t1\n"
					+ "inner join personas t2\n"
					+ "where t1.persona_dni = t2.persona_dni\n"
					+ "and t2.persona_dni = " + paciente_dni;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resultado = rs.getInt("paciente_id");
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener el paciente de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	public static int getDoctorID(int doctor_dni) {
		int resultado = -1;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from doctores t1\n"
					+ "inner join personas t2\n"
					+ "where t1.persona_dni = t2.persona_dni\n"
					+ "and t2.persona_dni = " + doctor_dni;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resultado = rs.getInt("doctor_id");
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener el paciente de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	public static boolean addTurno(Turno turno) {
		boolean rta = false;
		try {
			//insercion de persona en la bdd
			PreparedStatement stmt = conn.prepareStatement(
					"insert into muelas.turnos "
					+ "(turno_fecha, slot, paciente_id, doctor_id) "
					+ "value "
					+ "(?,?,?,?)"
					);
			stmt.setDate(1, Date.valueOf(turno.getFechaInicio()));
			stmt.setInt(2, turno.getSlot());
			stmt.setInt(3, getPacienteID(turno.getPaciente().getDni()));
			stmt.setInt(4, getDoctorID(turno.getDoctor().getDni()));
			int filasCargadas = stmt.executeUpdate();
			if(filasCargadas != 1) {
				return false;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			rta = false;
		}
		return rta;
	}
	
	public static boolean addPaciente(Paciente paciente) {
		boolean rta = false;
		try {
			//insercion de persona en la bdd
			PreparedStatement stmt = conn.prepareStatement(
					"insert into muelas.personas "
					+ "(persona_dni,persona_nombre,persona_apellido,persona_fecha_nac,persona_numero,persona_correo) "
					+ "value "
					+ "(?,?,?,?,?,?)"
					);
			stmt.setInt(1, paciente.getDni());
			stmt.setString(2, paciente.getNombre());
			stmt.setString(3, paciente.getApellido());
			stmt.setDate(4, Date.valueOf(paciente.getFechaNacimiento()));
			stmt.setString(5, paciente.getNumero());
			stmt.setString(6, paciente.getCorreo());
			int filasCargadas = stmt.executeUpdate();
			if(filasCargadas != 1) {
				return false;
			}
			
			//insercion de paciente en la bdd
			stmt = conn.prepareStatement(
					"insert into muelas.pacientes "
					+ "(persona_dni) "
					+ "value "
					+ "(?)"
					);
			stmt.setInt(1, paciente.getDni());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			rta = false;
		}
		return rta;
	}
	
	
}

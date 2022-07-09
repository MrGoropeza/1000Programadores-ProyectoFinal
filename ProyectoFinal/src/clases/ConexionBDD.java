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
	
	//========================== selects ===============================
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
	public static ArrayList<Paciente> getPacientes(){
		ArrayList<Paciente> resultados = new ArrayList<Paciente>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from pacientes t1\n"
					+ "inner join personas t2\n"
					+ "where t1.persona_dni = t2.persona_dni";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Paciente paciente = new Paciente(
						rs.getInt("persona_dni"), 
						rs.getString("persona_nombre"),
						rs.getString("persona_apellido"),
						rs.getDate("persona_fecha_nac").toLocalDate(),
						rs.getString("persona_numero"), 
						rs.getString("persona_correo"));
				resultados.add(paciente);
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
	public static Doctor getDoctorFromDNI(int dni) {
		Doctor resultado = null;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from doctores t1\n"
					+ "inner join personas t2, sectores t3\n"
					+ "where t1.persona_dni = t2.persona_dni\n"
					+ "and t3.sector_id = t1.sector_id\n"
					+ "and t2.persona_dni = " + dni;
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
			System.out.println("Hubo un error al obtener el doctor de la base de datos:");
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
				Turno turno = new Turno(
						fecha,
						rs.getInt("slot"),
						paciente,
						doctor,
						rs.getInt("turno_id"));
				turno.setEmergencia(rs.getBoolean("isEmergencia"));
				resultados.add(turno);
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener doctores de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultados;	
	}
	public static ArrayList<Paciente> getPacientesDoctor(Doctor doctor){
		ArrayList<Paciente> resultados = new ArrayList<Paciente>();
		try {
			String sql = "select \n"
					+ "	t1.persona_dni, t2.persona_nombre,\n"
					+ "    t2.persona_apellido, t2.persona_fecha_nac,\n"
					+ "    t2.persona_numero, t2.persona_correo\n"
					+ "from pacientes t1\n"
					+ "inner join personas t2, turnos t3, doctores t4\n"
					+ "where t1.persona_dni = t2.persona_dni\n"
					+ "and t3.paciente_id = t1.paciente_id\n"
					+ "and t4.doctor_id = " + getDoctorID(doctor.getDni()) + "\n"
					+ "group by t1.paciente_id;";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Paciente paciente = new Paciente(
						rs.getInt("persona_dni"),
						rs.getString("persona_nombre"), 
						rs.getString("persona_apellido"),
						rs.getDate("persona_fecha_nac").toLocalDate(),
						rs.getString("persona_numero"),
						rs.getString("persona_correo"));
						
				resultados.add(paciente);
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
				Turno turno = new Turno(
						rs.getDate("turno_fecha").toLocalDate(),
						rs.getInt("slot"),
						paciente,
						doctor,
						rs.getInt("turno_id"));
				turno.setEmergencia(rs.getBoolean("isEmergencia"));
				resultados.add(turno);
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener doctores de la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultados;	
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
	
	public static ArrayList<Tratamiento> getTratamientosPaciente(Paciente paciente){
		ArrayList<Tratamiento> tratamientos = new ArrayList<Tratamiento>();
		try {
			String sql = "select * from tratamientos t1 \n"
					+ "inner join pacientes t2, personas t3 \n"
					+ "where t3.persona_dni = t2.persona_dni \n"
					+ "and t1.paciente_id = t2.paciente_id \n"
					+ "and t3.persona_dni = '" + paciente.getDni() + "';";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Doctor doctor = getDoctorFromID(rs.getInt("doctor_id"));
				Tratamiento tratamiento = new Tratamiento(
						rs.getString("tratamiento_descripcion"), 
						rs.getDate("tratamiento_fecha").toLocalDate(),
						doctor,
						paciente, 
						rs.getBoolean("tratamiento_is_emergencia"));
				tratamientos.add(tratamiento);
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al obtener tratamientos de la base de datos:");
			System.out.println(e.getMessage());
		}
		return tratamientos;
	}
	//========================== selects ===============================
	

	
	//========================== deletes ===============================
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
	//========================== deletes ===============================
	
	
	
	//========================== inserts ===============================
	public static boolean addTurno(Turno turno) {
		boolean rta = false;
		try {
			//insercion de persona en la bdd
			PreparedStatement stmt = conn.prepareStatement(
					"insert into muelas.turnos "
					+ "(turno_fecha, slot, paciente_id, doctor_id, isEmergencia) "
					+ "value "
					+ "(?,?,?,?,?)"
					);
			stmt.setDate(1, Date.valueOf(turno.getFechaInicio()));
			stmt.setInt(2, turno.getSlot());
			stmt.setInt(3, getPacienteID(turno.getPaciente().getDni()));
			stmt.setInt(4, getDoctorID(turno.getDoctor().getDni()));
			stmt.setBoolean(5, turno.isEmergencia());
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
	public static boolean addTratamiento(Tratamiento tratamiento) {
		boolean rta = false;
		try {
			//insercion de persona en la bdd
			PreparedStatement stmt = conn.prepareStatement(
					"insert into muelas.tratamientos "
					+ "(tratamiento_descripcion, tratamiento_fecha, tratamiento_is_emergencia, doctor_id, paciente_id) "
					+ "value "
					+ "(?,?,?,?,?)"
					);
			stmt.setString(1, tratamiento.getDescripcion());
			stmt.setDate(2, Date.valueOf(tratamiento.getFecha()));
			stmt.setBoolean(3, tratamiento.getEsEmergencia());
			stmt.setInt(4, getDoctorID(tratamiento.getDoctor().getDni()));
			stmt.setInt(5, getPacienteID(tratamiento.getPaciente().getDni()));
			
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
	//========================== inserts ===============================
	
}

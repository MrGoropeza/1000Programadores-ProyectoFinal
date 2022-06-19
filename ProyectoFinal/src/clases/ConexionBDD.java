package clases;

import java.sql.*;
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
						rs.getString("persona_dni"),
						rs.getString("persona_nombre"),
						rs.getString("persona_apellido"),
						rs.getDate("persona_fecha_nac"),
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
						rs.getString("persona_dni"),
						rs.getString("persona_nombre"),
						rs.getString("persona_apellido"),
						rs.getDate("persona_fecha_nac"),
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
					+ "where t2.persona_dni = " + dni + ";";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				resultado = new Administrativo(
						rs.getString("persona_dni"),
						rs.getString("persona_nombre"),
						rs.getString("persona_apellido"),
						rs.getDate("persona_fecha_nac"),
						rs.getString("persona_numero"),
						rs.getString("persona_correo"));
			} else {
				System.out.println("Persona no encontrada.");
			}
		} catch (SQLException e) {
			System.out.println("Hubo un error al buscar la persona en la base de datos:");
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	public static void main(String[] args) {
		new ConexionBDD();
		try {
			Administrativo gonSanchez = getAdministrativoFromDNI(40297421);
			System.out.println("nombre: "+gonSanchez.getNombre());
			System.out.println("apellido: " + gonSanchez.getApellido());
			System.out.println("fecha: " + gonSanchez.getFechaNacimiento());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

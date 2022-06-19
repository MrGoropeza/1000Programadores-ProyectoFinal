package clases;

import java.sql.*;

public class ConexionBDD {
	
	static final String JBDC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/muelas";
	
	static final String USER = "basedatos";
	static final String PASS = "Contra123#";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JBDC_DRIVER);
			
			System.out.println("Conectando a la base de datos...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Conectado correctamente.");
			
			//Consulta SQL
			System.out.println("Creando sentencia...");
			stmt = conn.createStatement();
			String sql = "Select persona_nombre, persona_apellido From personas";
			ResultSet rs = stmt.executeQuery(sql);
			
			//Mostrar resultados
			while (rs.next()) {
				String nombre = rs.getString("persona_nombre");
				String apellido = rs.getString("persona_apellido");
				
				System.out.print("Nombre: " + nombre);
				System.out.print(", Apellido: " + apellido + "\n");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

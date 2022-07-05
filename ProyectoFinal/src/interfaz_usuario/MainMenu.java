package interfaz_usuario;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class MainMenu {
	
	public static Scanner input = new Scanner(System.in);
	
	public final static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");
	        
	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	    
	    
	}
	
	
	public static void selectUserToLoginText() {
		System.out.println(
				  "===================================================================\n"
				+ "  Bienvenido al Sistema Administrativo del Consultorio Dr. Muelas\n"
				+ "    Iniciar Sesión como (ingresar número de opción):\n"
				+ "      1) Administrador\n"
				+ "      2) Paciente\n"
				+ "===================================================================\n"
		);
		
		System.out.print("Opción: ");
	}
	
	public static int selectUserToLogin() {
		int respuesta = -1;
		
		selectUserToLoginText();
		String eleccion = input.nextLine();
		
		try {
			respuesta = Integer.parseInt(eleccion);
			
			switch (respuesta) {
			case 1:
				System.out.println("Opción elegida: Administrador");
				return respuesta;
			
			case 2:
				System.out.println("Opción elegida: Paciente");
				return respuesta;
				
			default:
				System.out.println("Opción no válida.");
				return -1;
			}
			
		} catch (Exception e) {
			System.out.println("Opción no válida.");
			clearConsole();
			return respuesta;
		}
		
	}
	
	public static void startMainMenu() {
		int respuesta;
		do {
			respuesta = selectUserToLogin();
		} while (respuesta == -1);
		
		switch (respuesta) {
		case 1:
			
			break;

		case 2:
			
			break;
		}
	}
	

	public static void main(String[] args) {
		startMainMenu();
		
	}

}

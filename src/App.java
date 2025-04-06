import java.util.Scanner;

import dependencias.*;
import factory.*;

public class App {
    public static void main(String[] args) throws Exception {
        boolean salir = false;
        Scanner scan = new Scanner(System.in);
        RegistroEmergencias registro = new RegistroEmergencias(); 
       

        do {
            System.out.println("*-*-*-*-*-*-*--*-*--*-*-*-*--*-*-*-*-*-*-*");
            System.out.println(" ¡Bienvenido al Sistema de Gestión de Emergencias Urbanas! ");
            System.out.println("*-*-*-*-*-*-*--*-*--*-*-*-*--*-*-*-*-*-*-*");
            System.out.println("===============================================");
            System.out.println("Aquí podrás registrar, visualizar y atender emergencias");
            System.out.println("de forma rápida y organizada.");
            System.out.println("Asegúrate de tener todos los recursos listos.");
            System.out.println("===============================================");
            System.out.println("Menú Principal:");
            System.out.println("1. Registrar Emergencia");
            System.out.println("2. Gestión de Emergencias");
            System.out.println("3. Atender Emergencias");
            System.out.println("4. Recursos");
            System.out.println("0. Terminar Jornada");

            switch (getOpcion(scan)) {
                case 0:
                    System.out.println("Terminando jornada...");
                    salir = true;
                    break;

                case 1:
                    registro.menu(); 
                    break;

                case 2:
                    registro.gestionEmergencias();  
                    break;

                case 3:
                    registro.atenderEmergencia();
                    break;
                
                case 4:
                       RecursosFactory.showRecursos();
                    break;

                default:
                    System.err.println("Opcion invalida, elija una opcion correcta");
            }
            pressEnter(scan);
        } while (!salir);

        scan.close(); 
    }

       //metodo para controlar lo que el usuario inserta
    private static int getOpcion(Scanner scan) {
        while (true) {
            try {
                System.out.print("Selecciona una opción: ");
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
            }
        }
    }

    public static void pressEnter(Scanner scan) {
        System.out.println("Presione ENTER para continuar...");
        scan.nextLine();
    }
}

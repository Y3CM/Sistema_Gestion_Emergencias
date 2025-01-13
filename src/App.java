import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        boolean salir = false;
        Scanner scan = new Scanner(System.in);
        RegistroEmergencias registro = new RegistroEmergencias(); // Instancia única de RegistroEmergencias

        do {
            System.out.println("*-*-*-*-*-*-*--*-*--*-*-*-*--*-*-*-*-*-*-*");
            System.out.println("Sistema de Gestion de Emergencias Urbanas");
            System.out.println("*-*-*-*-*-*-*--*-*--*-*-*-*--*-*-*-*-*-*-*");
            System.out.println("Menú Principal:");
            System.out.println("1. Registrar Emergencia");
            System.out.println("2. Consultar Emergencias");
            System.out.println("3. Atender Emergencias");
            System.out.println("4. Recursos");
            System.out.println("0. Terminar Jornada");

            int opcion = scan.nextInt();
            scan.nextLine();
            switch (opcion) {
                case 0:
                    System.out.println("Terminando jornada...");
                    salir = true;
                    break;

                case 1:
                    registro.menu(); 
                    break;

                case 2:
                    registro.showEmergencias();  
                    break;

                case 3:
                    
                    break;
                
                case 4:
                System.out.println("Opción de recursos aún no implementada.");
                    break;

                default:
                    System.err.println("Opcion invalida, elija una opcion correcta");
            }
            pressEnter(scan);
        } while (!salir);

        scan.close(); // Cerramos el Scanner al terminar
    }

    public static void pressEnter(Scanner scan) {
        System.out.println("Presione ENTER para continuar...");
        scan.nextLine();
    }
}

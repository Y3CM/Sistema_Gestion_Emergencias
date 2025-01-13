import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import enums.NivelGravedad;

public class RegistroEmergencias {

    private List<Emergencia> emergencias;
    private boolean salir = false;
    Scanner scan = new Scanner(System.in);
    public RegistroEmergencias(){
        emergencias = new ArrayList<>();
    }
    public void menu(){
        do{
            System.out.println("\nMenú de Registro de Emergencias");
            System.out.println("Elije el tipo de emergencia a registrar");
            System.out.println("1. Accidente Vehicular");
            System.out.println("2. Incendio");
            System.out.println("3. Robo");
            System.out.println("4. Volver");

            int opcion = scan.nextInt();
            scan.nextLine();
            switch (opcion) {
                case 1:

                    Emergencia accidente = new Emergencia("Accidente",nivel(scan),tiempoRespuesta(),ubicacion());
                     addEmergencia(accidente);
                    accidente.showEmergencia();
                    salir = true;
                    break;
                case 2:

                    Emergencia incendio = new Emergencia("Incendio",nivel(scan), tiempoRespuesta(), ubicacion());
                    addEmergencia(incendio);
                    incendio.showEmergencia();
                    salir = true;
                    break;
                case 3:
                        Emergencia robo = new Emergencia("Robo",nivel(scan), tiempoRespuesta(), ubicacion());
                    addEmergencia(robo);
                    robo.showEmergencia();
                    salir = true;
                    break;
                case 4:
                    System.out.println("Press enter para volver");
                    scan.nextLine();
                    salir = true; 
                    break;
            
                default:
                        System.out.println("Opción invalida.");
                    break;
            }
        }while(!salir);
    }

    public NivelGravedad nivel(Scanner scan){
        int opcion;
        boolean volver;
        do{
            System.out.println("Por favor elige el nivel de gravedad de la emergencia");
            System.out.println("1. Bajo");
            System.out.println("2. Medio");
            System.out.println("3. Alto");
            System.out.println("0. Volver");
           opcion = scan.nextInt();
            scan.nextLine();
            switch(opcion){
                case 0:
                    volver = true;
                case 1:
                return NivelGravedad.BAJO;
                case 2:
                return NivelGravedad.MEDIO;
                case 3: 
                return NivelGravedad.ALTO;
                default:
                    throw new IllegalArgumentException("Nivel de garavedad desconocido");
            }
        }while(!volver);


    }

    public int tiempoRespuesta() {
      /*   NivelGravedad gravedad = nivel(scan);
        if (gravedad == NivelGravedad.BAJO) {
            return 30;
        } else if (gravedad == NivelGravedad.MEDIO) {
            return 15;
        } else if (gravedad == NivelGravedad.ALTO) {
            return 5;
        } else {
            throw new IllegalArgumentException("Nivel de gravedad no válido.");
        } */
        System.out.println("Tiempo de respuesta estimado (minutos): ");
      int tiempo = scan.nextInt();
      scan.nextLine();
      return tiempo;
    }

    public String ubicacion(){
        System.out.println("Ubicacion de la emergencia: ");
        String ubicacion = scan.nextLine();
        return ubicacion;
    }

    public void addEmergencia(Emergencia emergencia){
        this.emergencias.add(emergencia);
    }

    public void showEmergencias(){
        if(emergencias.isEmpty()){
            System.out.println("Aun no has registrado ninguna emergencia.");
        }else{
            for(Emergencia emergencia : emergencias){
                emergencia.showEmergencia();
            }
        }
    }
    public List<Emergencia> getEmergencias() {
        return emergencias;
    }
    public void setEmergencias(List<Emergencia> emergencias) {
        this.emergencias = emergencias;
    }
   /*  public String tipo(String tipo){
        System.out.println("Tipo de emergencia:");
        String tipo;
        this.tipo = tipo;
        return tipo;
    } */
}

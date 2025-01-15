package dependencias;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import enums.NivelGravedad;

public class RegistroEmergencias {

    private List<Emergencia> emergencias;
    private Scanner scan;

    public RegistroEmergencias() {
        this.emergencias = new ArrayList<>();
        this.scan = new Scanner(System.in);
    }

    public void menu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("*-*-*-*-*-*-*--*-*--*-*-*-*--*-*-*-*-*-*-*");
            System.out.println("Menú de Registro de Emergencias");
            System.out.println("*-*-*-*-*-*-*--*-*--*-*-*-*--*-*-*-*-*-*-*");
            System.out.println("Elije el tipo de emergencia a registrar:");
            System.out.println("1. Accidente Vehicular");
            System.out.println("2. Incendio");
            System.out.println("3. Robo");
            System.out.println("4. Mostrar todas las emergencias registradas");
            System.out.println("5. Salir");

            int opcion = getOpcion();

            switch (opcion) {
                case 1 : registrarEmergencia("Accidente");
                        salir = true;
                        break;
                case 2 : registrarEmergencia("Incendio");
                        salir = true;
                        break;
                case 3 : registrarEmergencia("Robo");
                        salir = true;
                        break;
                case 4 : showEmergencias();
                        break;
                case 5 : 
                    System.out.println("Volver");
                    salir = true;
                    break;
                default : System.out.println("Opción inválida. Por favor, intenta de nuevo.");
            }
            
        }
    }

    //metodo para controlar lo que el usuario inserta
    private int getOpcion() {
        while (true) {
            try {
                System.out.print("Selecciona una opción: ");
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
            }
        }
    }

    private void registrarEmergencia(String tipo) {
        System.out.println("Registrando una nueva emergencia de tipo: " + tipo);

        NivelGravedad nivelGravedad = seleccionarNivelGravedad();
        int tiempoRespuesta = getTiempoRespuesta();
        String ubicacion = getUbicacion();

        Emergencia nuevaEmergencia = new Emergencia(tipo, nivelGravedad, tiempoRespuesta, ubicacion);
        addEmergencia(nuevaEmergencia);

        System.out.println("Emergencia registrada con éxito:");
        nuevaEmergencia.showEmergencia();
        
    }

    private NivelGravedad seleccionarNivelGravedad() {
        while (true) {
            System.out.println("Selecciona el nivel de gravedad:");
            System.out.println("1. Bajo");
            System.out.println("2. Medio");
            System.out.println("3. Alto");

            int opcion = getOpcion();

            switch (opcion) {
                case 1 : 
                    return NivelGravedad.BAJO;

                case 2 : 
                    return NivelGravedad.MEDIO;
                
                case 3 : 
                    return NivelGravedad.ALTO;
                
                default : System.out.println("Opción inválida. Por favor, intenta de nuevo.");
            }
        }
    }

    private int getTiempoRespuesta() {
        System.out.print("Introduce el tiempo de respuesta estimado (en minutos): ");
        return getOpcion(); // Reutilizamos `obtenerOpcion` para validar entrada numérica
    }

    private String getUbicacion() {
        System.out.print("Introduce la ubicación de la emergencia: ");
        return scan.nextLine();
    }

    public void addEmergencia(Emergencia emergencia) {
        this.emergencias.add(emergencia);
    }

    public void showEmergencias() {
        if (emergencias.isEmpty()) {
            System.out.println("No hay emergencias registradas.");
        } else {
            System.out.println("*-*-*-*-*-*-*--*-*--*-*-*-*--*-*-*-*-*-*-*");
            System.out.println("Lista de Emergencias Registradas:");
            System.out.println("*-*-*-*-*-*-*--*-*--*-*-*-*--*-*-*-*-*-*-*");
            for (int i = 0; i < emergencias.size(); i++) {
            System.out.println("-----------------------------------------");
                System.out.println("Emergencia #" + (i + 1)); 
                emergencias.get(i).showEmergencia();
            }
        }
    }

    public List<Emergencia> getEmergencias() {
        return emergencias;
    }

    public void setEmergencias(List<Emergencia> emergencias) {
        this.emergencias = emergencias;
    }

    public void atenderEmergencia(){
        System.out.println("Emergencias registradas:");
        for(Emergencia emergencia:emergencias){
            emergencia.showEmergencia();
        }
        System.out.println("Seleccione el numero de la emergencia que desea atender:");
        int indice = getOpcion() - 1;
        if (indice >= 0 && indice < getEmergencias().size()) {
            Emergencia emergencia = emergencias.get(indice);
            System.out.println("Atendiendo la emergencia:");
            emergencia.showEmergencia();
        } else {
            System.err.println("Índice inválido. No se pudo atender la emergencia.");
        }
    }

}

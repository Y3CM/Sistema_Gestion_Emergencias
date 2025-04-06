package dependencias;

import enums.NivelGravedad;
import factory.RecursosFactory;
import dependencias.Recursos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("\n*-*-*-*-*-*-* MENÚ REGISTRO EMERGENCIAS *-*-*-*-*-*-*");
            System.out.println("1. Registrar Accidente Vehicular");
            System.out.println("2. Registrar Incendio");
            System.out.println("3. Registrar Robo");
            System.out.println("4. Volver");
            

            int opcion = getOpcion();

            switch (opcion) {
                case 1 -> registrarEmergencia("Accidente");
                case 2 -> registrarEmergencia("Incendio");
                case 3 -> registrarEmergencia("Robo");
                case 4 -> salir = true;
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private int getOpcion() {
        while (true) {
            try {
                System.out.print("Selecciona una opción: ");
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingresa un número.");
            }
        }
    }

    private void registrarEmergencia(String tipo) {
        System.out.println("\nRegistrando nueva emergencia de tipo: " + tipo);

        NivelGravedad nivel = seleccionarNivelGravedad();
        int tiempo = getTiempoRespuesta();
        String ubicacion = getUbicacion();

        Emergencia emergencia = new Emergencia(tipo, nivel, tiempo, ubicacion);
        emergencias.add(emergencia);

        System.out.println("Emergencia registrada exitosamente:");
        emergencia.showEmergencia();

        pressEnter();
    }

    private NivelGravedad seleccionarNivelGravedad() {
        while (true) {
            System.out.println("Nivel de gravedad:");
            System.out.println("1. Bajo");
            System.out.println("2. Medio");
            System.out.println("3. Alto");
            int opcion = getOpcion();
            return switch (opcion) {
                case 1 -> NivelGravedad.BAJO;
                case 2 -> NivelGravedad.MEDIO;
                case 3 -> NivelGravedad.ALTO;
                default -> {
                    System.out.println("Opción inválida.");
                    yield null;
                }
            };
        }
    }

    private int getTiempoRespuesta() {
        return leerNumero("Tiempo de respuesta estimado (minutos):");
    }

    private String getUbicacion() {
        return leerTexto("Introduce la ubicación de la emergencia:");
    }

    public void showEmergencias() {
        if (emergencias.isEmpty()) {
            System.out.println("No hay emergencias registradas.");
            pressEnter();
            return;
        }

        System.out.println("\nLista de emergencias registradas:");
        for (int i = 0; i < emergencias.size(); i++) {
            System.out.println("Emergencia #" + (i + 1));
            emergencias.get(i).showEmergencia();
        }
    }

    public void atenderEmergencia() {
        if (emergencias.isEmpty()) {
            System.out.println("No hay emergencias registradas.");
            return;
        }

        System.out.println("\nSeleccione la emergencia que desea atender:");
        for (int i = 0; i < emergencias.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + emergencias.get(i).getTipo() + " - Estado: " + emergencias.get(i).getEstado());
        }

        int indice = getOpcion() - 1;

        if (indice < 0 || indice >= emergencias.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Emergencia emergencia = emergencias.get(indice);

        if (!emergencia.getEstado().equals("Pendiente")) {
            System.out.println("Esta emergencia ya está en estado: " + emergencia.getEstado());
            return;
        }

        emergencia.setEstado("Atendiendo");

        try {
            switch (emergencia.getGravedad()) {
                case BAJO -> asignarRecursos(emergencia, 1, 0, 0);
                case MEDIO -> asignarRecursos(emergencia, 1, 1, 1);
                case ALTO -> asignarRecursos(emergencia, 2, 2, 2);
            }
        } catch (IllegalStateException e) {
            System.out.println("No se pudieron asignar todos los recursos: " + e.getMessage());
        }

        emergencia.setEstado("Atendiendo");
        System.out.println("La emergencia esta siendo atendida recursos asignados con éxito.");
        pressEnter();
    }

    private void asignarRecursos(Emergencia emergencia, int policias, int ambulancias, int bomberos) {
        for (int i = 0; i < policias; i++) {
            Recursos r = RecursosFactory.obtenerRecursoDisponible("Policia");
            r.asignar(emergencia.getUbicacion());
            emergencia.agregarRecursoAsignado(r);
        }
        for (int i = 0; i < ambulancias; i++) {
            Recursos r = RecursosFactory.obtenerRecursoDisponible("Ambulancia");
            r.asignar(emergencia.getUbicacion());
            emergencia.agregarRecursoAsignado(r);
        }
        for (int i = 0; i < bomberos; i++) {
            Recursos r = RecursosFactory.obtenerRecursoDisponible("Bomberos");
            r.asignar(emergencia.getUbicacion());
            emergencia.agregarRecursoAsignado(r);
        }

        System.out.println("Recursos asignados:");
        for (Recursos r : emergencia.getRecursosAsignados()) {
            System.out.println("- " + r.getStatus());
        }
    }

    public void cerrarEmergencia() {
        if (emergencias.isEmpty()) {
            System.out.println("No hay emergencias registradas.");
            pressEnter();
            return;
        }

        System.out.println("\nSeleccione la emergencia que desea finalizar:");
        for (int i = 0; i < emergencias.size(); i++) {
            Emergencia e = emergencias.get(i);
            System.out.println((i + 1) + ". " + e.getTipo() + " - Estado: " + e.getEstado());
        }

        int indice = getOpcion() - 1;

        if (indice < 0 || indice >= emergencias.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Emergencia emergencia = emergencias.get(indice);

        if (!emergencia.getEstado().equals("Atendiendo")) {
            System.out.println("Solo se pueden finalizar emergencias que están en estado 'Atendiendo'.");
            return;

        }

        emergencia.liberarRecursos();
        emergencia.setEstado("Finalizada");

        System.out.println("Emergencia finalizada correctamente.");
    }

    // Getters y setters por si los necesitás en el futuro
    public List<Emergencia> getEmergencias() {
        return emergencias;
    }

    public void setEmergencias(List<Emergencia> emergencias) {
        this.emergencias = emergencias;
    }

    private void pressEnter() {
        System.out.println("Presiona ENTER para continuar...");
        scan.nextLine();
    }

    public void gestionEmergencias() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n*-*-*-*-*-*-* MENÚ GESTION EMERGENCIAS *-*-*-*-*-*-*");
            System.out.println("1. Mostrar emergencias");
            System.out.println("2. Atender emergencia");
            System.out.println("3. Finalizar emergencia");
            System.out.println("4. Mostrar recursos disponibles");
            System.out.println("5. Volver");

            int opcion = getOpcion();

            switch (opcion) {

                case 1 -> showEmergencias();
                case 2 -> atenderEmergencia();
                case 3 -> cerrarEmergencia();
                case 4 -> RecursosFactory.showRecursosDisponibles();
                case 5 -> salir = true;
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
    
    private String leerTexto(String mensaje) {
        System.out.print(mensaje + " ");
        return scan.nextLine();
    }

    private int leerNumero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + " ");
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
            }
        }
    }

}

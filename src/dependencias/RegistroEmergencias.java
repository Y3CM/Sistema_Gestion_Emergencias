package dependencias;

import enums.NivelGravedad;
import factory.RecursosFactory;
import interfaces.Observer;
import interfaces.Responder;
import interfaces.Subject;
import services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistroEmergencias implements Subject{

    private List<Emergencia> emergencias;
    private Scanner scan;
    private List<Observer> observers = new ArrayList<>();

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
              List<Recursos> recursosCopia = new ArrayList<>(emergencia.getRecursosAsignados());
            for (Recursos recurso : recursosCopia) {
                if (recurso instanceof Responder responder) {
                    responder.atenderEmergencia(emergencia);
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("No se pudieron asignar todos los recursos: " + e.getMessage());
        }

        emergencia.setEstado("Atendiendo");
        
        System.out.println("ya puedes cambiar el estado de la emergencia a finalizada");
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

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Emergencia emergencia) {
        observers.forEach(observer -> observer.update(emergencia));
    }


    public void registrarEmergencia(Emergencia emergencia) {
        emergencias.add(emergencia);
        notifyObservers(emergencia); 
    }

    public void mostrarResumen() {
        System.out.println("\n===== RESUMEN DE LA JORNADA =====");
        System.out.println("Total de emergencias registradas: " + emergencias.size());

        long atendidas = emergencias.stream().filter(e -> e.getEstado().equalsIgnoreCase("Finalizada")).count();
        long activas = emergencias.stream().filter(e -> !e.getEstado().equalsIgnoreCase("Finalizada")).count();

        System.out.println("Emergencias finalizadas: " + atendidas);
        System.out.println("Emergencias activas: " + activas);

        System.out.println("\n--- Emergencias activas ---");
        emergencias.stream()
                .filter(e -> !e.getEstado().equalsIgnoreCase("Finalizada"))
                .forEach(e -> System.out
                        .println(e.getTipo() + " en " + e.getUbicacion() + " - Estado: " + e.getEstado()));

        System.out.println("\n--- Recursos asignados por emergencia ---");
        for (Emergencia e : emergencias) {
            System.out.println("[" + e.getTipo() + " - " + e.getUbicacion() + " - Estado: " + e.getEstado() + "]");
            if (e.getRecursosAsignados().isEmpty()) {
                System.out.println("  Sin recursos asignados.");
            } else {
                e.getRecursosAsignados().forEach(
                        r -> System.out.println("  - " + r.getTipo() + " (ID: " + r.getId() + ") - " + r.getStatus()));
            }
        }

        // === Conteo total de recursos usados por tipo ===
        System.out.println("\n--- Total de recursos utilizados ---");
        int totalPolicias = 0, totalAmbulancias = 0, totalBomberos = 0;

        for (Emergencia e : emergencias) {
            for (Recursos r : e.getRecursosAsignados()) {
                switch (r.getTipo().toLowerCase()) {
                    case "policia" -> totalPolicias++;
                    case "ambulancia" -> totalAmbulancias++;
                    case "bomberos" -> totalBomberos++;
                }
            }
        }

        System.out.println("Policías utilizados: " + totalPolicias);
        System.out.println("Ambulancias utilizadas: " + totalAmbulancias);
        System.out.println("Bomberos utilizados: " + totalBomberos);

        System.out.println("=========================================\n");

    
    } 
    

}

package factory;

import java.util.ArrayList;
import java.util.List;

import services.*;

import dependencias.*;

public class RecursosFactory {
    private static List<Recursos> recursos;

    // los recursos se crean al inicio de la aplicación
    static {
        recursos = new ArrayList<>();
        // Crear recursos 
        recursos.add(new Ambulancia("AMB-001", "Hospital Central", 2));
        recursos.add(new Ambulancia("AMB-002", "Hospital Norte", 3));
        recursos.add(new Ambulancia("AMB-003", "Hospital Sur", 3));
        recursos.add(new Ambulancia("AMB-004", "Hospital Principal", 3));
        recursos.add(new Bomberos("BOM-001", "Estación Sur", 1));
        recursos.add(new Bomberos("BOM-002", "Estación Norte", 1));
        recursos.add(new Bomberos("BOM-003", "Estación Oeste", 1));
        recursos.add(new Bomberos("BOM-004", "Estación Este", 1));
        recursos.add(new Policia("POL-001", "Comisaría Central", 1));
        recursos.add(new Policia("POL-002", "Comisaría SurEste", 1));
        recursos.add(new Policia("POL-003", "Comisaría Noroeste", 1));
        recursos.add(new Policia("POL-004", "Comisaría Sur", 1));
    }

    // Método para obtener recursos disponibles según el tipo
    public static Recursos obtenerRecursoDisponible(String tipo) {
        for (Recursos recurso : recursos) {
            if (recurso.getTipo().equals(tipo) && recurso.isDisponible()) {
                return recurso;
            }
            
        }
        throw new IllegalStateException("No hay recursos disponibles del tipo: " + tipo);
    }

    // Método para obtener todos los recursos
    public static List<Recursos> getRecursos() {
        return recursos;
    }

    public static void showRecursos() {
        for (Recursos recurso : getRecursos()) {
            System.out.println("Recurso: " + recurso);
        }
    }

    public static void showRecursosDisponibles() {
        for (Recursos recurso : getRecursos()) {
            if (recurso.isDisponible()) {
                System.out.println("Recursos disponibles: " + recurso);
            }
        }   
    }

    
    }

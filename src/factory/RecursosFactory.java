package factory;

import java.util.ArrayList;
import java.util.List;

import services.*;

import dependencias.*;

public class RecursosFactory {
    private static List<Recursos> recursos;

    // Inicialización estática: los recursos se crean al inicio de la aplicación
    static {
        recursos = new ArrayList<>();
        // Crear recursos predefinidos
        recursos.add(new Ambulancia("AMB-001", "Estación Central", 4));
        recursos.add(new Ambulancia("AMB-002", "Estación Norte", 4));
        recursos.add(new Bomberos("BOM-001", "Estación Sur", 5000));
        recursos.add(new Bomberos("BOM-002", "Estación Este", 3000));
        recursos.add(new Policia("POL-001", "Comisaría Central", 1));
        recursos.add(new Policia("POL-002", "Comisaría Norte", 3));
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
}
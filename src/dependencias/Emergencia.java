package dependencias;

import enums.NivelGravedad;
import factory.RecursosFactory;


import java.util.ArrayList;
import java.util.List;

public class Emergencia {

    private String tipo;
    private NivelGravedad nivelGravedad;
    private int tiempoRespuesta;
    private String ubicacion;

    private String estado; // Pendiente, Atendiendo, Atendida, Finalizada
    private List<Recursos> recursosAsignados;

    public Emergencia(String tipo, NivelGravedad nivelGravedad, int tiempoRespuesta, String ubicacion) {
        this.tipo = tipo;
        this.nivelGravedad = nivelGravedad;
        this.tiempoRespuesta = tiempoRespuesta;
        this.ubicacion = ubicacion;
        this.estado = "Pendiente";
        this.recursosAsignados = new ArrayList<>();
    }

    public void showEmergencia() {
        System.out.println("Detalles de la emergencia");
        System.out.println("-----------------------------------------");
        System.out.println("Tipo de emergencia: " + tipo);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Tiempo de respuesta: " + tiempoRespuesta + " minutos (estimado)");
        System.out.println("Nivel de gravedad: " + nivelGravedad);
        System.out.println("Estado: " + estado);
    }

    public NivelGravedad getGravedad() {
        return nivelGravedad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void agregarRecursoAsignado(Recursos recurso) {
        recursosAsignados.add(recurso);
    }

    public List<Recursos> getRecursosAsignados() {
        return recursosAsignados;
    }

    public void liberarRecursos() {
        for (Recursos r : recursosAsignados) {
            r.liberar();
        }
        recursosAsignados.clear();
    }

    public String getTipo() {
        return tipo;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

  
}

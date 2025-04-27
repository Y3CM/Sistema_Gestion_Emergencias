package services;
import interfaces.Responder;
import dependencias.*;

public class Bomberos extends Recursos implements Responder {

    private int camion;
    
 public Bomberos(String id, String location, int camion) {
        super(id, location);
        this.camion = camion;
    }

  @Override
    public String getTipo() {
        return "Bomberos";
    }

public int getCamion() {
    return camion;
}

public void setCamion(int camion) {
    this.camion = camion;
}

@Override
public String toString() {
    return "Bomberos: " + super.toString() + " ,camion= " + camion;
}

@Override
public void atenderEmergencia(Emergencia emergencia) {
    System.out.println("Bomberos " + getId()
            + " desplazándose a " + emergencia.getUbicacion()
            + " para atender [" + emergencia.getTipo()
            + "] (gravedad: " + emergencia.getGravedad() + ")");

    this.asignar(emergencia.getUbicacion());
    emergencia.agregarRecursoAsignado(this);

    try {
        for (int progreso = 0; progreso <= 100; progreso += 25) {
            System.out.print("Atención: " + progreso + "% completado\r");
            Thread.sleep(500);
        }
        System.out.println();
    } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
    }

    System.out.println("Bomberos " + getId() + " ha terminado la atención en " + emergencia.getUbicacion());

    this.liberar();
}

@Override
public void notificar(Emergencia emergencia) {
    if (emergencia.getTipo() != null && emergencia.getTipo().equals("Incendio")) {
        System.out.println("Bomberos: nueva emergencia de incendio en " + emergencia.getUbicacion());
    }
}

}

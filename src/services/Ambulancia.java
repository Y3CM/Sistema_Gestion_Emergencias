package services;
import interfaces.*;
import dependencias.*;

public class Ambulancia extends Recursos implements Responder {

  private int paramedicos;

   public Ambulancia(String id, String location, int paramedicos) {
       super(id, location);
       this.paramedicos = paramedicos;
    }


    @Override
    public String getTipo() {
        return "Ambulancia";
    }


    public int getParamedicos() {
        return paramedicos;
    }



    public void setParamedicos(int paramedicos) {
        this.paramedicos = paramedicos;
    }



    @Override
    public String toString() {
        return "Ambulancia "+ super.toString() +" ,paramedicos= " + paramedicos ;
    }


    @Override
    public void atenderEmergencia(Emergencia emergencia) {
        System.out.println("Ambulancia " + getId()
                + " desplazándose a " + emergencia.getUbicacion()
                + " para atender [" + emergencia.getTipo()
                + "] (gravedad: " + emergencia.getGravedad() + ")");

        this.asignar(emergencia.getUbicacion());

        try {
            for (int progreso = 0; progreso <= 100; progreso += 25) {
                System.out.print("Atención: " + progreso + "% completado");
                Thread.sleep(500);
            }
            System.out.println();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Ambulancia " + getId() + " ha terminado la atención en " + emergencia.getUbicacion());

        this.liberar();
    }

    @Override
    public void notificar(Emergencia emergencia) {
        System.out.println("Ambulancia " + getId() + " notificada de emergencia: [" + emergencia.getTipo() + "] en " + emergencia.getUbicacion() + " (gravedad: " + emergencia.getGravedad() + ")");
    }

    
}


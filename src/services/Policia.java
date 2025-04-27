package services;
import interfaces.Responder;
import dependencias.*;

public class Policia extends Recursos implements Responder{

    private int patrullas;

     public Policia(String id, String location, int patrullas) {
        super(id, location);
        this.patrullas = patrullas;
    }


    @Override
    public String getTipo() {
        return "Policia";
    }



    public int getPatrullas() {
        return patrullas;
    }



    public void setPatrullas(int patrullas) {
        this.patrullas = patrullas;
    }



    @Override
    public String toString() {
        return "Policia: "+ super.toString() +" ,patrullas= " + patrullas;
    }


    @Override
    public void atenderEmergencia(Emergencia emergencia) {
        this.setDisponible(false);
        System.out.println("Policía " + getId() + " se dirige a atender la emergencia: " + emergencia.getTipo()
                + " en " + emergencia.getUbicacion());
    }


    @Override
    public void notificar(Emergencia emergencia) {
        System.out.println("Notificación: Policía " + getId() + " ha sido asignada para una emergencia de tipo "
                + emergencia.getTipo() + " en " + emergencia.getUbicacion());
    }

 

}

package services;
import interfaces.Responder;
import dependencias.*;
import enums.NivelGravedad;

public class Policia extends Recursos implements Responder{

    private int patrullas;

     public Policia(String id, String location, int patrullas) {
        super(id, location);
        this.patrullas = patrullas;
    }

  
    
    @Override
    public void atenderEmergencia() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atenderEmergencia'");
    }

    @Override
    public void evaluarEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'evaluarEstado'");
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

 

}

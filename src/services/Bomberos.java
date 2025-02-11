package services;
import interfaces.Responder;
import dependencias.*;
import enums.NivelGravedad;

public class Bomberos extends Recursos implements Responder {

    private int camion;
    
 public Bomberos(String id, String location, int camion) {
        super(id, location);
        this.camion = camion;
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

}

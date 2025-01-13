import interfaces.Responder;
import enums.NivelGravedad;

public class Ambulancia extends Emergencia implements Responder {

  private int paramedicos;

  public Ambulancia(String tipo,NivelGravedad nivelGravedad, int tiempoRespuesta, String ubicacion){
    super(tipo,nivelGravedad,tiempoRespuesta,ubicacion);
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

}

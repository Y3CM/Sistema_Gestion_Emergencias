package accidentes;
import interfaces.Emergencias;

public class Incendio implements Emergencias{

    @Override
    public void tipo(String tipo) {
        System.out.println("Tipo de emergencia: " + tipo);
    }
    
  
    }



package accidentes;
import interfaces.Emergencias; 

public class AccidenteVehicular implements Emergencias{

    @Override
    public void tipo(String tipo) {
       System.out.println("Tipo de emergencia: " + tipo);
    }
  
   

}

package accidentes;
import interfaces.Emergencias;

public class Robo implements Emergencias{

    @Override
    public void tipo(String tipo) {
        System.out.println("Tipo de emergencia: " + tipo);
    }

   
}

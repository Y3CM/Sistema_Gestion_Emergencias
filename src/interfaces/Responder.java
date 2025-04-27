package interfaces;
import dependencias.*;

public interface Responder {
    void atenderEmergencia(Emergencia emergencia);
    void notificar(Emergencia emergencia);
}

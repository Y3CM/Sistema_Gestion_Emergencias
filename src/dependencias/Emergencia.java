package dependencias;
import enums.NivelGravedad;

public class Emergencia {

   private NivelGravedad nivelGravedad;
   private int tiempoRespuesta;
   private String ubicacion;
   private String tipo;

public Emergencia(NivelGravedad nivelGravedad) {
      this.nivelGravedad = nivelGravedad;
   }

public Emergencia(String tipo,NivelGravedad nivelGravedad, int tiempoRespuesta, String ubicacion) {
    this.tipo = tipo;
    this.nivelGravedad = nivelGravedad;
    this.tiempoRespuesta = tiempoRespuesta;
    this.ubicacion = ubicacion;
}
public int getTiempoRespuesta() { 
    return tiempoRespuesta;
}

public void setTiempoRespuesta(int tiempoRespuesta) {
    this.tiempoRespuesta = tiempoRespuesta;
}

public String getUbicacion() {
    return ubicacion;
}

public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
}
   

public void showEmergencia(){
    System.out.println("Detalles de la emergencia");
    System.out.println("-----------------------------------------");
    System.out.println("Tipo de emergencia: " + tipo);
    System.out.println("Ubicación: " + ubicacion);
    System.out.println("Tiempo de respuesta: " + tiempoRespuesta + " Minutos. (Este tiempo es un  estimado.)");
    System.out.println("Nivel de gravedad: " + nivelGravedad);
}

public NivelGravedad getNivelGravedad() {
    return nivelGravedad;
}

public void setNivelGravedad(NivelGravedad nivelGravedad) {
    this.nivelGravedad = nivelGravedad;
}
}

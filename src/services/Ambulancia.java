package services;
import interfaces.*;
import dependencias.*;
import enums.NivelGravedad;

public class Ambulancia extends Recursos implements Responder, CalcularRecursos {

  private int paramedicos;

   public Ambulancia(String id, String location, int paramedicos) {
       super(id, location);
       this.paramedicos = paramedicos;
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
        return "Ambulancia";
    }



    @Override
    public int recursosRequeridos(String tipoEmergencia, NivelGravedad nivelGravedad) {
        if(nivelGravedad == NivelGravedad.ALTO){
            return 2;
        }
        return 1;
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

    
}


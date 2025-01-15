package dependencias;

public abstract class Recursos {
    private String id;
    private String ubicacion;
    private boolean disponible;

    public Recursos(String id, String ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.disponible = true;
    }

    public String getId() {
        return id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void asignar(String nuevaUbicacion) {
        this.ubicacion = nuevaUbicacion;
        this.disponible = false;
        System.out.println(id + " asignado a " + nuevaUbicacion);
    }

    public void liberar() {
        this.disponible = true;
        System.out.println(id + " liberado y disponible nuevamente.");
    }

    public abstract String getTipo();

    public String getStatus() {
        return "Recurso: " + id + " | Tipo: " + getTipo() + " | Ubicación: " + ubicacion + " | Disponible: "
                + disponible;
    }
}


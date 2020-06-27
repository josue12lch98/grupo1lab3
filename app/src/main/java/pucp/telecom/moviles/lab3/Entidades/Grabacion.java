package pucp.telecom.moviles.lab3.Entidades;

public class Grabacion {

    private int  tiempo;
    private String latitud;
    private String longitud;
    private double[] mediciones;

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public double[] getMediciones() {
        return mediciones;
    }

    public void setMediciones(double[] mediciones) {
        this.mediciones = mediciones;
    }
}

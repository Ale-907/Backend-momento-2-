package app.domain;


public class Sala2D extends Sala {
    private boolean tieneSonidoDolby;

    public Sala2D() { }

    public Sala2D(int numeroSala, int capacidadAsientos, boolean tieneSonidoDolby) {
        super(numeroSala, capacidadAsientos);
        this.tieneSonidoDolby = tieneSonidoDolby;
    }

    public boolean getTieneSonidoDolby() { return tieneSonidoDolby; }
    public void setTieneSonidoDolby(boolean tieneSonidoDolby) { this.tieneSonidoDolby = tieneSonidoDolby; }

    @Override
    public String obtenerTipoPantalla() { return "2D - Sonido " + (tieneSonidoDolby ? "Dolby" : "estandar"); }
}

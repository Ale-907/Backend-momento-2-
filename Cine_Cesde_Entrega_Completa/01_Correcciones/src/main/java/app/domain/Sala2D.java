package app.domain;

public class Sala2D extends Sala{

    private boolean tieneSonidoDolby;

    public Sala2D(int numeroSala, int capacidadAsientos, boolean tieneSonidoDolby) {
        super(numeroSala, capacidadAsientos);
        this.tieneSonidoDolby = tieneSonidoDolby;
    }

    @Override
    public String obtenerTipoPantalla() {
        return "Pantalla 2D Estándar (Sonido: " + (tieneSonidoDolby ? "Dolby Atmos" : "Estándar") + ")";
    }
}

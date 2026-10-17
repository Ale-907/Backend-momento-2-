package app.domain;

public class Sala3D extends Sala{
    private String versionGafas;

    public Sala3D(int numeroSala, int capacidadAsientos, String versionGafas) {
        super(numeroSala, capacidadAsientos);
        this.versionGafas = versionGafas;
    }

    @Override
    public String obtenerTipoPantalla() {
        return "Pantalla RealD 3D (Lentes " + versionGafas + ")";
    }
}

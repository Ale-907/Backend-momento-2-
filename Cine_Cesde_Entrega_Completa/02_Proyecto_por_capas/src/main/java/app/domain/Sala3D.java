package app.domain;


public class Sala3D extends Sala {
    private String versionGafas;

    public Sala3D() { }

    public Sala3D(int numeroSala, int capacidadAsientos, String versionGafas) {
        super(numeroSala, capacidadAsientos);
        this.versionGafas = versionGafas;
    }

    public String getVersionGafas() { return versionGafas; }
    public void setVersionGafas(String versionGafas) { this.versionGafas = versionGafas; }

    @Override
    public String obtenerTipoPantalla() { return "3D - Gafas " + versionGafas; }
}

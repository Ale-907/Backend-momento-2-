package app.domain;


public class Cliente {
    private String cedula;
    private String nombre;
    private int puntosCesde;

    public Cliente() { }

    public Cliente(String cedula, String nombre, int puntosCesde) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.puntosCesde = puntosCesde;
    }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getPuntosCesde() { return puntosCesde; }
    public void setPuntosCesde(int puntosCesde) { this.puntosCesde = puntosCesde; }
}

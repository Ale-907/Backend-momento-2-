package app.domain;

public class Cliente {

    private String cedula;
    private String nombre;
    private int puntosCesde;

    public Cliente(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.puntosCesde = 0;
    }

    public String getCedula() { return cedula; }

    public String getNombre() { return nombre; }
    public int getPuntosCesde() { return puntosCesde; }

    public void acumularPuntos(int valorCompra) {
        if (valorCompra > 0) {
            this.puntosCesde += (valorCompra / 1000);
        }
    }
}

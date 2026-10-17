package app.domain;


public class ComboComida {
    private int id;
    private String nombreCombo;
    private double precio;

    public ComboComida() { }

    public ComboComida(int id, String nombreCombo, double precio) {
        this.id = id;
        this.nombreCombo = nombreCombo;
        this.precio = precio;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreCombo() { return nombreCombo; }
    public void setNombreCombo(String nombreCombo) { this.nombreCombo = nombreCombo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}

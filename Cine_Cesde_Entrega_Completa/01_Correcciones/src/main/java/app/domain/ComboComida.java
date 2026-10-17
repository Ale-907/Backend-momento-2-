package app.domain;

public class ComboComida {

    private String nombreCombo;
    private double precio;

    public ComboComida(String nombreCombo, double precio) {
        this.nombreCombo = nombreCombo;
        setPrecio(precio);
    }

    public String getNombreCombo() { return nombreCombo; }
    public double getPrecio() { return precio; }

    public void setPrecio(double precio) {
        if (Double.isFinite(precio) && precio > 0) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException("El precio del combo debe ser positivo.");
        }
    }
}

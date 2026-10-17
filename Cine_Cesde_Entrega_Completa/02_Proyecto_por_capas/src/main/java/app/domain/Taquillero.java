package app.domain;


public class Taquillero extends Empleado {
    private int boletasVendidas;

    public Taquillero() { }

    public Taquillero(String idEmpleado, String nombre, double salarioBase, int boletasVendidas) {
        super(idEmpleado, nombre, salarioBase);
        this.boletasVendidas = boletasVendidas;
    }

    public int getBoletasVendidas() { return boletasVendidas; }
    public void setBoletasVendidas(int boletasVendidas) { this.boletasVendidas = boletasVendidas; }

    @Override
    public double calcularSueldo() {
        return getSalarioBase() + (boletasVendidas * 500.0);
    }
}

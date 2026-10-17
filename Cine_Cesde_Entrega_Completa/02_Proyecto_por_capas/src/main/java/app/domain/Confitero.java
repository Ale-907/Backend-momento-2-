package app.domain;


public class Confitero extends Empleado {
    private int combosVendidos;

    public Confitero() { }

    public Confitero(String idEmpleado, String nombre, double salarioBase, int combosVendidos) {
        super(idEmpleado, nombre, salarioBase);
        this.combosVendidos = combosVendidos;
    }

    public int getCombosVendidos() { return combosVendidos; }
    public void setCombosVendidos(int combosVendidos) { this.combosVendidos = combosVendidos; }

    @Override
    public double calcularSueldo() {
        return getSalarioBase() + (combosVendidos * 1000.0);
    }
}

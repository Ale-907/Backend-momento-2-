package app.domain;

public class Confitero extends Empleado{

    private int combosVendidos;

    public Confitero(String idEmpleado, String nombre, double salarioBase, int combosVendidos) {
        super(idEmpleado, nombre, salarioBase);
        this.combosVendidos = combosVendidos;
    }

    @Override
    public double calcularSueldo() {
        return getSalarioBase() + (combosVendidos * 1000);
    }
}

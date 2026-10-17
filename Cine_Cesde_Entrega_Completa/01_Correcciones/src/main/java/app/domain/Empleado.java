package app.domain;

public abstract class Empleado {

    private String idEmpleado;
    private String nombre;
    private double salarioBase;

    public Empleado(String idEmpleado, String nombre, double salarioBase) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

    public String getIdEmpleado() { return idEmpleado; }

    public String getNombre() { return nombre; }
    public double getSalarioBase() { return salarioBase; }

    public abstract double calcularSueldo();
}

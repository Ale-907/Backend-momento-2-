package app.domain;


public abstract class Empleado {
    private String idEmpleado;
    private String nombre;
    private double salarioBase;

    public Empleado() { }

    public Empleado(String idEmpleado, String nombre, double salarioBase) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

    public String getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(String idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    public abstract double calcularSueldo();
}

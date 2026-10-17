package app.view;

import app.domain.Taquillero;
import app.service.inputports.TaquilleroServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class TaquilleroView {
    private final TaquilleroServiceInterface service;
    public TaquilleroView(TaquilleroServiceInterface service) { this.service = service; }

    public void createTaquillero() {
        String idEmpleado = FormTypeValidator.stringValidator("ID del empleado");
        String nombre = FormTypeValidator.stringValidator("Nombre");
        double salarioBase = FormTypeValidator.doubleValidator("Salario base");
        int boletasVendidas = FormTypeValidator.intValidator("Boletas vendidas");
        service.createTaquillero(idEmpleado, nombre, salarioBase, boletasVendidas);
        System.out.println("Registro creado.");
    }
    public void selectAll() {
        List<Taquillero> objetos = service.selectAll();
        if (objetos.isEmpty()) { System.out.println("No hay registros."); }
        for (Taquillero objeto : objetos) { mostrar(objeto); }
    }
    public void selectTaquilleroById() {
        String id = FormTypeValidator.stringValidator("ID del empleado");
        Taquillero objeto = service.selectTaquilleroById(id);
        if (objeto == null) { System.out.println("El registro no existe."); }
        else { mostrar(objeto); }
    }
    public void updateTaquillero() {
        String idEmpleado = FormTypeValidator.stringValidator("ID del empleado");
        String nombre = FormTypeValidator.stringValidator("Nombre");
        double salarioBase = FormTypeValidator.doubleValidator("Salario base");
        int boletasVendidas = FormTypeValidator.intValidator("Boletas vendidas");
        service.updateTaquillero(idEmpleado, nombre, salarioBase, boletasVendidas);
        System.out.println("Registro actualizado.");
    }
    public void deleteTaquillero() {
        String id = FormTypeValidator.stringValidator("ID del empleado");
        boolean eliminado = service.deleteTaquillero(id);
        System.out.println(eliminado ? "Registro eliminado." : "El registro no existe.");
    }
    private void mostrar(Taquillero objeto) {
        System.out.println(objeto.getIdEmpleado() + " - " + objeto.getNombre() + " - Boletas: " + objeto.getBoletasVendidas() + " - Sueldo: $" + objeto.calcularSueldo());
    }
}

package app.view;

import app.domain.Confitero;
import app.service.inputports.ConfiteroServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class ConfiteroView {
    private final ConfiteroServiceInterface service;
    public ConfiteroView(ConfiteroServiceInterface service) { this.service = service; }

    public void createConfitero() {
        String idEmpleado = FormTypeValidator.stringValidator("ID del empleado");
        String nombre = FormTypeValidator.stringValidator("Nombre");
        double salarioBase = FormTypeValidator.doubleValidator("Salario base");
        int combosVendidos = FormTypeValidator.intValidator("Combos vendidos");
        service.createConfitero(idEmpleado, nombre, salarioBase, combosVendidos);
        System.out.println("Registro creado.");
    }
    public void selectAll() {
        List<Confitero> objetos = service.selectAll();
        if (objetos.isEmpty()) { System.out.println("No hay registros."); }
        for (Confitero objeto : objetos) { mostrar(objeto); }
    }
    public void selectConfiteroById() {
        String id = FormTypeValidator.stringValidator("ID del empleado");
        Confitero objeto = service.selectConfiteroById(id);
        if (objeto == null) { System.out.println("El registro no existe."); }
        else { mostrar(objeto); }
    }
    public void updateConfitero() {
        String idEmpleado = FormTypeValidator.stringValidator("ID del empleado");
        String nombre = FormTypeValidator.stringValidator("Nombre");
        double salarioBase = FormTypeValidator.doubleValidator("Salario base");
        int combosVendidos = FormTypeValidator.intValidator("Combos vendidos");
        service.updateConfitero(idEmpleado, nombre, salarioBase, combosVendidos);
        System.out.println("Registro actualizado.");
    }
    public void deleteConfitero() {
        String id = FormTypeValidator.stringValidator("ID del empleado");
        boolean eliminado = service.deleteConfitero(id);
        System.out.println(eliminado ? "Registro eliminado." : "El registro no existe.");
    }
    private void mostrar(Confitero objeto) {
        System.out.println(objeto.getIdEmpleado() + " - " + objeto.getNombre() + " - Combos: " + objeto.getCombosVendidos() + " - Sueldo: $" + objeto.calcularSueldo());
    }
}

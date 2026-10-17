package app.view;

import app.domain.ComboComida;
import app.service.inputports.ComboComidaServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class ComboComidaView {
    private final ComboComidaServiceInterface service;
    public ComboComidaView(ComboComidaServiceInterface service) { this.service = service; }

    public void createComboComida() {
        int id = FormTypeValidator.intValidator("ID");
        String nombreCombo = FormTypeValidator.stringValidator("Nombre del combo");
        double precio = FormTypeValidator.doubleValidator("Precio sin puntos de miles");
        service.createComboComida(id, nombreCombo, precio);
        System.out.println("Registro creado.");
    }
    public void selectAll() {
        List<ComboComida> objetos = service.selectAll();
        if (objetos.isEmpty()) { System.out.println("No hay registros."); }
        for (ComboComida objeto : objetos) { mostrar(objeto); }
    }
    public void selectComboComidaById() {
        int id = FormTypeValidator.intValidator("ID");
        ComboComida objeto = service.selectComboComidaById(id);
        if (objeto == null) { System.out.println("El registro no existe."); }
        else { mostrar(objeto); }
    }
    public void updateComboComida() {
        int id = FormTypeValidator.intValidator("ID");
        String nombreCombo = FormTypeValidator.stringValidator("Nombre del combo");
        double precio = FormTypeValidator.doubleValidator("Precio sin puntos de miles");
        service.updateComboComida(id, nombreCombo, precio);
        System.out.println("Registro actualizado.");
    }
    public void deleteComboComida() {
        int id = FormTypeValidator.intValidator("ID");
        boolean eliminado = service.deleteComboComida(id);
        System.out.println(eliminado ? "Registro eliminado." : "El registro no existe.");
    }
    private void mostrar(ComboComida objeto) {
        System.out.println(objeto.getId() + " - " + objeto.getNombreCombo() + " - $" + objeto.getPrecio());
    }
}

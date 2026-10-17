package app.view;

import app.domain.Cliente;
import app.service.inputports.ClienteServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class ClienteView {
    private final ClienteServiceInterface service;
    public ClienteView(ClienteServiceInterface service) { this.service = service; }

    public void createCliente() {
        String cedula = FormTypeValidator.stringValidator("Cedula");
        String nombre = FormTypeValidator.stringValidator("Nombre");
        service.createCliente(cedula, nombre);
        System.out.println("Registro creado.");
    }
    public void selectAll() {
        List<Cliente> objetos = service.selectAll();
        if (objetos.isEmpty()) { System.out.println("No hay registros."); }
        for (Cliente objeto : objetos) { mostrar(objeto); }
    }
    public void selectClienteById() {
        String id = FormTypeValidator.stringValidator("Cedula");
        Cliente objeto = service.selectClienteById(id);
        if (objeto == null) { System.out.println("El registro no existe."); }
        else { mostrar(objeto); }
    }
    public void updateCliente() {
        String cedula = FormTypeValidator.stringValidator("Cedula");
        String nombre = FormTypeValidator.stringValidator("Nombre");
        service.updateCliente(cedula, nombre);
        System.out.println("Registro actualizado.");
    }
    public void deleteCliente() {
        String id = FormTypeValidator.stringValidator("Cedula");
        boolean eliminado = service.deleteCliente(id);
        System.out.println(eliminado ? "Registro eliminado." : "El registro no existe.");
    }
    private void mostrar(Cliente objeto) {
        System.out.println(objeto.getCedula() + " - " + objeto.getNombre() + " - Puntos: " + objeto.getPuntosCesde());
    }
}

package app.view;

import app.domain.Sala2D;
import app.service.inputports.Sala2DServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class Sala2DView {
    private final Sala2DServiceInterface service;
    public Sala2DView(Sala2DServiceInterface service) { this.service = service; }

    public void createSala2D() {
        int numeroSala = FormTypeValidator.intValidator("Numero de sala");
        int capacidadAsientos = FormTypeValidator.intValidator("Cantidad de asientos (1 a 500)");
        boolean tieneSonidoDolby = FormTypeValidator.booleanValidator("Tiene sonido Dolby");
        service.createSala2D(numeroSala, capacidadAsientos, tieneSonidoDolby);
        System.out.println("Registro creado.");
    }
    public void selectAll() {
        List<Sala2D> objetos = service.selectAll();
        if (objetos.isEmpty()) { System.out.println("No hay registros."); }
        for (Sala2D objeto : objetos) { mostrar(objeto); }
    }
    public void selectSala2DById() {
        int id = FormTypeValidator.intValidator("Numero de sala");
        Sala2D objeto = service.selectSala2DById(id);
        if (objeto == null) { System.out.println("El registro no existe."); }
        else { mostrar(objeto); }
    }
    public void updateSala2D() {
        int numeroSala = FormTypeValidator.intValidator("Numero de sala");
        int capacidadAsientos = FormTypeValidator.intValidator("Cantidad de asientos (1 a 500)");
        boolean tieneSonidoDolby = FormTypeValidator.booleanValidator("Tiene sonido Dolby");
        service.updateSala2D(numeroSala, capacidadAsientos, tieneSonidoDolby);
        System.out.println("Registro actualizado.");
    }
    public void deleteSala2D() {
        int id = FormTypeValidator.intValidator("Numero de sala");
        boolean eliminado = service.deleteSala2D(id);
        System.out.println(eliminado ? "Registro eliminado." : "El registro no existe.");
    }
    private void mostrar(Sala2D objeto) {
        System.out.println(objeto.getNumeroSala() + " - " + objeto.obtenerTipoPantalla() + " - Capacidad: " + objeto.getCapacidadAsientos());
        System.out.print("    Asientos: ");
        for (app.domain.Asiento asiento : objeto.getAsientos()) { System.out.print(asiento.getCodigo() + " "); }
        System.out.println();

    }
}

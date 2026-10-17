package app.view;

import app.domain.Sala3D;
import app.service.inputports.Sala3DServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class Sala3DView {
    private final Sala3DServiceInterface service;
    public Sala3DView(Sala3DServiceInterface service) { this.service = service; }

    public void createSala3D() {
        int numeroSala = FormTypeValidator.intValidator("Numero de sala");
        int capacidadAsientos = FormTypeValidator.intValidator("Cantidad de asientos (1 a 500)");
        String versionGafas = FormTypeValidator.stringValidator("Version de las gafas");
        service.createSala3D(numeroSala, capacidadAsientos, versionGafas);
        System.out.println("Registro creado.");
    }
    public void selectAll() {
        List<Sala3D> objetos = service.selectAll();
        if (objetos.isEmpty()) { System.out.println("No hay registros."); }
        for (Sala3D objeto : objetos) { mostrar(objeto); }
    }
    public void selectSala3DById() {
        int id = FormTypeValidator.intValidator("Numero de sala");
        Sala3D objeto = service.selectSala3DById(id);
        if (objeto == null) { System.out.println("El registro no existe."); }
        else { mostrar(objeto); }
    }
    public void updateSala3D() {
        int numeroSala = FormTypeValidator.intValidator("Numero de sala");
        int capacidadAsientos = FormTypeValidator.intValidator("Cantidad de asientos (1 a 500)");
        String versionGafas = FormTypeValidator.stringValidator("Version de las gafas");
        service.updateSala3D(numeroSala, capacidadAsientos, versionGafas);
        System.out.println("Registro actualizado.");
    }
    public void deleteSala3D() {
        int id = FormTypeValidator.intValidator("Numero de sala");
        boolean eliminado = service.deleteSala3D(id);
        System.out.println(eliminado ? "Registro eliminado." : "El registro no existe.");
    }
    private void mostrar(Sala3D objeto) {
        System.out.println(objeto.getNumeroSala() + " - " + objeto.obtenerTipoPantalla() + " - Capacidad: " + objeto.getCapacidadAsientos());
        System.out.print("    Asientos: ");
        for (app.domain.Asiento asiento : objeto.getAsientos()) { System.out.print(asiento.getCodigo() + " "); }
        System.out.println();

    }
}

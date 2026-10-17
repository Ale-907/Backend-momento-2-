package app.view;

import app.domain.Asiento;
import app.service.inputports.AsientoServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class AsientoView {
    private final AsientoServiceInterface service;
    public AsientoView(AsientoServiceInterface service) { this.service = service; }

    public void createAsiento() {
        int numeroSala = FormTypeValidator.intValidator("Numero de sala");
        String codigo = FormTypeValidator.stringValidator("Codigo del asiento");
        service.createAsiento(numeroSala, codigo);
        System.out.println("Registro creado.");
    }
    public void selectAll() {
        List<Asiento> objetos = service.selectAll();
        if (objetos.isEmpty()) { System.out.println("No hay registros."); }
        for (Asiento objeto : objetos) { mostrar(objeto); }
    }
    public void selectAsientoById() {
        String id = FormTypeValidator.stringValidator("ID del asiento (sala:codigo, por ejemplo 1:S1)");
        Asiento objeto = service.selectAsientoById(id);
        if (objeto == null) { System.out.println("El registro no existe."); }
        else { mostrar(objeto); }
    }
    public void updateAsiento() {
        String id = FormTypeValidator.stringValidator("ID del asiento (sala:codigo, por ejemplo 1:S1)");
        String nuevoCodigo = FormTypeValidator.stringValidator("Nuevo codigo");
        service.updateAsiento(id, nuevoCodigo);
        System.out.println("Registro actualizado.");
    }
    public void deleteAsiento() {
        String id = FormTypeValidator.stringValidator("ID del asiento (sala:codigo, por ejemplo 1:S1)");
        boolean eliminado = service.deleteAsiento(id);
        System.out.println(eliminado ? "Registro eliminado." : "El registro no existe.");
    }
    private void mostrar(Asiento objeto) {
        System.out.println(objeto.getId() + " - Sala " + objeto.getNumeroSala() + " - Codigo " + objeto.getCodigo());
    }
}

package app.view;

import app.domain.Cine;
import app.service.inputports.CineServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class CineView {
    private final CineServiceInterface service;
    public CineView(CineServiceInterface service) { this.service = service; }

    public void createCine() {
        int id = FormTypeValidator.intValidator("ID");
        String nombre = FormTypeValidator.stringValidator("Nombre");
        service.createCine(id, nombre);
        System.out.println("Registro creado.");
    }
    public void selectAll() {
        List<Cine> objetos = service.selectAll();
        if (objetos.isEmpty()) { System.out.println("No hay registros."); }
        for (Cine objeto : objetos) { mostrar(objeto); }
    }
    public void selectCineById() {
        int id = FormTypeValidator.intValidator("ID");
        Cine objeto = service.selectCineById(id);
        if (objeto == null) { System.out.println("El registro no existe."); }
        else { mostrar(objeto); }
    }
    public void updateCine() {
        int id = FormTypeValidator.intValidator("ID");
        String nombre = FormTypeValidator.stringValidator("Nombre");
        service.updateCine(id, nombre);
        System.out.println("Registro actualizado.");
    }
    public void deleteCine() {
        int id = FormTypeValidator.intValidator("ID");
        boolean eliminado = service.deleteCine(id);
        System.out.println(eliminado ? "Registro eliminado." : "El registro no existe.");
    }
    private void mostrar(Cine objeto) {
        System.out.println(objeto.getId() + " - " + objeto.getNombre() + " - Peliculas: " + objeto.getPeliculas().size());
        for (app.domain.Pelicula pelicula : objeto.getPeliculas()) {
            System.out.println("    " + pelicula.getId() + " - " + pelicula.getTitulo());
        }

    }

    public void agregarPelicula() {
        int cine = FormTypeValidator.intValidator("ID del cine");
        int pelicula = FormTypeValidator.intValidator("ID de la pelicula");
        service.agregarPelicula(cine, pelicula);
        System.out.println("Pelicula agregada al catalogo.");
    }
    public void retirarPelicula() {
        int cine = FormTypeValidator.intValidator("ID del cine");
        int pelicula = FormTypeValidator.intValidator("ID de la pelicula");
        service.retirarPelicula(cine, pelicula);
        System.out.println("Pelicula retirada del catalogo.");
    }
}

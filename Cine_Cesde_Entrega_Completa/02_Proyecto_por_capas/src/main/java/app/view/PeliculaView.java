package app.view;

import app.domain.Pelicula;
import app.domain.enums.ClasificacionEnum;
import app.service.inputports.PeliculaServiceInterface;
import app.service.validations.FormTypeValidator;
import app.service.helpers.SetClasificacionHelper;
import java.util.List;

public class PeliculaView {
    private final PeliculaServiceInterface service;
    public PeliculaView(PeliculaServiceInterface service) { this.service = service; }

    public void createPelicula() {
        int id = FormTypeValidator.intValidator("ID");
        String titulo = FormTypeValidator.stringValidator("Titulo");
        int duracionMinutos = FormTypeValidator.intValidator("Duracion en minutos");
        ClasificacionEnum clasificacion = SetClasificacionHelper.seleccionarClasificacion();
        service.createPelicula(id, titulo, duracionMinutos, clasificacion);
        System.out.println("Registro creado.");
    }
    public void selectAll() {
        List<Pelicula> objetos = service.selectAll();
        if (objetos.isEmpty()) { System.out.println("No hay registros."); }
        for (Pelicula objeto : objetos) { mostrar(objeto); }
    }
    public void selectPeliculaById() {
        int id = FormTypeValidator.intValidator("ID");
        Pelicula objeto = service.selectPeliculaById(id);
        if (objeto == null) { System.out.println("El registro no existe."); }
        else { mostrar(objeto); }
    }
    public void updatePelicula() {
        int id = FormTypeValidator.intValidator("ID");
        String titulo = FormTypeValidator.stringValidator("Titulo");
        int duracionMinutos = FormTypeValidator.intValidator("Duracion en minutos");
        ClasificacionEnum clasificacion = SetClasificacionHelper.seleccionarClasificacion();
        service.updatePelicula(id, titulo, duracionMinutos, clasificacion);
        System.out.println("Registro actualizado.");
    }
    public void deletePelicula() {
        int id = FormTypeValidator.intValidator("ID");
        boolean eliminado = service.deletePelicula(id);
        System.out.println(eliminado ? "Registro eliminado." : "El registro no existe.");
    }
    private void mostrar(Pelicula objeto) {
        System.out.println(objeto.getId() + " - " + objeto.getTitulo() + " - " + objeto.getDuracionMinutos() + " min - " + objeto.getClasificacion().getValor());
    }
}

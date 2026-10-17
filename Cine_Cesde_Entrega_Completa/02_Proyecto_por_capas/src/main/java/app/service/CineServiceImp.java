package app.service;

import app.domain.*;
import app.service.inputports.CineServiceInterface;
import app.service.outputports.CineRepositoryPort;
import app.service.helpers.DatosRelacionados;
import app.service.validations.Validaciones;
import java.util.List;

public class CineServiceImp implements CineServiceInterface {
    private final CineRepositoryPort repository;
    private final DatosRelacionados datos;

    public CineServiceImp(CineRepositoryPort repository, DatosRelacionados datos) {
        this.repository = repository;
        this.datos = datos;
    }
    @Override
    public Cine createCine(int id, String nombre) {
        Validaciones.positivo(id, "id");
        Validaciones.texto(nombre, "nombre");
        Validaciones.libre(repository.selectCineById(id) != null, "El identificador ya existe.");
        
        
        Cine objeto = new Cine(id, nombre);
        repository.saveCine(objeto);
        
        return objeto;
    }
    @Override
    public Cine selectCineById(int id) { return repository.selectCineById(id); }

    @Override
    public List<Cine> selectAll() { return repository.selectAll(); }

    @Override
    public Cine updateCine(int id, String nombre) {
        Validaciones.positivo(id, "id");
        Validaciones.texto(nombre, "nombre");
        Cine objeto = repository.selectCineById(id);
        Validaciones.existe(objeto, "El registro no existe.");
        
        
        objeto.setNombre(nombre);
        repository.updateCine(objeto);
        
        return objeto;
    }
    @Override
    public boolean deleteCine(int id) {
        
        boolean eliminado = repository.deleteCine(id);
        
        return eliminado;
    }

    @Override
    public void agregarPelicula(int idCine, int idPelicula) {
        Cine cine = selectCineById(idCine);
        Pelicula pelicula = datos.buscarPelicula(idPelicula);
        Validaciones.existe(cine, "El cine no existe.");
        Validaciones.existe(pelicula, "La pelicula no existe.");
        cine.agregarPelicula(pelicula);
        repository.updateCine(cine);
    }
    @Override
    public void retirarPelicula(int idCine, int idPelicula) {
        Cine cine = selectCineById(idCine);
        Pelicula pelicula = datos.buscarPelicula(idPelicula);
        Validaciones.existe(cine, "El cine no existe.");
        Validaciones.existe(pelicula, "La pelicula no existe.");
        cine.retirarPelicula(pelicula);
        repository.updateCine(cine);
    }
}

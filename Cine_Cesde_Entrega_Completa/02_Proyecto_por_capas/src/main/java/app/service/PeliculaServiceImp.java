package app.service;

import app.domain.*;
import app.domain.enums.ClasificacionEnum;
import app.service.inputports.PeliculaServiceInterface;
import app.service.outputports.PeliculaRepositoryPort;
import app.service.helpers.DatosRelacionados;
import app.service.validations.Validaciones;
import java.util.List;

public class PeliculaServiceImp implements PeliculaServiceInterface {
    private final PeliculaRepositoryPort repository;
    private final DatosRelacionados datos;

    public PeliculaServiceImp(PeliculaRepositoryPort repository, DatosRelacionados datos) {
        this.repository = repository;
        this.datos = datos;
    }
    @Override
    public Pelicula createPelicula(int id, String titulo, int duracionMinutos, ClasificacionEnum clasificacion) {
        Validaciones.positivo(id, "id");
        Validaciones.texto(titulo, "titulo");
        Validaciones.positivo(duracionMinutos, "duracionMinutos");
        Validaciones.existe(clasificacion, "Seleccione una clasificacion.");
        Validaciones.libre(repository.selectPeliculaById(id) != null, "El identificador ya existe.");
        
        
        Pelicula objeto = new Pelicula(id, titulo, duracionMinutos, clasificacion);
        repository.savePelicula(objeto);
        
        return objeto;
    }
    @Override
    public Pelicula selectPeliculaById(int id) { return repository.selectPeliculaById(id); }

    @Override
    public List<Pelicula> selectAll() { return repository.selectAll(); }

    @Override
    public Pelicula updatePelicula(int id, String titulo, int duracionMinutos, ClasificacionEnum clasificacion) {
        Validaciones.positivo(id, "id");
        Validaciones.texto(titulo, "titulo");
        Validaciones.positivo(duracionMinutos, "duracionMinutos");
        Validaciones.existe(clasificacion, "Seleccione una clasificacion.");
        Pelicula objeto = repository.selectPeliculaById(id);
        Validaciones.existe(objeto, "El registro no existe.");
        
        
        objeto.setTitulo(titulo);
        objeto.setDuracionMinutos(duracionMinutos);
        objeto.setClasificacion(clasificacion);
        repository.updatePelicula(objeto);
        
        return objeto;
    }
    @Override
    public boolean deletePelicula(int id) {
        Validaciones.libre(datos.peliculaEnUso(id), "Retire primero la pelicula del catalogo y los tickets.");
        boolean eliminado = repository.deletePelicula(id);
        
        return eliminado;
    }
}

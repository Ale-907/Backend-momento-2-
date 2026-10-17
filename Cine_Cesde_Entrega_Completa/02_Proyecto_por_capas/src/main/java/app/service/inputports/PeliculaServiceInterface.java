package app.service.inputports;

import app.domain.Pelicula;
import app.domain.enums.ClasificacionEnum;
import java.util.List;

public interface PeliculaServiceInterface {
    Pelicula createPelicula(int id, String titulo, int duracionMinutos, ClasificacionEnum clasificacion);
    Pelicula selectPeliculaById(int id);
    List<Pelicula> selectAll();
    Pelicula updatePelicula(int id, String titulo, int duracionMinutos, ClasificacionEnum clasificacion);
    boolean deletePelicula(int id);
}

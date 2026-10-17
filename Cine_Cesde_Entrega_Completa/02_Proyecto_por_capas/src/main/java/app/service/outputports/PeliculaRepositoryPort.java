package app.service.outputports;

import app.domain.Pelicula;
import java.util.List;

public interface PeliculaRepositoryPort {
    Pelicula savePelicula(Pelicula objeto);
    Pelicula selectPeliculaById(int id);
    List<Pelicula> selectAll();
    Pelicula updatePelicula(Pelicula objeto);
    boolean deletePelicula(int id);
}

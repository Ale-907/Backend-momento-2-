package app.service.inputports;

import app.domain.Cine;
import java.util.List;

public interface CineServiceInterface {
    Cine createCine(int id, String nombre);
    Cine selectCineById(int id);
    List<Cine> selectAll();
    Cine updateCine(int id, String nombre);
    boolean deleteCine(int id);
    void agregarPelicula(int idCine, int idPelicula);
    void retirarPelicula(int idCine, int idPelicula);
}

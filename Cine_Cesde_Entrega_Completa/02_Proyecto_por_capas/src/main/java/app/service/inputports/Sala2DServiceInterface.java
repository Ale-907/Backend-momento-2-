package app.service.inputports;

import app.domain.Sala2D;
import java.util.List;

public interface Sala2DServiceInterface {
    Sala2D createSala2D(int numeroSala, int capacidadAsientos, boolean tieneSonidoDolby);
    Sala2D selectSala2DById(int id);
    List<Sala2D> selectAll();
    Sala2D updateSala2D(int numeroSala, int capacidadAsientos, boolean tieneSonidoDolby);
    boolean deleteSala2D(int id);
}

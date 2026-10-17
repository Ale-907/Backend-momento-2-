package app.service.outputports;

import app.domain.Sala2D;
import java.util.List;

public interface Sala2DRepositoryPort {
    Sala2D saveSala2D(Sala2D objeto);
    Sala2D selectSala2DById(int id);
    List<Sala2D> selectAll();
    Sala2D updateSala2D(Sala2D objeto);
    boolean deleteSala2D(int id);
}

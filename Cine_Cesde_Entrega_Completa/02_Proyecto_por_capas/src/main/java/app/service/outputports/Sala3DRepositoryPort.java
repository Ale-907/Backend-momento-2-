package app.service.outputports;

import app.domain.Sala3D;
import java.util.List;

public interface Sala3DRepositoryPort {
    Sala3D saveSala3D(Sala3D objeto);
    Sala3D selectSala3DById(int id);
    List<Sala3D> selectAll();
    Sala3D updateSala3D(Sala3D objeto);
    boolean deleteSala3D(int id);
}

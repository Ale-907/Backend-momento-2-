package app.service.inputports;

import app.domain.Sala3D;
import java.util.List;

public interface Sala3DServiceInterface {
    Sala3D createSala3D(int numeroSala, int capacidadAsientos, String versionGafas);
    Sala3D selectSala3DById(int id);
    List<Sala3D> selectAll();
    Sala3D updateSala3D(int numeroSala, int capacidadAsientos, String versionGafas);
    boolean deleteSala3D(int id);
}

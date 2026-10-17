package app.service.outputports;

import app.domain.Cine;
import java.util.List;

public interface CineRepositoryPort {
    Cine saveCine(Cine objeto);
    Cine selectCineById(int id);
    List<Cine> selectAll();
    Cine updateCine(Cine objeto);
    boolean deleteCine(int id);
}

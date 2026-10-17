package app.service.outputports;

import app.domain.Asiento;
import java.util.List;

public interface AsientoRepositoryPort {
    Asiento saveAsiento(Asiento objeto);
    Asiento selectAsientoById(String id);
    List<Asiento> selectAll();
    Asiento updateAsiento(Asiento objeto);
    boolean deleteAsiento(String id);
}

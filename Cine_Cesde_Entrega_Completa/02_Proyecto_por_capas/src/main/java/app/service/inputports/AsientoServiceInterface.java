package app.service.inputports;

import app.domain.Asiento;
import java.util.List;

public interface AsientoServiceInterface {
    Asiento createAsiento(int numeroSala, String codigo);
    Asiento selectAsientoById(String id);
    List<Asiento> selectAll();
    Asiento updateAsiento(String id, String nuevoCodigo);
    boolean deleteAsiento(String id);
}

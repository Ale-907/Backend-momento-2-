package app.service.outputports;

import app.domain.Taquillero;
import java.util.List;

public interface TaquilleroRepositoryPort {
    Taquillero saveTaquillero(Taquillero objeto);
    Taquillero selectTaquilleroById(String id);
    List<Taquillero> selectAll();
    Taquillero updateTaquillero(Taquillero objeto);
    boolean deleteTaquillero(String id);
}

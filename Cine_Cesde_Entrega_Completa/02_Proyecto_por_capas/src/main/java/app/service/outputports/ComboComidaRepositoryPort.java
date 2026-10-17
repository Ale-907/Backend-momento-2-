package app.service.outputports;

import app.domain.ComboComida;
import java.util.List;

public interface ComboComidaRepositoryPort {
    ComboComida saveComboComida(ComboComida objeto);
    ComboComida selectComboComidaById(int id);
    List<ComboComida> selectAll();
    ComboComida updateComboComida(ComboComida objeto);
    boolean deleteComboComida(int id);
}

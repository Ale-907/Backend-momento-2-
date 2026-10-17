package app.service.inputports;

import app.domain.ComboComida;
import java.util.List;

public interface ComboComidaServiceInterface {
    ComboComida createComboComida(int id, String nombreCombo, double precio);
    ComboComida selectComboComidaById(int id);
    List<ComboComida> selectAll();
    ComboComida updateComboComida(int id, String nombreCombo, double precio);
    boolean deleteComboComida(int id);
}

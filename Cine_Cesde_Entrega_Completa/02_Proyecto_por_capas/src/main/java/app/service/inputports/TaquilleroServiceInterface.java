package app.service.inputports;

import app.domain.Taquillero;
import java.util.List;

public interface TaquilleroServiceInterface {
    Taquillero createTaquillero(String idEmpleado, String nombre, double salarioBase, int boletasVendidas);
    Taquillero selectTaquilleroById(String id);
    List<Taquillero> selectAll();
    Taquillero updateTaquillero(String idEmpleado, String nombre, double salarioBase, int boletasVendidas);
    boolean deleteTaquillero(String id);
}

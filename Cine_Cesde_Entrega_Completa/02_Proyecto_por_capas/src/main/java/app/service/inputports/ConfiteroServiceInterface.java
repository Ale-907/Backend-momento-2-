package app.service.inputports;

import app.domain.Confitero;
import java.util.List;

public interface ConfiteroServiceInterface {
    Confitero createConfitero(String idEmpleado, String nombre, double salarioBase, int combosVendidos);
    Confitero selectConfiteroById(String id);
    List<Confitero> selectAll();
    Confitero updateConfitero(String idEmpleado, String nombre, double salarioBase, int combosVendidos);
    boolean deleteConfitero(String id);
}

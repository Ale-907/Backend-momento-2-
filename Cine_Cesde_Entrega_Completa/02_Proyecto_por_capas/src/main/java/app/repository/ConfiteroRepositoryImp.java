package app.repository;

import app.domain.Confitero;
import app.service.outputports.ConfiteroRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class ConfiteroRepositoryImp implements ConfiteroRepositoryPort {
    private final List<Confitero> objetos = new ArrayList<>();

    @Override
    public Confitero saveConfitero(Confitero objeto) {
        objetos.add(objeto);
        return objeto;
    }
    @Override
    public Confitero selectConfiteroById(String id) {
        for (Confitero objeto : objetos) {
            if (objeto.getIdEmpleado().equals(id)) { return objeto; }
        }
        return null;
    }
    @Override
    public List<Confitero> selectAll() { return new ArrayList<>(objetos); }

    @Override
    public Confitero updateConfitero(Confitero objeto) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getIdEmpleado().equals(objeto.getIdEmpleado())) {
                objetos.set(i, objeto);
                return objeto;
            }
        }
        return null;
    }
    @Override
    public boolean deleteConfitero(String id) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getIdEmpleado().equals(id)) { objetos.remove(i); return true; }
        }
        return false;
    }
}

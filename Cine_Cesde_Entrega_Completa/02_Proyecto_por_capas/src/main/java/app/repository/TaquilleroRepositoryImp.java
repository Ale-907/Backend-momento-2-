package app.repository;

import app.domain.Taquillero;
import app.service.outputports.TaquilleroRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class TaquilleroRepositoryImp implements TaquilleroRepositoryPort {
    private final List<Taquillero> objetos = new ArrayList<>();

    @Override
    public Taquillero saveTaquillero(Taquillero objeto) {
        objetos.add(objeto);
        return objeto;
    }
    @Override
    public Taquillero selectTaquilleroById(String id) {
        for (Taquillero objeto : objetos) {
            if (objeto.getIdEmpleado().equals(id)) { return objeto; }
        }
        return null;
    }
    @Override
    public List<Taquillero> selectAll() { return new ArrayList<>(objetos); }

    @Override
    public Taquillero updateTaquillero(Taquillero objeto) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getIdEmpleado().equals(objeto.getIdEmpleado())) {
                objetos.set(i, objeto);
                return objeto;
            }
        }
        return null;
    }
    @Override
    public boolean deleteTaquillero(String id) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getIdEmpleado().equals(id)) { objetos.remove(i); return true; }
        }
        return false;
    }
}

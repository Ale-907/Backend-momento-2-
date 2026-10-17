package app.repository;

import app.domain.Cine;
import app.service.outputports.CineRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class CineRepositoryImp implements CineRepositoryPort {
    private final List<Cine> objetos = new ArrayList<>();

    @Override
    public Cine saveCine(Cine objeto) {
        objetos.add(objeto);
        return objeto;
    }
    @Override
    public Cine selectCineById(int id) {
        for (Cine objeto : objetos) {
            if (objeto.getId() == id) { return objeto; }
        }
        return null;
    }
    @Override
    public List<Cine> selectAll() { return new ArrayList<>(objetos); }

    @Override
    public Cine updateCine(Cine objeto) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getId() == objeto.getId()) {
                objetos.set(i, objeto);
                return objeto;
            }
        }
        return null;
    }
    @Override
    public boolean deleteCine(int id) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getId() == id) { objetos.remove(i); return true; }
        }
        return false;
    }
}

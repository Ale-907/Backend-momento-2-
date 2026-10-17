package app.repository;

import app.domain.Sala2D;
import app.service.outputports.Sala2DRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class Sala2DRepositoryImp implements Sala2DRepositoryPort {
    private final List<Sala2D> objetos = new ArrayList<>();

    @Override
    public Sala2D saveSala2D(Sala2D objeto) {
        objetos.add(objeto);
        return objeto;
    }
    @Override
    public Sala2D selectSala2DById(int id) {
        for (Sala2D objeto : objetos) {
            if (objeto.getNumeroSala() == id) { return objeto; }
        }
        return null;
    }
    @Override
    public List<Sala2D> selectAll() { return new ArrayList<>(objetos); }

    @Override
    public Sala2D updateSala2D(Sala2D objeto) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getNumeroSala() == objeto.getNumeroSala()) {
                objetos.set(i, objeto);
                return objeto;
            }
        }
        return null;
    }
    @Override
    public boolean deleteSala2D(int id) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getNumeroSala() == id) { objetos.remove(i); return true; }
        }
        return false;
    }
}

package app.repository;

import app.domain.Sala3D;
import app.service.outputports.Sala3DRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class Sala3DRepositoryImp implements Sala3DRepositoryPort {
    private final List<Sala3D> objetos = new ArrayList<>();

    @Override
    public Sala3D saveSala3D(Sala3D objeto) {
        objetos.add(objeto);
        return objeto;
    }
    @Override
    public Sala3D selectSala3DById(int id) {
        for (Sala3D objeto : objetos) {
            if (objeto.getNumeroSala() == id) { return objeto; }
        }
        return null;
    }
    @Override
    public List<Sala3D> selectAll() { return new ArrayList<>(objetos); }

    @Override
    public Sala3D updateSala3D(Sala3D objeto) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getNumeroSala() == objeto.getNumeroSala()) {
                objetos.set(i, objeto);
                return objeto;
            }
        }
        return null;
    }
    @Override
    public boolean deleteSala3D(int id) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getNumeroSala() == id) { objetos.remove(i); return true; }
        }
        return false;
    }
}

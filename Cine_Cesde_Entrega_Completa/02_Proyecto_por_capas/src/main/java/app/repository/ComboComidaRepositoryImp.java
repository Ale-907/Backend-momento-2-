package app.repository;

import app.domain.ComboComida;
import app.service.outputports.ComboComidaRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class ComboComidaRepositoryImp implements ComboComidaRepositoryPort {
    private final List<ComboComida> objetos = new ArrayList<>();

    @Override
    public ComboComida saveComboComida(ComboComida objeto) {
        objetos.add(objeto);
        return objeto;
    }
    @Override
    public ComboComida selectComboComidaById(int id) {
        for (ComboComida objeto : objetos) {
            if (objeto.getId() == id) { return objeto; }
        }
        return null;
    }
    @Override
    public List<ComboComida> selectAll() { return new ArrayList<>(objetos); }

    @Override
    public ComboComida updateComboComida(ComboComida objeto) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getId() == objeto.getId()) {
                objetos.set(i, objeto);
                return objeto;
            }
        }
        return null;
    }
    @Override
    public boolean deleteComboComida(int id) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getId() == id) { objetos.remove(i); return true; }
        }
        return false;
    }
}

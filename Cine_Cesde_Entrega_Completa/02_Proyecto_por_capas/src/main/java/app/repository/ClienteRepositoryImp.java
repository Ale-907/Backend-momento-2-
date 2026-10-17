package app.repository;

import app.domain.Cliente;
import app.service.outputports.ClienteRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImp implements ClienteRepositoryPort {
    private final List<Cliente> objetos = new ArrayList<>();

    @Override
    public Cliente saveCliente(Cliente objeto) {
        objetos.add(objeto);
        return objeto;
    }
    @Override
    public Cliente selectClienteById(String id) {
        for (Cliente objeto : objetos) {
            if (objeto.getCedula().equals(id)) { return objeto; }
        }
        return null;
    }
    @Override
    public List<Cliente> selectAll() { return new ArrayList<>(objetos); }

    @Override
    public Cliente updateCliente(Cliente objeto) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getCedula().equals(objeto.getCedula())) {
                objetos.set(i, objeto);
                return objeto;
            }
        }
        return null;
    }
    @Override
    public boolean deleteCliente(String id) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getCedula().equals(id)) { objetos.remove(i); return true; }
        }
        return false;
    }
}

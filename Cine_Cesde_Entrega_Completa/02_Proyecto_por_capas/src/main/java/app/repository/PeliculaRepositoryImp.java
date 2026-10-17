package app.repository;

import app.domain.Pelicula;
import app.service.outputports.PeliculaRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class PeliculaRepositoryImp implements PeliculaRepositoryPort {
    private final List<Pelicula> objetos = new ArrayList<>();

    @Override
    public Pelicula savePelicula(Pelicula objeto) {
        objetos.add(objeto);
        return objeto;
    }
    @Override
    public Pelicula selectPeliculaById(int id) {
        for (Pelicula objeto : objetos) {
            if (objeto.getId() == id) { return objeto; }
        }
        return null;
    }
    @Override
    public List<Pelicula> selectAll() { return new ArrayList<>(objetos); }

    @Override
    public Pelicula updatePelicula(Pelicula objeto) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getId() == objeto.getId()) {
                objetos.set(i, objeto);
                return objeto;
            }
        }
        return null;
    }
    @Override
    public boolean deletePelicula(int id) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getId() == id) { objetos.remove(i); return true; }
        }
        return false;
    }
}

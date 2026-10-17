package app.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cine {
    private String nombre;
    private List<Pelicula> peliculas = new ArrayList<>();

    public Cine(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Agregacion: la pelicula se crea afuera y luego se agrega al catalogo.
    public void agregarPelicula(Pelicula pelicula) {
        if (pelicula == null) {
            throw new IllegalArgumentException("La pelicula es obligatoria.");
        }
        if (!peliculas.contains(pelicula)) {
            peliculas.add(pelicula);
        }
    }

    public List<Pelicula> getPeliculas() {
        return Collections.unmodifiableList(peliculas);
    }
}

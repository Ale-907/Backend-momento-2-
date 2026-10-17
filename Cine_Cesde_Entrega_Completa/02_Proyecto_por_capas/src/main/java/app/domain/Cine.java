package app.domain;

import java.util.ArrayList;
import java.util.List;

public class Cine {
    private int id;
    private String nombre;
    private List<Pelicula> peliculas = new ArrayList<>();

    public Cine() { }

    public Cine(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<Pelicula> getPeliculas() { return new ArrayList<>(peliculas); }
    public void setPeliculas(List<Pelicula> peliculas) { this.peliculas = new ArrayList<>(peliculas); }

    public void agregarPelicula(Pelicula pelicula) {
        if (!peliculas.contains(pelicula)) { peliculas.add(pelicula); }
    }

    public boolean retirarPelicula(Pelicula pelicula) {
        return peliculas.remove(pelicula);
    }
}

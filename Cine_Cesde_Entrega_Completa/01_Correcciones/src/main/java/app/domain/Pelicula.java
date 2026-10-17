package app.domain;

public class Pelicula {

    private String titulo;
    private int duracionMinutos;
    private String clasificacion;

    public Pelicula(String titulo, int duracionMinutos, String clasificacion) {
        this.titulo = titulo;
        this.duracionMinutos = duracionMinutos;
        this.clasificacion = clasificacion;
    }

    public int getDuracionMinutos() { return duracionMinutos; }

    public String getTitulo() { return titulo; }
    public String getClasificacion() { return clasificacion; }
}

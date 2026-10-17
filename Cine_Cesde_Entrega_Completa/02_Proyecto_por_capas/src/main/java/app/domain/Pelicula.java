package app.domain;

import app.domain.enums.ClasificacionEnum;

public class Pelicula {
    private int id;
    private String titulo;
    private int duracionMinutos;
    private ClasificacionEnum clasificacion;

    public Pelicula() { }

    public Pelicula(int id, String titulo, int duracionMinutos, ClasificacionEnum clasificacion) {
        this.id = id;
        this.titulo = titulo;
        this.duracionMinutos = duracionMinutos;
        this.clasificacion = clasificacion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }

    public ClasificacionEnum getClasificacion() { return clasificacion; }
    public void setClasificacion(ClasificacionEnum clasificacion) { this.clasificacion = clasificacion; }
}

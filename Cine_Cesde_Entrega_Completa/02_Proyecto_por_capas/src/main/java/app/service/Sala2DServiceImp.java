package app.service;

import app.domain.*;
import app.service.inputports.Sala2DServiceInterface;
import app.service.outputports.Sala2DRepositoryPort;
import app.service.helpers.DatosRelacionados;
import app.service.validations.Validaciones;
import java.util.List;

public class Sala2DServiceImp implements Sala2DServiceInterface {
    private final Sala2DRepositoryPort repository;
    private final DatosRelacionados datos;

    public Sala2DServiceImp(Sala2DRepositoryPort repository, DatosRelacionados datos) {
        this.repository = repository;
        this.datos = datos;
    }
    @Override
    public Sala2D createSala2D(int numeroSala, int capacidadAsientos, boolean tieneSonidoDolby) {
        Validaciones.positivo(numeroSala, "numeroSala");
        Validaciones.positivo(capacidadAsientos, "capacidadAsientos");
        if (capacidadAsientos > 500) { throw new IllegalArgumentException("Maximo 500 asientos en este ejercicio."); }
        Validaciones.libre(repository.selectSala2DById(numeroSala) != null, "El identificador ya existe.");
        Validaciones.libre(datos.buscarSala(numeroSala) != null, "El numero ya existe en otra sala.");
        
        Sala2D objeto = new Sala2D(numeroSala, capacidadAsientos, tieneSonidoDolby);
        repository.saveSala2D(objeto);
        
        return objeto;
    }
    @Override
    public Sala2D selectSala2DById(int id) { return repository.selectSala2DById(id); }

    @Override
    public List<Sala2D> selectAll() { return repository.selectAll(); }

    @Override
    public Sala2D updateSala2D(int numeroSala, int capacidadAsientos, boolean tieneSonidoDolby) {
        Validaciones.positivo(numeroSala, "numeroSala");
        Validaciones.positivo(capacidadAsientos, "capacidadAsientos");
        if (capacidadAsientos > 500) { throw new IllegalArgumentException("Maximo 500 asientos en este ejercicio."); }
        Sala2D objeto = repository.selectSala2DById(numeroSala);
        Validaciones.existe(objeto, "El registro no existe.");
        Validaciones.libre(datos.salaEnUso(numeroSala), "No modifique una sala con tickets registrados.");
        
        objeto.setCapacidadAsientos(capacidadAsientos);
        objeto.setTieneSonidoDolby(tieneSonidoDolby);
        repository.updateSala2D(objeto);
        
        return objeto;
    }
    @Override
    public boolean deleteSala2D(int id) {
        Validaciones.libre(datos.salaEnUso(id), "La sala tiene tickets registrados.");
        boolean eliminado = repository.deleteSala2D(id);
        
        return eliminado;
    }
}

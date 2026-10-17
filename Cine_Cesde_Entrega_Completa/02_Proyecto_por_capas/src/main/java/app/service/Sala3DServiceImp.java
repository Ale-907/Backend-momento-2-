package app.service;

import app.domain.*;
import app.service.inputports.Sala3DServiceInterface;
import app.service.outputports.Sala3DRepositoryPort;
import app.service.helpers.DatosRelacionados;
import app.service.validations.Validaciones;
import java.util.List;

public class Sala3DServiceImp implements Sala3DServiceInterface {
    private final Sala3DRepositoryPort repository;
    private final DatosRelacionados datos;

    public Sala3DServiceImp(Sala3DRepositoryPort repository, DatosRelacionados datos) {
        this.repository = repository;
        this.datos = datos;
    }
    @Override
    public Sala3D createSala3D(int numeroSala, int capacidadAsientos, String versionGafas) {
        Validaciones.positivo(numeroSala, "numeroSala");
        Validaciones.positivo(capacidadAsientos, "capacidadAsientos");
        if (capacidadAsientos > 500) { throw new IllegalArgumentException("Maximo 500 asientos en este ejercicio."); }
        Validaciones.texto(versionGafas, "versionGafas");
        Validaciones.libre(repository.selectSala3DById(numeroSala) != null, "El identificador ya existe.");
        Validaciones.libre(datos.buscarSala(numeroSala) != null, "El numero ya existe en otra sala.");
        
        Sala3D objeto = new Sala3D(numeroSala, capacidadAsientos, versionGafas);
        repository.saveSala3D(objeto);
        
        return objeto;
    }
    @Override
    public Sala3D selectSala3DById(int id) { return repository.selectSala3DById(id); }

    @Override
    public List<Sala3D> selectAll() { return repository.selectAll(); }

    @Override
    public Sala3D updateSala3D(int numeroSala, int capacidadAsientos, String versionGafas) {
        Validaciones.positivo(numeroSala, "numeroSala");
        Validaciones.positivo(capacidadAsientos, "capacidadAsientos");
        if (capacidadAsientos > 500) { throw new IllegalArgumentException("Maximo 500 asientos en este ejercicio."); }
        Validaciones.texto(versionGafas, "versionGafas");
        Sala3D objeto = repository.selectSala3DById(numeroSala);
        Validaciones.existe(objeto, "El registro no existe.");
        Validaciones.libre(datos.salaEnUso(numeroSala), "No modifique una sala con tickets registrados.");
        
        objeto.setCapacidadAsientos(capacidadAsientos);
        objeto.setVersionGafas(versionGafas);
        repository.updateSala3D(objeto);
        
        return objeto;
    }
    @Override
    public boolean deleteSala3D(int id) {
        Validaciones.libre(datos.salaEnUso(id), "La sala tiene tickets registrados.");
        boolean eliminado = repository.deleteSala3D(id);
        
        return eliminado;
    }
}

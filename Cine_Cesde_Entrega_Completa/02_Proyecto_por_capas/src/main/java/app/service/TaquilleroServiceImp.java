package app.service;

import app.domain.*;
import app.service.inputports.TaquilleroServiceInterface;
import app.service.outputports.TaquilleroRepositoryPort;
import app.service.validations.Validaciones;
import java.util.List;

public class TaquilleroServiceImp implements TaquilleroServiceInterface {
    private final TaquilleroRepositoryPort repository;

    public TaquilleroServiceImp(TaquilleroRepositoryPort repository) {
        this.repository = repository;
    }
    @Override
    public Taquillero createTaquillero(String idEmpleado, String nombre, double salarioBase, int boletasVendidas) {
        Validaciones.texto(idEmpleado, "idEmpleado");
        Validaciones.texto(nombre, "nombre");
        Validaciones.noNegativo(salarioBase, "salarioBase");
        Validaciones.noNegativo(boletasVendidas, "boletasVendidas");
        Validaciones.libre(repository.selectTaquilleroById(idEmpleado) != null, "El identificador ya existe.");
        
        
        Taquillero objeto = new Taquillero(idEmpleado, nombre, salarioBase, boletasVendidas);
        repository.saveTaquillero(objeto);
        
        return objeto;
    }
    @Override
    public Taquillero selectTaquilleroById(String id) { return repository.selectTaquilleroById(id); }

    @Override
    public List<Taquillero> selectAll() { return repository.selectAll(); }

    @Override
    public Taquillero updateTaquillero(String idEmpleado, String nombre, double salarioBase, int boletasVendidas) {
        Validaciones.texto(idEmpleado, "idEmpleado");
        Validaciones.texto(nombre, "nombre");
        Validaciones.noNegativo(salarioBase, "salarioBase");
        Validaciones.noNegativo(boletasVendidas, "boletasVendidas");
        Taquillero objeto = repository.selectTaquilleroById(idEmpleado);
        Validaciones.existe(objeto, "El registro no existe.");
        
        
        objeto.setNombre(nombre);
        objeto.setSalarioBase(salarioBase);
        objeto.setBoletasVendidas(boletasVendidas);
        repository.updateTaquillero(objeto);
        
        return objeto;
    }
    @Override
    public boolean deleteTaquillero(String id) {
        
        boolean eliminado = repository.deleteTaquillero(id);
        
        return eliminado;
    }
}

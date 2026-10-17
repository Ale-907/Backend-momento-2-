package app.service;

import app.domain.*;
import app.service.inputports.ConfiteroServiceInterface;
import app.service.outputports.ConfiteroRepositoryPort;
import app.service.validations.Validaciones;
import java.util.List;

public class ConfiteroServiceImp implements ConfiteroServiceInterface {
    private final ConfiteroRepositoryPort repository;

    public ConfiteroServiceImp(ConfiteroRepositoryPort repository) {
        this.repository = repository;
    }
    @Override
    public Confitero createConfitero(String idEmpleado, String nombre, double salarioBase, int combosVendidos) {
        Validaciones.texto(idEmpleado, "idEmpleado");
        Validaciones.texto(nombre, "nombre");
        Validaciones.noNegativo(salarioBase, "salarioBase");
        Validaciones.noNegativo(combosVendidos, "combosVendidos");
        Validaciones.libre(repository.selectConfiteroById(idEmpleado) != null, "El identificador ya existe.");
        
        
        Confitero objeto = new Confitero(idEmpleado, nombre, salarioBase, combosVendidos);
        repository.saveConfitero(objeto);
        
        return objeto;
    }
    @Override
    public Confitero selectConfiteroById(String id) { return repository.selectConfiteroById(id); }

    @Override
    public List<Confitero> selectAll() { return repository.selectAll(); }

    @Override
    public Confitero updateConfitero(String idEmpleado, String nombre, double salarioBase, int combosVendidos) {
        Validaciones.texto(idEmpleado, "idEmpleado");
        Validaciones.texto(nombre, "nombre");
        Validaciones.noNegativo(salarioBase, "salarioBase");
        Validaciones.noNegativo(combosVendidos, "combosVendidos");
        Confitero objeto = repository.selectConfiteroById(idEmpleado);
        Validaciones.existe(objeto, "El registro no existe.");
        
        
        objeto.setNombre(nombre);
        objeto.setSalarioBase(salarioBase);
        objeto.setCombosVendidos(combosVendidos);
        repository.updateConfitero(objeto);
        
        return objeto;
    }
    @Override
    public boolean deleteConfitero(String id) {
        
        boolean eliminado = repository.deleteConfitero(id);
        
        return eliminado;
    }
}

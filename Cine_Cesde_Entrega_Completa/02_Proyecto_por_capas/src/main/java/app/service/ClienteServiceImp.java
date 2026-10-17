package app.service;

import app.domain.*;
import app.service.inputports.ClienteServiceInterface;
import app.service.outputports.ClienteRepositoryPort;
import app.service.helpers.DatosRelacionados;
import app.service.validations.Validaciones;
import java.util.List;

public class ClienteServiceImp implements ClienteServiceInterface {
    private final ClienteRepositoryPort repository;
    private final DatosRelacionados datos;

    public ClienteServiceImp(ClienteRepositoryPort repository, DatosRelacionados datos) {
        this.repository = repository;
        this.datos = datos;
    }
    @Override
    public Cliente createCliente(String cedula, String nombre) {
        Validaciones.texto(cedula, "cedula");
        Validaciones.texto(nombre, "nombre");
        Validaciones.libre(repository.selectClienteById(cedula) != null, "El identificador ya existe.");
        
        
        Cliente objeto = new Cliente(cedula, nombre, 0);
        repository.saveCliente(objeto);
        
        return objeto;
    }
    @Override
    public Cliente selectClienteById(String id) { return repository.selectClienteById(id); }

    @Override
    public List<Cliente> selectAll() { return repository.selectAll(); }

    @Override
    public Cliente updateCliente(String cedula, String nombre) {
        Validaciones.texto(cedula, "cedula");
        Validaciones.texto(nombre, "nombre");
        Cliente objeto = repository.selectClienteById(cedula);
        Validaciones.existe(objeto, "El registro no existe.");
        
        
        objeto.setNombre(nombre);
        repository.updateCliente(objeto);
        
        return objeto;
    }
    @Override
    public boolean deleteCliente(String id) {
        Validaciones.libre(datos.clienteEnUso(id), "El cliente tiene tickets registrados.");
        boolean eliminado = repository.deleteCliente(id);
        
        return eliminado;
    }
}

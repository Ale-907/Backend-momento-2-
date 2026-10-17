package app.service;

import app.domain.*;
import app.service.inputports.AsientoServiceInterface;
import app.service.outputports.AsientoRepositoryPort;
import app.service.helpers.DatosRelacionados;
import app.service.validations.Validaciones;
import java.util.List;

public class AsientoServiceImp implements AsientoServiceInterface {
    private final AsientoRepositoryPort repository;
    private final DatosRelacionados datos;
    public AsientoServiceImp(AsientoRepositoryPort repository, DatosRelacionados datos) {
        this.repository = repository; this.datos = datos;
    }
    @Override
    public Asiento createAsiento(int numeroSala, String codigo) {
        Validaciones.texto(codigo, "Codigo");
        Sala sala = datos.buscarSala(numeroSala);
        Validaciones.existe(sala, "La sala no existe.");
        Validaciones.libre(datos.buscarAsiento(numeroSala, codigo) != null, "El asiento ya existe.");
        if (sala.getCapacidadAsientos() >= 500) { throw new IllegalArgumentException("Maximo 500 asientos."); }
        return repository.saveAsiento(new Asiento(numeroSala, codigo));
    }
    @Override
    public Asiento selectAsientoById(String id) { return repository.selectAsientoById(id); }
    @Override
    public List<Asiento> selectAll() { return repository.selectAll(); }
    @Override
    public Asiento updateAsiento(String id, String nuevoCodigo) {
        Validaciones.texto(nuevoCodigo, "Codigo");
        Asiento asiento = repository.selectAsientoById(id);
        Validaciones.existe(asiento, "El asiento no existe.");
        Validaciones.libre(datos.asientoEnUso(asiento.getNumeroSala(), asiento.getCodigo(), -1), "El asiento tiene un ticket.");
        Asiento repetido = datos.buscarAsiento(asiento.getNumeroSala(), nuevoCodigo);
        Validaciones.libre(repetido != null && repetido != asiento, "El nuevo codigo ya existe.");
        asiento.setCodigo(nuevoCodigo);
        return repository.updateAsiento(asiento);
    }
    @Override
    public boolean deleteAsiento(String id) {
        Asiento asiento = repository.selectAsientoById(id);
        if (asiento == null) { return false; }
        Sala sala = datos.buscarSala(asiento.getNumeroSala());
        Validaciones.libre(datos.asientoEnUso(asiento.getNumeroSala(), asiento.getCodigo(), -1), "El asiento tiene un ticket.");
        if (sala.getCapacidadAsientos() <= 1) { throw new IllegalArgumentException("La sala debe conservar un asiento."); }
        return repository.deleteAsiento(id);
    }
}

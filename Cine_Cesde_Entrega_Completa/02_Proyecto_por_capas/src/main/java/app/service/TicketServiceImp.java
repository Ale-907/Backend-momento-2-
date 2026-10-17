package app.service;

import app.domain.*;
import app.service.inputports.TicketServiceInterface;
import app.service.outputports.TicketRepositoryPort;
import app.service.helpers.DatosRelacionados;
import app.service.validations.Validaciones;
import java.util.List;

public class TicketServiceImp implements TicketServiceInterface {
    private final TicketRepositoryPort repository;
    private final DatosRelacionados datos;

    public TicketServiceImp(TicketRepositoryPort repository, DatosRelacionados datos) {
        this.repository = repository;
        this.datos = datos;
    }
    @Override
    public Ticket createTicket(int id, String cedulaCliente, int idPelicula, int numeroSala, String codigoAsiento, int idCombo, double precio) {
        Validaciones.positivo(id, "id");
        Validaciones.texto(cedulaCliente, "cedulaCliente");
        Validaciones.positivo(idPelicula, "idPelicula");
        Validaciones.positivo(numeroSala, "numeroSala");
        Validaciones.texto(codigoAsiento, "codigoAsiento");
        Validaciones.noNegativo(idCombo, "ID combo");
        Validaciones.positivo(precio, "precio");
        if (precio > 1000000) { throw new IllegalArgumentException("Maximo $1.000.000 en este ejercicio."); }
        Validaciones.libre(repository.selectTicketById(id) != null, "El identificador ya existe.");
        Validaciones.libre(datos.asientoEnUso(numeroSala, codigoAsiento, -1), "El asiento ya tiene un ticket registrado.");
        
        Cliente cliente = datos.buscarCliente(cedulaCliente);
        Pelicula pelicula = datos.buscarPelicula(idPelicula);
        Sala sala = datos.buscarSala(numeroSala);
        Asiento asiento = datos.buscarAsiento(numeroSala, codigoAsiento);
        ComboComida combo = idCombo == 0 ? null : datos.buscarCombo(idCombo);
        Validaciones.existe(cliente, "El cliente no existe.");
        Validaciones.existe(pelicula, "La pelicula no existe.");
        Validaciones.existe(sala, "La sala no existe.");
        Validaciones.existe(asiento, "El asiento no pertenece a la sala.");
        if (idCombo != 0) { Validaciones.existe(combo, "El combo no existe."); }

        Ticket objeto = new Ticket(id, cliente, pelicula, sala, asiento, combo, precio);
        repository.saveTicket(objeto);
        datos.recalcularPuntos();
        return objeto;
    }
    @Override
    public Ticket selectTicketById(int id) { return repository.selectTicketById(id); }

    @Override
    public List<Ticket> selectAll() { return repository.selectAll(); }

    @Override
    public Ticket updateTicket(int id, String cedulaCliente, int idPelicula, int numeroSala, String codigoAsiento, int idCombo, double precio) {
        Validaciones.positivo(id, "id");
        Validaciones.texto(cedulaCliente, "cedulaCliente");
        Validaciones.positivo(idPelicula, "idPelicula");
        Validaciones.positivo(numeroSala, "numeroSala");
        Validaciones.texto(codigoAsiento, "codigoAsiento");
        Validaciones.noNegativo(idCombo, "ID combo");
        Validaciones.positivo(precio, "precio");
        if (precio > 1000000) { throw new IllegalArgumentException("Maximo $1.000.000 en este ejercicio."); }
        Ticket objeto = repository.selectTicketById(id);
        Validaciones.existe(objeto, "El registro no existe.");
        Validaciones.libre(datos.asientoEnUso(numeroSala, codigoAsiento, id), "El asiento ya tiene otro ticket.");
        
        Cliente cliente = datos.buscarCliente(cedulaCliente);
        Pelicula pelicula = datos.buscarPelicula(idPelicula);
        Sala sala = datos.buscarSala(numeroSala);
        Asiento asiento = datos.buscarAsiento(numeroSala, codigoAsiento);
        ComboComida combo = idCombo == 0 ? null : datos.buscarCombo(idCombo);
        Validaciones.existe(cliente, "El cliente no existe.");
        Validaciones.existe(pelicula, "La pelicula no existe.");
        Validaciones.existe(sala, "La sala no existe.");
        Validaciones.existe(asiento, "El asiento no pertenece a la sala.");
        if (idCombo != 0) { Validaciones.existe(combo, "El combo no existe."); }

        objeto.setCliente(cliente);
        objeto.setPelicula(pelicula);
        objeto.setSala(sala);
        objeto.setAsiento(asiento);
        objeto.setCombo(combo);
        objeto.setPrecio(precio);
        repository.updateTicket(objeto);
        datos.recalcularPuntos();
        return objeto;
    }
    @Override
    public boolean deleteTicket(int id) {
        
        boolean eliminado = repository.deleteTicket(id);
        datos.recalcularPuntos();
        return eliminado;
    }
}

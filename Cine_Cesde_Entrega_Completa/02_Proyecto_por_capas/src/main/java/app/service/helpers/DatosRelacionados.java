package app.service.helpers;

import app.domain.*;
import app.service.outputports.*;
import java.util.ArrayList;
import java.util.List;

public class DatosRelacionados {
    private final ClienteRepositoryPort clientes;
    private final PeliculaRepositoryPort peliculas;
    private final ComboComidaRepositoryPort combos;
    private final CineRepositoryPort cines;
    private final Sala2DRepositoryPort salas2D;
    private final Sala3DRepositoryPort salas3D;
    private final TicketRepositoryPort tickets;

    public DatosRelacionados(ClienteRepositoryPort clientes, PeliculaRepositoryPort peliculas,
            ComboComidaRepositoryPort combos, CineRepositoryPort cines,
            Sala2DRepositoryPort salas2D, Sala3DRepositoryPort salas3D, TicketRepositoryPort tickets) {
        this.clientes = clientes; this.peliculas = peliculas; this.combos = combos;
        this.cines = cines; this.salas2D = salas2D; this.salas3D = salas3D; this.tickets = tickets;
    }
    public Cliente buscarCliente(String cedula) { return clientes.selectClienteById(cedula); }
    public Pelicula buscarPelicula(int id) { return peliculas.selectPeliculaById(id); }
    public ComboComida buscarCombo(int id) { return combos.selectComboComidaById(id); }
    public List<Sala> obtenerSalas() {
        List<Sala> salas = new ArrayList<>();
        salas.addAll(salas2D.selectAll());
        salas.addAll(salas3D.selectAll());
        return salas;
    }
    public Sala buscarSala(int numero) {
        for (Sala sala : obtenerSalas()) { if (sala.getNumeroSala() == numero) { return sala; } }
        return null;
    }
    public Asiento buscarAsiento(int numero, String codigo) {
        Sala sala = buscarSala(numero);
        if (sala != null) {
            for (Asiento asiento : sala.getAsientos()) {
                if (asiento.getCodigo().equals(codigo)) { return asiento; }
            }
        }
        return null;
    }
    public boolean salaEnUso(int numero) {
        for (Ticket ticket : tickets.selectAll()) {
            if (ticket.getSala().getNumeroSala() == numero) { return true; }
        }
        return false;
    }
    public boolean asientoEnUso(int numero, String codigo, int ignorarTicket) {
        for (Ticket ticket : tickets.selectAll()) {
            if (ticket.getId() != ignorarTicket && ticket.getSala().getNumeroSala() == numero
                    && ticket.getAsiento().getCodigo().equals(codigo)) { return true; }
        }
        return false;
    }
    public boolean clienteEnUso(String cedula) {
        for (Ticket ticket : tickets.selectAll()) {
            if (ticket.getCliente().getCedula().equals(cedula)) { return true; }
        }
        return false;
    }
    public boolean comboEnUso(int id) {
        for (Ticket ticket : tickets.selectAll()) {
            if (ticket.getCombo() != null && ticket.getCombo().getId() == id) { return true; }
        }
        return false;
    }
    public boolean peliculaEnUso(int id) {
        for (Ticket ticket : tickets.selectAll()) {
            if (ticket.getPelicula().getId() == id) { return true; }
        }
        for (Cine cine : cines.selectAll()) {
            for (Pelicula pelicula : cine.getPeliculas()) {
                if (pelicula.getId() == id) { return true; }
            }
        }
        return false;
    }
    // Los puntos reflejan las compras que siguen registradas en memoria.
    public void recalcularPuntos() {
        for (Cliente cliente : clientes.selectAll()) { cliente.setPuntosCesde(0); }
        for (Ticket ticket : tickets.selectAll()) {
            Cliente cliente = ticket.getCliente();
            int puntos = (int) (ticket.calcularTotal() / 1000);
            cliente.setPuntosCesde(cliente.getPuntosCesde() + puntos);
        }
    }
}

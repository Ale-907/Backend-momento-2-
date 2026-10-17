package app.view;

import app.domain.Ticket;
import app.service.inputports.TicketServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class TicketView {
    private final TicketServiceInterface service;
    public TicketView(TicketServiceInterface service) { this.service = service; }

    public void createTicket() {
        int id = FormTypeValidator.intValidator("ID");
        String cedulaCliente = FormTypeValidator.stringValidator("Cedula del cliente registrado");
        int idPelicula = FormTypeValidator.intValidator("ID de la pelicula registrada");
        int numeroSala = FormTypeValidator.intValidator("Numero de sala");
        String codigoAsiento = FormTypeValidator.stringValidator("Codigo del asiento (por ejemplo S1)");
        int idCombo = FormTypeValidator.intValidator("ID del combo (0 si no desea combo)");
        double precio = FormTypeValidator.doubleValidator("Precio sin puntos de miles");
        service.createTicket(id, cedulaCliente, idPelicula, numeroSala, codigoAsiento, idCombo, precio);
        System.out.println("Registro creado.");
    }
    public void selectAll() {
        List<Ticket> objetos = service.selectAll();
        if (objetos.isEmpty()) { System.out.println("No hay registros."); }
        for (Ticket objeto : objetos) { mostrar(objeto); }
    }
    public void selectTicketById() {
        int id = FormTypeValidator.intValidator("ID");
        Ticket objeto = service.selectTicketById(id);
        if (objeto == null) { System.out.println("El registro no existe."); }
        else { mostrar(objeto); }
    }
    public void updateTicket() {
        int id = FormTypeValidator.intValidator("ID");
        String cedulaCliente = FormTypeValidator.stringValidator("Cedula del cliente registrado");
        int idPelicula = FormTypeValidator.intValidator("ID de la pelicula registrada");
        int numeroSala = FormTypeValidator.intValidator("Numero de sala");
        String codigoAsiento = FormTypeValidator.stringValidator("Codigo del asiento (por ejemplo S1)");
        int idCombo = FormTypeValidator.intValidator("ID del combo (0 si no desea combo)");
        double precio = FormTypeValidator.doubleValidator("Precio sin puntos de miles");
        service.updateTicket(id, cedulaCliente, idPelicula, numeroSala, codigoAsiento, idCombo, precio);
        System.out.println("Registro actualizado.");
    }
    public void deleteTicket() {
        int id = FormTypeValidator.intValidator("ID");
        boolean eliminado = service.deleteTicket(id);
        System.out.println(eliminado ? "Registro eliminado." : "El registro no existe.");
    }
    private void mostrar(Ticket objeto) {
        System.out.println(objeto.getId() + " - Cliente: " + objeto.getCliente().getNombre() + " - Pelicula: " + objeto.getPelicula().getTitulo() + " - Sala " + objeto.getSala().getNumeroSala() + " - Asiento " + objeto.getAsiento().getCodigo() + " - Combo: " + (objeto.getCombo() == null ? "Sin combo" : objeto.getCombo().getNombreCombo()) + " - Total: $" + objeto.calcularTotal());
    }
}

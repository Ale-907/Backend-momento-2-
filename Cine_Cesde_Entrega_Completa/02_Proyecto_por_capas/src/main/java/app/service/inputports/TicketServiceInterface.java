package app.service.inputports;

import app.domain.Ticket;
import java.util.List;

public interface TicketServiceInterface {
    Ticket createTicket(int id, String cedulaCliente, int idPelicula, int numeroSala, String codigoAsiento, int idCombo, double precio);
    Ticket selectTicketById(int id);
    List<Ticket> selectAll();
    Ticket updateTicket(int id, String cedulaCliente, int idPelicula, int numeroSala, String codigoAsiento, int idCombo, double precio);
    boolean deleteTicket(int id);
}

package app.service.outputports;

import app.domain.Ticket;
import java.util.List;

public interface TicketRepositoryPort {
    Ticket saveTicket(Ticket objeto);
    Ticket selectTicketById(int id);
    List<Ticket> selectAll();
    Ticket updateTicket(Ticket objeto);
    boolean deleteTicket(int id);
}

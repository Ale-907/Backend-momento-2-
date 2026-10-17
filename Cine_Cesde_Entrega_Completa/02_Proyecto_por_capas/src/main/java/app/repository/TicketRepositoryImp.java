package app.repository;

import app.domain.Ticket;
import app.service.outputports.TicketRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImp implements TicketRepositoryPort {
    private final List<Ticket> objetos = new ArrayList<>();

    @Override
    public Ticket saveTicket(Ticket objeto) {
        objetos.add(objeto);
        return objeto;
    }
    @Override
    public Ticket selectTicketById(int id) {
        for (Ticket objeto : objetos) {
            if (objeto.getId() == id) { return objeto; }
        }
        return null;
    }
    @Override
    public List<Ticket> selectAll() { return new ArrayList<>(objetos); }

    @Override
    public Ticket updateTicket(Ticket objeto) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getId() == objeto.getId()) {
                objetos.set(i, objeto);
                return objeto;
            }
        }
        return null;
    }
    @Override
    public boolean deleteTicket(int id) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getId() == id) { objetos.remove(i); return true; }
        }
        return false;
    }
}

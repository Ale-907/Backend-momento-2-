package app.service.inputports;

import app.domain.Cliente;
import java.util.List;

public interface ClienteServiceInterface {
    Cliente createCliente(String cedula, String nombre);
    Cliente selectClienteById(String id);
    List<Cliente> selectAll();
    Cliente updateCliente(String cedula, String nombre);
    boolean deleteCliente(String id);
}

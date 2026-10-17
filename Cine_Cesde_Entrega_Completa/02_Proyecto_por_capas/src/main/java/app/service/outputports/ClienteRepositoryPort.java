package app.service.outputports;

import app.domain.Cliente;
import java.util.List;

public interface ClienteRepositoryPort {
    Cliente saveCliente(Cliente objeto);
    Cliente selectClienteById(String id);
    List<Cliente> selectAll();
    Cliente updateCliente(Cliente objeto);
    boolean deleteCliente(String id);
}

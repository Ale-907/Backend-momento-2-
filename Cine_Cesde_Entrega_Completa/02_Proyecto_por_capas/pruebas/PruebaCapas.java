import app.domain.*;
import app.domain.enums.ClasificacionEnum;
import app.repository.*;
import app.service.*;
import app.service.inputports.*;
import app.service.outputports.*;
import app.service.helpers.DatosRelacionados;

public class PruebaCapas {
    private static int comprobaciones = 0;
    private static void revisar(boolean condicion, String mensaje) {
        comprobaciones++;
        if (!condicion) { throw new AssertionError(mensaje); }
    }
    private static void rechazar(Runnable accion, String mensaje) {
        boolean rechazo = false;
        try { accion.run(); } catch (IllegalArgumentException e) { rechazo = true; }
        revisar(rechazo, mensaje);
    }
    public static void main(String[] args) {
        ClienteRepositoryPort clienteRepository = new ClienteRepositoryImp();
        PeliculaRepositoryPort peliculaRepository = new PeliculaRepositoryImp();
        ComboComidaRepositoryPort comboComidaRepository = new ComboComidaRepositoryImp();
        CineRepositoryPort cineRepository = new CineRepositoryImp();
        TaquilleroRepositoryPort taquilleroRepository = new TaquilleroRepositoryImp();
        ConfiteroRepositoryPort confiteroRepository = new ConfiteroRepositoryImp();
        Sala2DRepositoryPort sala2DRepository = new Sala2DRepositoryImp();
        Sala3DRepositoryPort sala3DRepository = new Sala3DRepositoryImp();
        TicketRepositoryPort ticketRepository = new TicketRepositoryImp();
        DatosRelacionados datos = new DatosRelacionados(clienteRepository, peliculaRepository,
                comboComidaRepository, cineRepository, sala2DRepository, sala3DRepository, ticketRepository);
        AsientoRepositoryPort asientoRepository = new AsientoRepositoryImp(datos);
        ClienteServiceInterface cliente = new ClienteServiceImp(clienteRepository, datos);
        PeliculaServiceInterface pelicula = new PeliculaServiceImp(peliculaRepository, datos);
        ComboComidaServiceInterface comboComida = new ComboComidaServiceImp(comboComidaRepository, datos);
        CineServiceInterface cine = new CineServiceImp(cineRepository, datos);
        TaquilleroServiceInterface taquillero = new TaquilleroServiceImp(taquilleroRepository);
        ConfiteroServiceInterface confitero = new ConfiteroServiceImp(confiteroRepository);
        Sala2DServiceInterface sala2D = new Sala2DServiceImp(sala2DRepository, datos);
        Sala3DServiceInterface sala3D = new Sala3DServiceImp(sala3DRepository, datos);
        TicketServiceInterface ticket = new TicketServiceImp(ticketRepository, datos);
        AsientoServiceInterface asiento = new AsientoServiceImp(asientoRepository, datos);

        // CRUD de cliente y reglas basicas.
        Cliente objetoCliente = new Cliente();
        objetoCliente.setCedula("99"); objetoCliente.setNombre("Bean"); objetoCliente.setPuntosCesde(2);
        revisar(objetoCliente.getNombre().equals("Bean") && objetoCliente.getPuntosCesde() == 2, "Constructor vacio y accesores");
        objetoCliente = cliente.createCliente("101", "Laura");
        revisar(cliente.selectAll().size() == 1, "Crear y listar cliente");
        revisar(cliente.selectClienteById("101") == objetoCliente, "Buscar cliente");
        cliente.updateCliente("101", "Laura Actualizada");
        revisar(objetoCliente.getNombre().equals("Laura Actualizada"), "Actualizar sin romper referencias");
        rechazar(() -> cliente.createCliente("101", "Otra"), "Cedula repetida");
        rechazar(() -> cliente.createCliente("", "Otra"), "Cedula vacia");
        rechazar(() -> cliente.updateCliente("999", "Otra"), "Actualizar registro inexistente");
        revisar(!cliente.deleteCliente("999"), "Eliminar inexistente");
        cliente.selectAll().clear();
        revisar(cliente.selectAll().size() == 1, "Listado no permite vaciar almacenamiento");

        Pelicula objetoPelicula = new Pelicula();
        objetoPelicula.setId(1); objetoPelicula.setTitulo("Temporal"); objetoPelicula.setDuracionMinutos(90);
        objetoPelicula.setClasificacion(ClasificacionEnum.TODO_PUBLICO);
        revisar(objetoPelicula.getTitulo().equals("Temporal"), "Pelicula POJO");
        objetoPelicula = pelicula.createPelicula(1, "Ejemplo", 100, ClasificacionEnum.TODO_PUBLICO);
        revisar(pelicula.selectPeliculaById(1) == objetoPelicula, "Buscar pelicula");
        pelicula.updatePelicula(1, "Ejemplo actualizado", 120, ClasificacionEnum.MAYORES_12);
        revisar(objetoPelicula.getDuracionMinutos() == 120, "Actualizar pelicula");
        rechazar(() -> pelicula.createPelicula(2, "Invalida", 0, ClasificacionEnum.TODO_PUBLICO), "Duracion positiva");
        rechazar(() -> pelicula.createPelicula(2, "Invalida", 100, null), "Enum obligatorio");
        revisar(pelicula.selectAll().size() == 1, "Listar peliculas");

        comboComida.createComboComida(1, "Pareja", 28000);
        comboComida.updateComboComida(1, "Pareja nueva", 28000);
        revisar(comboComida.selectComboComidaById(1).getNombreCombo().equals("Pareja nueva"), "Actualizar combo");
        revisar(comboComida.selectAll().size() == 1, "Listar combos");
        rechazar(() -> comboComida.createComboComida(2, "Mal", -1), "Precio positivo");
        rechazar(() -> comboComida.createComboComida(2, "Mal", Double.NaN), "Rechazar NaN");
        rechazar(() -> comboComida.createComboComida(2, "Mal", Double.POSITIVE_INFINITY), "Rechazar infinito");

        cine.createCine(1, "Cesde");
        cine.agregarPelicula(1, 1); cine.agregarPelicula(1, 1);
        revisar(cine.selectCineById(1).getPeliculas().size() == 1, "Catalogo no repite pelicula");
        revisar(cine.selectCineById(1).getPeliculas().get(0) == objetoPelicula, "Agregacion de pelicula externa");
        cine.updateCine(1, "Cesde actualizado");
        revisar(cine.selectCineById(1).getPeliculas().size() == 1, "Actualizar cine conserva catalogo");
        revisar(cine.selectAll().size() == 1, "Listar cines");
        rechazar(() -> pelicula.deletePelicula(1), "No eliminar pelicula del catalogo");
        rechazar(() -> cine.agregarPelicula(1, 99), "No asociar pelicula inexistente");

        sala2D.createSala2D(1, 12, true);
        sala3D.createSala3D(2, 8, "Basicas");
        Sala sala = datos.buscarSala(1);
        revisar(sala.getAsientos().size() == 12, "Composicion al crear sala");
        revisar(datos.buscarSala(2).getAsientos().size() == 8, "Capacidad sala 3D");
        revisar(sala.obtenerTipoPantalla().contains("2D"), "Polimorfismo 2D");
        revisar(datos.buscarSala(2).obtenerTipoPantalla().contains("3D"), "Polimorfismo 3D");
        revisar(sala.getAsientos().get(0) != datos.buscarSala(2).getAsientos().get(0), "Cada sala crea asientos propios");
        sala.getAsientos().clear();
        revisar(sala.getAsientos().size() == 12, "No borrar composicion desde listado");
        rechazar(() -> sala3D.createSala3D(1, 8, "Otras"), "Numero de sala unico entre tipos");
        rechazar(() -> sala2D.createSala2D(3, 0, false), "Capacidad positiva");
        rechazar(() -> sala2D.createSala2D(3, 501, false), "Limite de capacidad");
        sala2D.updateSala2D(1, 12, false);
        sala3D.updateSala3D(2, 8, "Nuevas");
        revisar(!sala2D.selectSala2DById(1).getTieneSonidoDolby(), "Actualizar 2D");
        revisar(sala3D.selectSala3DById(2).getVersionGafas().equals("Nuevas"), "Actualizar 3D");
        revisar(sala2D.selectAll().size() == 1 && sala3D.selectAll().size() == 1, "Listar ambos tipos");

        Asiento nuevo = asiento.createAsiento(1, "VIP");
        revisar(nuevo == datos.buscarAsiento(1, "VIP"), "Asiento nuevo pertenece a sala");
        revisar(datos.buscarSala(1).getCapacidadAsientos() == 13, "Agregar asiento actualiza capacidad");
        asiento.updateAsiento("1:VIP", "VIP2");
        revisar(asiento.selectAsientoById("1:VIP") == null && asiento.selectAsientoById("1:VIP2") != null, "Renombrar asiento");
        rechazar(() -> asiento.createAsiento(1, "S1"), "No repetir codigo dentro de sala");
        rechazar(() -> asiento.createAsiento(99, "S1"), "Asiento exige sala existente");
        revisar(asiento.selectAll().size() == 21, "Listar asientos de ambas salas");
        revisar(asiento.deleteAsiento("1:VIP2"), "Eliminar asiento");
        revisar(datos.buscarSala(1).getCapacidadAsientos() == 12, "Eliminar actualiza capacidad");

        Empleado empleado = taquillero.createTaquillero("T1", "Ana", 1300000, 100);
        revisar(empleado.calcularSueldo() == 1350000, "Polimorfismo sueldo taquillero");
        taquillero.updateTaquillero("T1", "Ana nueva", 1300000, 200);
        revisar(taquillero.selectTaquilleroById("T1").calcularSueldo() == 1400000, "Actualizar comision taquillero");
        empleado = confitero.createConfitero("C1", "Luis", 1300000, 35);
        revisar(empleado.calcularSueldo() == 1335000, "Polimorfismo sueldo confitero");
        confitero.updateConfitero("C1", "Luis nuevo", 1300000, 40);
        revisar(confitero.selectConfiteroById("C1").calcularSueldo() == 1340000, "Actualizar comision confitero");
        revisar(taquillero.selectAll().size() == 1 && confitero.selectAll().size() == 1, "Listar personal");
        rechazar(() -> taquillero.createTaquillero("T2", "Ana", 1000, -1), "Ventas no negativas");

        Ticket compra = ticket.createTicket(1, "101", 1, 1, "S1", 1, 18000);
        revisar(compra.calcularTotal() == 46000, "Total con combo");
        revisar(compra.getCliente() == objetoCliente && compra.getPelicula() == objetoPelicula, "Referencias reales del ticket");
        revisar(objetoCliente.getPuntosCesde() == 46, "Puntos al crear ticket");
        revisar(compra.getAsiento() == datos.buscarAsiento(1, "S1"), "Asiento de la sala elegida");
        rechazar(() -> ticket.createTicket(2, "101", 1, 1, "S1", 0, 18000), "No duplicar asiento vendido");
        rechazar(() -> ticket.createTicket(2, "999", 1, 1, "S2", 0, 18000), "Cliente inexistente");
        rechazar(() -> ticket.createTicket(2, "101", 99, 1, "S2", 0, 18000), "Pelicula inexistente");
        rechazar(() -> ticket.createTicket(2, "101", 1, 99, "S2", 0, 18000), "Sala inexistente");
        rechazar(() -> ticket.createTicket(2, "101", 1, 1, "S99", 0, 18000), "Asiento inexistente");
        rechazar(() -> ticket.createTicket(2, "101", 1, 1, "S2", 99, 18000), "Combo inexistente");
        rechazar(() -> ticket.createTicket(2, "101", 1, 1, "S2", -1, 18000), "Combo negativo");
        revisar(ticket.selectAll().size() == 1, "Creaciones invalidas no guardan tickets");
        rechazar(() -> cliente.deleteCliente("101"), "Cliente usado no se elimina");
        rechazar(() -> pelicula.deletePelicula(1), "Pelicula usada no se elimina");
        rechazar(() -> comboComida.deleteComboComida(1), "Combo usado no se elimina");
        rechazar(() -> comboComida.updateComboComida(1, "Cambio", 1), "Precio de compra conserva valor");
        rechazar(() -> sala2D.deleteSala2D(1), "Sala usada no se elimina");
        rechazar(() -> sala2D.updateSala2D(1, 2, true), "No regenerar asientos usados");
        rechazar(() -> asiento.deleteAsiento("1:S1"), "Asiento usado no se elimina");
        rechazar(() -> asiento.updateAsiento("1:S1", "X"), "Asiento usado no se renombra");
        revisar(objetoCliente.getPuntosCesde() == 46, "Fallos no cambian puntos");
        ticket.updateTicket(1, "101", 1, 2, "S2", 0, 15000);
        revisar(ticket.selectTicketById(1).calcularTotal() == 15000, "Actualizar ticket sin combo");
        revisar(objetoCliente.getPuntosCesde() == 15, "Recalcular puntos al actualizar");
        ticket.createTicket(2, "101", 1, 1, "S1", 0, 18000);
        revisar(objetoCliente.getPuntosCesde() == 33, "Acumular puntos de dos compras");
        revisar(ticket.deleteTicket(1), "Eliminar ticket");
        revisar(objetoCliente.getPuntosCesde() == 18, "Puntos de compras que permanecen");
        revisar(ticket.deleteTicket(2), "Eliminar segundo ticket");
        revisar(objetoCliente.getPuntosCesde() == 0, "Puntos vuelven a cero sin compras");
        revisar(datos.buscarAsiento(1, "S1") != null, "Eliminar ticket conserva sala y asiento");
        revisar(cliente.deleteCliente("101"), "Eliminar cliente ya sin tickets");
        cine.retirarPelicula(1, 1);
        revisar(objetoPelicula.getTitulo().equals("Ejemplo actualizado"), "Retirar de catalogo no destruye pelicula");
        revisar(pelicula.deletePelicula(1), "Eliminar pelicula sin referencias");
        revisar(comboComida.deleteComboComida(1), "Eliminar combo sin tickets");
        revisar(cine.deleteCine(1), "Eliminar cine");
        revisar(taquillero.deleteTaquillero("T1"), "Eliminar taquillero");
        revisar(confitero.deleteConfitero("C1"), "Eliminar confitero");
        revisar(sala2D.deleteSala2D(1), "Eliminar sala 2D");
        revisar(asiento.selectAsientoById("1:S1") == null, "Composicion: asientos dejan de estar gestionados al retirar sala");
        revisar(sala3D.deleteSala3D(2), "Eliminar sala 3D");
        revisar(asiento.selectAll().isEmpty(), "No quedan asientos huerfanos en repositorio");
        System.out.println("OK: " + comprobaciones + " comprobaciones del proyecto por capas.");
    }
}

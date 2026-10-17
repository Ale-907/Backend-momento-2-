import app.domain.*;

public class PruebaModelo {
    private static int verificaciones = 0;

    private static void comprobar(boolean condicion, String mensaje) {
        verificaciones++;
        if (!condicion) { throw new AssertionError(mensaje); }
    }

    private static void esperarError(Runnable accion, String mensaje) {
        boolean fallo = false;
        try { accion.run(); } catch (IllegalArgumentException e) { fallo = true; }
        comprobar(fallo, mensaje);
    }

    public static void main(String[] args) {
        Empleado taquillero = new Taquillero("T1", "Ana", 1300000, 100);
        Empleado confitero = new Confitero("C1", "Luis", 1300000, 35);
        comprobar(taquillero.calcularSueldo() == 1350000, "Comision del taquillero");
        comprobar(confitero.calcularSueldo() == 1335000, "Comision del confitero");

        Sala sala = new Sala2D(1, 12, true);
        Sala otraSala = new Sala3D(2, 12, "Basicas");
        comprobar(sala.obtenerTipoPantalla().contains("2D"), "Pantalla 2D");
        comprobar(otraSala.obtenerTipoPantalla().contains("3D"), "Pantalla 3D");
        comprobar(sala.getAsientos().size() == 12, "Capacidad y asientos coinciden");
        comprobar(sala.buscarAsiento("S1") != otraSala.buscarAsiento("S1"), "Asientos propios por sala");
        boolean protegida = false;
        try { sala.getAsientos().clear(); } catch (UnsupportedOperationException e) { protegida = true; }
        comprobar(protegida, "No se modifica la composicion desde afuera");
        esperarError(() -> sala.buscarAsiento("S99"), "No permite asientos inexistentes");
        esperarError(() -> new Sala2D(3, 0, false), "Capacidad positiva");

        Pelicula pelicula = new Pelicula("Ejemplo", 100, "Todos");
        Cine cine = new Cine("Cesde");
        cine.agregarPelicula(pelicula);
        cine.agregarPelicula(pelicula);
        comprobar(cine.getPeliculas().size() == 1, "No repite el mismo objeto");
        comprobar(cine.getPeliculas().get(0) == pelicula, "Agrega la pelicula externa");
        Cliente cliente = new Cliente("123", "Laura");
        ComboComida combo = new ComboComida("Pareja", 28000);
        Ticket ticket = new Ticket(cliente, pelicula, sala, "S10", 18000, combo);
        comprobar(ticket.calcularTotal() == 46000, "Total con combo");
        comprobar(ticket.getCliente() == cliente, "Asociacion con cliente");
        comprobar(ticket.getPelicula() == pelicula, "Asociacion con pelicula");
        comprobar(ticket.getSala() == sala, "Asociacion con sala");
        comprobar(ticket.getAsiento() == sala.buscarAsiento("S10"), "Referencia al asiento de la sala");
        comprobar(ticket.getCombo() == combo, "Asociacion con combo");
        Ticket sinCombo = new Ticket(cliente, pelicula, sala, "S2", 18000, null);
        comprobar(sinCombo.calcularTotal() == 18000, "Combo opcional");
        cliente.acumularPuntos((int) ticket.calcularTotal());
        comprobar(cliente.getPuntosCesde() == 46, "Un punto por cada mil pesos");
        cliente.acumularPuntos(-1000);
        comprobar(cliente.getPuntosCesde() == 46, "Compra negativa no resta puntos");
        esperarError(() -> new ComboComida("Invalido", 0), "Precio del combo positivo");
        esperarError(() -> new ComboComida("Invalido", Double.NaN), "Rechaza NaN");
        esperarError(() -> new Ticket(cliente, pelicula, sala, "S1", -1, null), "Precio del ticket positivo");
        esperarError(() -> new Ticket(null, pelicula, sala, "S1", 18000, null), "Cliente obligatorio");
        esperarError(() -> cine.agregarPelicula(null), "Pelicula obligatoria");
        System.out.println("OK: " + verificaciones + " verificaciones del modelo.");
    }
}

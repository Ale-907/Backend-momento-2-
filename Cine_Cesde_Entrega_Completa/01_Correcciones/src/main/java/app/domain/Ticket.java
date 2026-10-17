package app.domain;

public class Ticket {
    private Cliente cliente;
    private Pelicula pelicula;
    private Sala sala;
    private Asiento asiento;
    private ComboComida combo;
    private double precio;

    public Ticket(Cliente cliente, Pelicula pelicula, Sala sala,
                  String codigoAsiento, double precio, ComboComida combo) {
        if (cliente == null || pelicula == null || sala == null) {
            throw new IllegalArgumentException("Cliente, pelicula y sala son obligatorios.");
        }
        if (!Double.isFinite(precio) || precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser positivo.");
        }
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.sala = sala;
        this.asiento = sala.buscarAsiento(codigoAsiento);
        this.precio = precio;
        this.combo = combo; // Puede ser null si el cliente no compra comida.
    }

    public Cliente getCliente() { return cliente; }
    public Pelicula getPelicula() { return pelicula; }
    public Sala getSala() { return sala; }
    public Asiento getAsiento() { return asiento; }
    public ComboComida getCombo() { return combo; }
    public double getPrecio() { return precio; }

    public double calcularTotal() {
        return precio + (combo == null ? 0 : combo.getPrecio());
    }

    public void imprimirTicket() {
        System.out.println("--- TICKET CINE CESDE ---");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Pelicula: " + pelicula.getTitulo());
        System.out.println("Sala: " + sala.getNumeroSala() + " - " + sala.obtenerTipoPantalla());
        System.out.println("Asiento: " + asiento.getCodigo());
        System.out.println("Boleta: $" + precio);
        if (combo != null) {
            System.out.println("Combo: " + combo.getNombreCombo() + " - $" + combo.getPrecio());
        }
        System.out.println("Total: $" + calcularTotal());
    }
}

package app.domain;


public class Ticket {
    private int id;
    private Cliente cliente;
    private Pelicula pelicula;
    private Sala sala;
    private Asiento asiento;
    private ComboComida combo;
    private double precio;

    public Ticket() { }

    public Ticket(int id, Cliente cliente, Pelicula pelicula, Sala sala, Asiento asiento, ComboComida combo, double precio) {
        this.id = id;
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.sala = sala;
        this.asiento = asiento;
        this.combo = combo;
        this.precio = precio;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Pelicula getPelicula() { return pelicula; }
    public void setPelicula(Pelicula pelicula) { this.pelicula = pelicula; }

    public Sala getSala() { return sala; }
    public void setSala(Sala sala) { this.sala = sala; }

    public Asiento getAsiento() { return asiento; }
    public void setAsiento(Asiento asiento) { this.asiento = asiento; }

    public ComboComida getCombo() { return combo; }
    public void setCombo(ComboComida combo) { this.combo = combo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public double calcularTotal() {
        return precio + (combo == null ? 0 : combo.getPrecio());
    }
}

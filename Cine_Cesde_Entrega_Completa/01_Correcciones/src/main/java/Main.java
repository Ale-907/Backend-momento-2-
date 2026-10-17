import app.domain.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("BIENVENIDO A CINE CESDE");

        // La pelicula existe antes de agregarla al cine: agregacion.
        Pelicula pelicula = new Pelicula("Avatar 3", 190, "PG-13");
        Cine cine = new Cine("Cine Cesde");
        cine.agregarPelicula(pelicula);
        System.out.println("Cartelera de " + cine.getNombre());
        for (Pelicula item : cine.getPeliculas()) {
            System.out.println("- " + item.getTitulo());
        }

        // Cada sala crea sus propios asientos: composicion.
        List<Sala> salas = Arrays.asList(
                new Sala2D(1, 120, true),
                new Sala3D(2, 80, "MasterImage 3D"));
        System.out.println("\nSALAS DISPONIBLES");
        for (Sala sala : salas) {
            System.out.println("Sala " + sala.getNumeroSala() + ": "
                    + sala.obtenerTipoPantalla() + " - "
                    + sala.getAsientos().size() + " asientos");
        }

        // Mismo metodo, resultados distintos segun la subclase: polimorfismo.
        List<Empleado> empleados = Arrays.asList(
                new Taquillero("T01", "Carlos Perez", 1300000, 100),
                new Confitero("C01", "Maria Gomez", 1300000, 35));
        System.out.println("\nSUELDOS DEL EJEMPLO");
        for (Empleado empleado : empleados) {
            System.out.println(empleado.getNombre() + ": $" + empleado.calcularSueldo());
        }

        Cliente cliente = new Cliente("1012345678", "Laura Restrepo");
        ComboComida combo = new ComboComida("Combo Pareja", 28000);
        Ticket ticket = new Ticket(cliente, pelicula, salas.get(1), "S10", 18000, combo);
        System.out.println("\nVENTA DE EJEMPLO");
        ticket.imprimirTicket();
        cliente.acumularPuntos((int) ticket.calcularTotal());
        System.out.println("Puntos de " + cliente.getNombre() + ": " + cliente.getPuntosCesde());
    }
}

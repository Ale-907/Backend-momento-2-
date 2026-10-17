package app.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Sala {

    private int numeroSala;
    private int capacidadAsientos;
    private final List<Asiento> asientos = new ArrayList<>();

    public Sala(int numeroSala, int capacidadAsientos) {
        if (capacidadAsientos <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }
        this.numeroSala = numeroSala;
        this.capacidadAsientos = capacidadAsientos;
        for (int i = 1; i <= capacidadAsientos; i++) {
            asientos.add(new Asiento("S" + i));
        }
    }

    public int getNumeroSala() { return numeroSala; }
    public int getCapacidadAsientos() { return capacidadAsientos; }

    public List<Asiento> getAsientos() {
        return Collections.unmodifiableList(asientos);
    }

    public Asiento buscarAsiento(String codigo) {
        for (Asiento asiento : asientos) {
            if (asiento.getCodigo().equals(codigo)) {
                return asiento;
            }
        }
        throw new IllegalArgumentException("El asiento no existe en esta sala.");
    }

    public abstract String obtenerTipoPantalla();
}

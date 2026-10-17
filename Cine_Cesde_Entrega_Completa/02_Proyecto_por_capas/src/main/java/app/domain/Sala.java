package app.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Sala {
    private int numeroSala;
    private int capacidadAsientos;
    private List<Asiento> asientos = new ArrayList<>();

    public Sala() { }

    public Sala(int numeroSala, int capacidadAsientos) {
        this.numeroSala = numeroSala;
        setCapacidadAsientos(capacidadAsientos);
    }

    public int getNumeroSala() { return numeroSala; }
    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
        for (Asiento asiento : asientos) { asiento.setNumeroSala(numeroSala); }
    }
    public int getCapacidadAsientos() { return capacidadAsientos; }
    public void setCapacidadAsientos(int capacidadAsientos) {
        this.capacidadAsientos = capacidadAsientos;
        asientos.clear();
        for (int i = 1; i <= capacidadAsientos; i++) {
            asientos.add(new Asiento(numeroSala, "S" + i));
        }
    }
    public List<Asiento> getAsientos() { return new ArrayList<>(asientos); }
    public void setAsientos(List<Asiento> nuevos) {
        asientos.clear();
        for (Asiento asiento : nuevos) {
            asientos.add(new Asiento(numeroSala, asiento.getCodigo()));
        }
        capacidadAsientos = asientos.size();
    }
    public void agregarAsiento(Asiento asiento) {
        asientos.add(new Asiento(numeroSala, asiento.getCodigo()));
        capacidadAsientos = asientos.size();
    }
    public boolean retirarAsiento(String codigo) {
        for (int i = 0; i < asientos.size(); i++) {
            if (asientos.get(i).getCodigo().equals(codigo)) {
                asientos.remove(i);
                capacidadAsientos = asientos.size();
                return true;
            }
        }
        return false;
    }
    public abstract String obtenerTipoPantalla();
}

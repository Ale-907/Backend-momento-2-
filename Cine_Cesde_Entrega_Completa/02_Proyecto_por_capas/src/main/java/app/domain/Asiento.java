package app.domain;


public class Asiento {
    private int numeroSala;
    private String codigo;

    public Asiento() { }

    public Asiento(int numeroSala, String codigo) {
        this.numeroSala = numeroSala;
        this.codigo = codigo;
    }

    public int getNumeroSala() { return numeroSala; }
    public void setNumeroSala(int numeroSala) { this.numeroSala = numeroSala; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getId() { return numeroSala + ":" + codigo; }
}

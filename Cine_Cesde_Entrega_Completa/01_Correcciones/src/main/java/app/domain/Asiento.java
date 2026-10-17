package app.domain;

/** Parte de una sala. Su constructor solo se usa desde el paquete del dominio. */
public final class Asiento {
    private final String codigo;

    Asiento(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}

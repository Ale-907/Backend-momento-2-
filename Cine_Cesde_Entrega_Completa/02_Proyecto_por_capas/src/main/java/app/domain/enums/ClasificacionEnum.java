package app.domain.enums;


public enum ClasificacionEnum {
    TODO_PUBLICO("Todo publico"),
    MAYORES_12("Mayores de 12"),
    MAYORES_15("Mayores de 15"),
    MAYORES_18("Mayores de 18");

    private final String valor;
    ClasificacionEnum(String valor) { this.valor = valor; }
    public String getValor() { return valor; }
}

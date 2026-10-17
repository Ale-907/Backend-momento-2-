package app.service.validations;


public class Validaciones {
    private Validaciones() { }
    public static void texto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacio.");
        }
    }
    public static void positivo(double valor, String campo) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException(campo + " debe ser positivo.");
        }
    }
    public static void noNegativo(double valor, String campo) {
        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException(campo + " no puede ser negativo.");
        }
    }
    public static void existe(Object objeto, String mensaje) {
        if (objeto == null) { throw new IllegalArgumentException(mensaje); }
    }
    public static void libre(boolean enUso, String mensaje) {
        if (enUso) { throw new IllegalArgumentException(mensaje); }
    }
}

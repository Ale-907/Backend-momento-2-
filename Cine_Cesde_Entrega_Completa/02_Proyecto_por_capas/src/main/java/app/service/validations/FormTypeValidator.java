package app.service.validations;

import java.util.Scanner;

public class FormTypeValidator {
    private static final Scanner SCANNER = new Scanner(System.in);
    private FormTypeValidator() { }

    public static String stringValidator(String mensaje) {
        while (true) {
            System.out.print(mensaje + ": ");
            if (!SCANNER.hasNextLine()) { throw new IllegalStateException("Entrada finalizada."); }
            String valor = SCANNER.nextLine().trim();
            if (!valor.isEmpty()) { return valor; }
            System.out.println("El valor no puede quedar vacio.");
        }
    }
    public static int intValidator(String mensaje) {
        while (true) {
            try { return Integer.parseInt(stringValidator(mensaje)); }
            catch (NumberFormatException e) { System.out.println("Ingrese un numero entero."); }
        }
    }
    public static double doubleValidator(String mensaje) {
        while (true) {
            try {
                double valor = Double.parseDouble(stringValidator(mensaje).replace(',', '.'));
                if (Double.isFinite(valor)) { return valor; }
                System.out.println("Ingrese un numero finito.");
            } catch (NumberFormatException e) { System.out.println("Ingrese un numero sin separadores de miles."); }
        }
    }
    public static boolean booleanValidator(String mensaje) {
        while (true) {
            int opcion = intValidator(mensaje + " (1 Si / 2 No)");
            if (opcion == 1) { return true; }
            if (opcion == 2) { return false; }
            System.out.println("Seleccione 1 o 2.");
        }
    }
}

package app.service.helpers;

import app.domain.enums.ClasificacionEnum;
import app.service.validations.FormTypeValidator;

public class SetClasificacionHelper {
    private SetClasificacionHelper() { }
    public static ClasificacionEnum seleccionarClasificacion() {
        while (true) {
            System.out.println("1. Todo publico\n2. Mayores de 12\n3. Mayores de 15\n4. Mayores de 18");
            int opcion = FormTypeValidator.intValidator("Clasificacion");
            switch (opcion) {
                case 1: return ClasificacionEnum.TODO_PUBLICO;
                case 2: return ClasificacionEnum.MAYORES_12;
                case 3: return ClasificacionEnum.MAYORES_15;
                case 4: return ClasificacionEnum.MAYORES_18;
                default: System.out.println("Seleccione una clasificacion valida.");
            }
        }
    }
}

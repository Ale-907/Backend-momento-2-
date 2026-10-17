package app.service.outputports;

import app.domain.Confitero;
import java.util.List;

public interface ConfiteroRepositoryPort {
    Confitero saveConfitero(Confitero objeto);
    Confitero selectConfiteroById(String id);
    List<Confitero> selectAll();
    Confitero updateConfitero(Confitero objeto);
    boolean deleteConfitero(String id);
}

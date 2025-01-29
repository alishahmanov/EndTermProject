package repositories.interfaces;
import models.Insoles;
import java.util.List;

public interface IInsolesRepository {
    boolean addInsoles(Insoles insoles);
    Insoles getInsoles(int id);
    List<Insoles> getAllInsoles();
}

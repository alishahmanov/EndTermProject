package repositories.interfaces;

import models.Insoles;
import java.util.List;
import java.util.Optional;

public interface IInsolesRepository {
    Optional<Insoles> getInsoles(int id);
    List<Insoles> getAllInsoles();
    boolean addInsoles(Insoles insole);
}
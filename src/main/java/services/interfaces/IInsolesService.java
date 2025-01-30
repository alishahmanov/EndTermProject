package services.interfaces;

import models.Insoles;
import java.util.List;

public interface IInsolesService {
    Insoles getInsoleById(int id);
    List<Insoles> getAllInsoles();
    boolean addInsole(String type, String material, int size, int price, String availability);
}

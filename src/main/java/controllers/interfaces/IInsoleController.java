package controllers.interfaces;

import models.enums.Availability;
import models.enums.InsoleType;
import models.enums.Material;

public interface IInsoleController {
    String addInsole(InsoleType type, Material material, int size, int price, Availability availability);
    String getAllInsoles();
    String getInsole(int id);
}

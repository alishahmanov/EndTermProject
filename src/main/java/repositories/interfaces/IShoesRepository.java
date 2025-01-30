package repositories.interfaces;

import models.Shoes;
import java.util.List;
import java.util.Optional;

public interface IShoesRepository {
    Optional<Shoes> getShoe(int id);
    List<Shoes> getAllShoes();
    boolean addShoe(Shoes shoe);
}
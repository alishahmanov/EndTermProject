package repositories.interfaces;
import models.Shoes;
import java.util.List;

public interface IShoesRepository {
    boolean addShoe(Shoes shoe);
    Shoes getShoe(int id);
    List<Shoes> getAllShoes();
}

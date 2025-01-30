package repositories;

import data.interfaces.IDB;
import models.Insoles;
import repositories.interfaces.IInsolesRepository;
import models.enums.InsoleType;
import models.enums.Material;
import models.enums.Availability;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InsolesRepository implements IInsolesRepository {
    private final IDB db;

    public InsolesRepository(IDB db) {
        this.db = db;
    }

    @Override
    public Optional<Insoles> getInsoles(int id) {
        String sql = "SELECT * FROM insoles WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String insoleTypeStr = rs.getString("type");
                String materialStr = rs.getString("material");
                int size = rs.getInt("size");
                int price = rs.getInt("price");
                String availabilityStr = rs.getString("availability");

                InsoleType insoleType = InsoleType.valueOf(insoleTypeStr);
                Material material = Material.valueOf(materialStr);
                Availability availability = Availability.valueOf(availabilityStr);

                Insoles insole = new Insoles(
                        insoleType,
                        material,
                        size,
                        price,
                        availability
                );
                return Optional.of(insole);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Insoles> getAllInsoles() {
        String sql = "SELECT * FROM insoles";
        List<Insoles> insolesList = new ArrayList<>();

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String insoleTypeStr = rs.getString("type");
                String materialStr = rs.getString("material");
                int size = rs.getInt("size");
                int price = rs.getInt("price");
                String availabilityStr = rs.getString("availability");

                InsoleType insoleType = InsoleType.valueOf(insoleTypeStr);
                Material material = Material.valueOf(materialStr);
                Availability availability = Availability.valueOf(availabilityStr);

                Insoles insole = new Insoles(insoleType, material, size, price, availability);
                insolesList.add(insole);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        return insolesList;
    }

    @Override
    public boolean addInsoles(Insoles insole) {
        String sql = "INSERT INTO insoles (type, material, size, price, availability) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, insole.getType().name());
            st.setString(2, insole.getMaterial().name());
            st.setInt(3, insole.getSize());
            st.setInt(4, insole.getPrice());
            st.setString(5, insole.getAvailability().name());

            return st.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }
    }
}

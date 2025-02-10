package repositories;
import models.enums.*;
import factorieses.ShoesFactory;
import data.interfaces.IDB;
import models.Shoes;
import repositories.interfaces.IShoesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoesRepository implements IShoesRepository {
    private final IDB db;

    public ShoesRepository(IDB db) {
        this.db = db;
    }

    @Override
    public Optional<Shoes> getShoe(int id) {
        String sql = "SELECT * FROM shoes WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                boolean gender = rs.getBoolean("gender");
                String brand = rs.getString("brand");
                String materialStr = rs.getString("material");
                String seasonStr = rs.getString("season_of_shoes");
                String color = rs.getString("color");
                int size = rs.getInt("size");
                int price = rs.getInt("price");
                String availabilityStr = rs.getString("availability");

                Material material = Material.valueOf(materialStr);
                Season season = Season.valueOf(seasonStr);
                Availability availability = Availability.valueOf(availabilityStr);

                Shoes shoe = ShoesFactory.createShoes(
                        rs.getBoolean("gender"),
                        rs.getString("brand"),
                        rs.getString("material"),
                        rs.getString("season_of_shoes"),
                        rs.getString("color"),
                        rs.getInt("size"),
                        rs.getInt("price"),
                        rs.getString("availability")
                );

                return Optional.of(shoe);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        return Optional.empty();
    }


    @Override
    public List<Shoes> getAllShoes() {
        String sql = "SELECT * FROM shoes";
        List<Shoes> shoesList = new ArrayList<>();

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Shoes shoe = ShoesFactory.createShoes(
                        rs.getBoolean("gender"),
                        rs.getString("brand"),
                        rs.getString("material"),
                        rs.getString("season_of_shoes"),
                        rs.getString("color"),
                        rs.getInt("size"),
                        rs.getInt("price"),
                        rs.getString("availability")
                );

                shoesList.add(shoe);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        return shoesList;
    }

    @Override
    public boolean addShoe(Shoes shoe) {
        String sql = "INSERT INTO shoes (gender, brand, material, season_of_shoes, color, size, price, availability) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setBoolean(1, shoe.isGender());
            st.setString(2, shoe.getBrand());
            st.setString(3, shoe.getMaterial().name());
            st.setString(4, shoe.getSeasonOfShoes().name());
            st.setString(5, shoe.getColor());
            st.setInt(6, shoe.getSize());
            st.setInt(7, shoe.getPrice());
            st.setString(8, shoe.getAvailability().name());

            return st.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }
    }

}
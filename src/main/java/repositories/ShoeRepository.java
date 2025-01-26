package repositories;
import data.interfaces.IDB;
import models.Shoes;
import repositories.interfaces.IShoesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ShoeRepository implements IShoesRepository {
    private final IDB db;

    public ShoeRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean addShoe(Shoes shoe) {
        String sql = "INSERT INTO shoes(brand, material, color, season, gender, size, price, availability) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, shoe.getBrand());
            st.setString(2, shoe.getMaterial());
            st.setString(3, shoe.getColor());
            st.setString(4, shoe.getSeasonOfShoes());
            st.setBoolean(5, shoe.isGender());
            st.setInt(6, shoe.getSize());
            st.setInt(7, shoe.getPrice());
            st.setString(8, shoe.getAvailability());

            st.executeUpdate();

            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error while adding shoe: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Shoes getShoe(int id) {
        String sql = "SELECT id, brand, material, color, season, gender, size, price, availability FROM shoes WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Shoes(
                        rs.getBoolean("gender"),
                        rs.getString("brand"),
                        rs.getString("material"),
                        rs.getString("season"),
                        rs.getString("color"),
                        rs.getInt("size"),
                        rs.getInt("price"),
                        rs.getString("availability")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error while fetching shoe: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Shoes> getAllShoes() {
        String sql = "SELECT id, brand, material, color, season, gender, size, price, availability FROM shoes";

        List<Shoes> shoesList = new ArrayList<>();

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Shoes shoe = new Shoes(
                        rs.getBoolean("gender"),
                        rs.getString("brand"),
                        rs.getString("material"),
                        rs.getString("season"),
                        rs.getString("color"),
                        rs.getInt("size"),
                        rs.getInt("price"),
                        rs.getString("availability")
                );
                shoesList.add(shoe);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error while fetching all shoes: " + e.getMessage());
        }

        return shoesList;
    }
}

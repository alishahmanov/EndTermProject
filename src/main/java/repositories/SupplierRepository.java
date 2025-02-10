package repositories;

import data.interfaces.IDB;
import models.Supplier;
import repositories.interfaces.ISupplierRepository;
import models.enums.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierRepository implements ISupplierRepository {
    private final IDB db;

    public SupplierRepository(IDB db) {
        this.db = db;
    }

    @Override
    public Optional<Supplier> findByEmail(String email) {
        String sql = "SELECT * FROM suppliers WHERE email = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return Optional.of(new Supplier(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("brandofshoes"),
                        rs.getString("countryoforigin"),
                        rs.getInt("deliverycost"),
                        Role.valueOf(rs.getString("role"))
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        String sql = "SELECT * FROM suppliers";
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                suppliers.add(new Supplier(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("brandofshoes"),
                        rs.getString("countryoforigin"),
                        rs.getInt("deliverycost"),
                        Role.valueOf(rs.getString("role"))
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        return suppliers;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        String sql = "INSERT INTO suppliers(name, email, password, brandofshoes, countryoforigin, deliverycost, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, supplier.getName());
            st.setString(2, supplier.getEmail());
            st.setString(3, supplier.getPassword());
            st.setString(4, supplier.getBrandofshoes());
            st.setString(5, supplier.getCountryOfOrigin());
            st.setInt(6, supplier.getDeliveryCost());
            st.setString(7, supplier.getRole().name());

            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = st.getGeneratedKeys();
                if (generatedKeys.next()) {
                    supplier.setId(generatedKeys.getLong(1));
                    return true;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        return false;
    }
}

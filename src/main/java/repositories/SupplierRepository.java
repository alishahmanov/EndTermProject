package repositories;

import data.interfaces.IDB;
import models.Supplier;
import repositories.interfaces.ISupplierRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository implements ISupplierRepository {
    private final IDB db;

    public SupplierRepository(IDB db) {
        this.db = db;
    }

    @Override
    public Supplier findByEmail(String email) {
        String sql = "SELECT * FROM suppliers WHERE email = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Supplier(
                        rs.getString("brandofshoes"),
                        rs.getString("countryoforigin"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("deliverycost"),
                        rs.getString("password")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error while finding supplier by email: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        String sql = "SELECT * FROM suppliers";
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getString("brandofshoes"),
                        rs.getString("countryoforigin"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("deliverycost"),
                        rs.getString("password")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error while fetching all suppliers: " + e.getMessage());
        }

        return suppliers;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        String sql = "INSERT INTO suppliers(brandofshoes, countryoforigin, name, email, deliverycost, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, supplier.getBrandofshoes());
            st.setString(2, supplier.getCountryOfOrigin());
            st.setString(3, supplier.getName());
            st.setString(4, supplier.getEmail());
            st.setInt(5, supplier.getDeliveryCost());
            st.setString(6, supplier.getPassword());

            st.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error while adding supplier: " + e.getMessage());
        }

        return false;
    }
}
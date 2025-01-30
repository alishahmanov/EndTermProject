package repositories;

import data.interfaces.IDB;
import models.Client;
import repositories.interfaces.IClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements IClientRepository {
    private final IDB db;

    public ClientRepository(IDB db) {
        this.db = db;
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        String sql = "SELECT * FROM clients WHERE email = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return Optional.of(new Client(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("gender"),
                        rs.getInt("size"),
                        rs.getInt("amountofmoney")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Client> getAllClients() {
        String sql = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                clients.add(new Client(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("gender"),
                        rs.getInt("size"),
                        rs.getInt("amountofmoney")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        return clients;
    }

    @Override
    public boolean addClient(Client client) {
        String sql = "INSERT INTO clients(name, email, password, gender, size, amountofmoney) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, client.getName());
            st.setString(2, client.getEmail());
            st.setString(3, client.getPassword());
            st.setBoolean(4, client.isGender());
            st.setInt(5, client.getSize());
            st.setInt(6, client.getAmountOfMoney());

            return st.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }
    }
}
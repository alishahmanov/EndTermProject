package data;

import data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:6543/postgres";
        String user = "postgres";
        String password = "0000";

        Connection con = DriverManager.getConnection(connectionUrl, user, password);
        if (con != null) {
            return con;
        } else {
            throw new SQLException("Failed to establish a database connection.");
        }
    }
}


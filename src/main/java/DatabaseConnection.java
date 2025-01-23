import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:postgresql://localhost:6543/postgres";
        String user = "postgres";
        String password = "0000";

        try (Connection con = DriverManager.getConnection(connectionUrl, user, password)) {
            if (con != null) {
                System.out.println("Success!");
            } else {
                System.out.println("I hate this project");
            }
        } catch (Exception e) {
            System.out.println("No comments");
            e.printStackTrace();
        }
    }
}

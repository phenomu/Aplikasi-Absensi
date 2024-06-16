package app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/absensi?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
    // String query = "SELECT * FROM mahasiswa WHERE nim = ? AND password = ?";
    // statement = connection.prepareStatement(query);
    // statement.setString(1, nim);
    // statement.setString(2, password);

}
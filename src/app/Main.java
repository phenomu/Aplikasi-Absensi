package app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.SQLException;
import app.config.Conn;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainframe.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }

    public static void main(String[] args) {
        
        Connection connection = null;

        try {
            // Connect ke Database
            connection = Conn.getConnection();
            System.out.println("Koneksi ke database berhasil!");
            String query = "SELECT VERSION()";
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String version = rs.getString(1);
                System.out.println("Versi MySQL: " + version);
            }

        } catch (SQLException e) {
            System.out.println("Koneksi ke database gagal! Error: " + e.getMessage());
            System.out.println("Pastikan Nama Database Telah Benar");
            System.exit(0);
        } 
        launch(args);
    }
}
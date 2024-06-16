package app;

import java.io.IOException;

import app.data.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class mainframe {

    @FXML
    private Button admin;
    @FXML
    private Button student;
    String source = "";

    @FXML
    void login_admin(ActionEvent event) throws IOException {
        admin.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin/login.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Admin Login");
        stage.show();
    }

    @FXML
    void login_student(ActionEvent event) throws IOException {
        if(Session.isValid()){
            source = "Mahasiswa/DashboardUserController.fxml";
        }else{
            source = "Mahasiswa/login.fxml";
        }
        student.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(source));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Student Login");
        stage.show();
    }

    @FXML
    public void initialize() {
        
    }

}

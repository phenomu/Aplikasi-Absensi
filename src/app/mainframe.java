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
    private Button dosen;
    @FXML
    private Button admin;
    @FXML
    private Button student;
    String source = "";

    @FXML
    void login_dosen(ActionEvent event) throws IOException {
        if(Session.isValid()){
            source = "Dosen/DashboardDosenController.fxml";
        }else{
            source = "Dosen/login.fxml";
        }
        dosen.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(source));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login Dosen");
        stage.show();
    }

    @FXML
    void login_admin(ActionEvent event) throws IOException {
        if(Session.isValid()){
            source = "Mahasiswa/DashboardAdminController.fxml";
        }else{
            source = "admin/login.fxml";
        }
        admin.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(source));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login admin");
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
        stage.setTitle("Login Mahasiswa");
        stage.show();
    }

    @FXML
    public void initialize() {
        
    }

}
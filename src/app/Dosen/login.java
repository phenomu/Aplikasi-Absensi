package app.Dosen;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import app.data.Hash;
import app.data.Session;
import app.config.Conn;

public class login {

    Connection connection = null;
    static String user;
    String pwd;
    PreparedStatement statement;

    @FXML private Stage stage;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button submit;
    @FXML private Button logout;
    @FXML private Label res;

    public static String getUser(){
        return user;
    }

    public boolean validate(String nim, String password){
        try{
            connection = Conn.getConnection();
            String query = "SELECT * FROM dosen WHERE nim = ? AND password = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, nim);
            statement.setString(2, password);
            try(ResultSet res = statement.executeQuery()){
                res.next();
                System.out.println("User With ID "+res.getString("id")+" Has Logged In!");
                if (!Session.isValid()) {
                    Session.addSession(res.getString("id"), res.getString("nim"), query, 2);
                }
                return (true);
            }
        }catch(SQLException e){
            return false;
        }
    }

    public void LoadFXML(){
        try {
            // submit.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardDosenController.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Dashboard Dosen");
            stage.show();
        }catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public boolean addLog(String username,Boolean is_login){
        try{
            connection = Conn.getConnection();
            String query = "INSERT INTO log (id, username, time_login, is_login) SELECT NULL, m.nim, NOW(), ? FROM dosen m WHERE m.nim = ?";
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, is_login);
            statement.setString(2, username);
            int res = statement.executeUpdate();
            if(res>0){
                System.out.println("Log Added");
                return true;
            }
            return false;
        }catch(SQLException er){
            System.out.println(er);
            return false;
        }
    }

    @FXML
    void initialize() {
        if(Session.isValid()){
            try {
                LoadFXML();
            } catch (Exception er) {
                System.out.println(er);
            }
        }

        Hash md5 = new Hash();
        submit.setOnAction(e -> {
            user = username.getText();
            pwd = password.getText();
            if (user.length() == 15 ) {
                if(validate(user,md5.md5(pwd))){
                    res.setText("");
                    try {
                        submit.getScene().getWindow().hide();
                        addLog(user, true);
                        LoadFXML();
                    } catch (Exception er) {
                        System.out.println(er);
                    }
                }
                else{
                    res.setText("NIM Atau Password Salah!");
                    addLog(user, false);
                }
			}else{
				res.setText("Nim Harus 15 Digit!");
			}
        });
        
        logout.setOnAction(e -> {
            try {
                logout.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainframe.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Login Page");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

}

package app.admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
// import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import app.data.Hash;
import app.data.Session;
// import app.data.filter;
import app.config.Conn;

public class profile implements Initializable{


    Connection connection = null;
    static String user;
    String pwd;
    PreparedStatement statement;

    @FXML private Stage stage;
    @FXML private Button submit;
    @FXML private Button logout;
    @FXML private Label res;
    @FXML private TextField ids;
    @FXML private TextField namas;
    @FXML private TextField usernames;
    @FXML private TextField passwords;
    @FXML private Integer id;
    @FXML private String nama;
    @FXML private String username;
    @FXML private String password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Hash md5 = new Hash();
        namas.setText(Session.getName());
        usernames.setText(Session.getNim());
        submit.setOnAction(e -> {
            nama = namas.getText();
            username = usernames.getText();
            password = md5.md5(passwords.getText());
            if(editUser(Integer.parseInt(Session.userId), nama, username, password)){
                System.out.println("Data Berhasil Diubah");
                res.setTextFill(Color.GREEN);
                res.setText("Data Berhasil Diubah.");
            }else{
                res.setTextFill(Color.FIREBRICK);
                res.setText("Data Gagal Diubah.");
            }
        });
        
        logout.setOnAction(e -> {
            logout.getScene().getWindow().hide();
        });
    }

    public static String getUser(){
        return user;
    }

    public boolean editUser(int admin_id, String nama, String username, String password){
        try{
            connection = Conn.getConnection();
            String query = "UPDATE admin SET nama =?, username =?, password =? WHERE id =?";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nama);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setInt(4, Integer.valueOf(Session.userId));
            int res = statement.executeUpdate();
            if(res > 0){
                System.out.println("Data Updated!");
                return true;
            }return false;
        }catch(SQLException er){
            System.out.println(er);
            return false;
        }
    }
}

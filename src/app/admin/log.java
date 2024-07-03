package app.admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import app.config.Conn;
import app.data.Log;
import app.data.Session;


public class log implements Initializable {

    Connection connection = null;
    String uname; 
    
    private ObservableList<Log> Log = FXCollections.observableArrayList();
    @FXML private TableView<Log> tableView1;
    @FXML private TableColumn<Log, Integer> id;
    @FXML private TableColumn<Log, Integer> no;
    @FXML private TableColumn<Log, String> username;
    @FXML private TableColumn<Log, String> time_login;
    @FXML private TableColumn<Log, String> is_login;
    @FXML private AnchorPane add_books;
    @FXML private Label nim;
    @FXML private Label name;
    @FXML private Label alert;
    @FXML private TextField idkelas;
    @FXML private Button submitid;
    @FXML private Button logout;
    @FXML private TableColumn<Log, Void> presensi;
    PreparedStatement statement;
    

    public boolean show(){
        Log.clear();
        try{
            connection = Conn.getConnection();
            String query = "SELECT id, username, time_login, is_login FROM log";
            statement = connection.prepareStatement(query);
            try(ResultSet res = statement.executeQuery()){
                int i = 0;
                while(res.next()){
                    Timestamp timestamp = res.getTimestamp("time_login");
                    LocalDateTime localDateTime = timestamp.toLocalDateTime();
                    String formattedTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    String islog;
                    if(res.getInt("is_login")!=0){
                        islog = "Loged in";
                    }else{
                        islog = "Not Loged";
                    }
                    Log.add(new Log(res.getInt("id"),i,res.getString("username"),formattedTime,islog));
                    i++;
                }
                    return (true);
                }
            }catch(SQLException e){
                System.out.println(e);
                return false;
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //Cek Apakah User Yang Masuk Telah Memiliki Sesi Yang Valid
        if(!Session.isValid()||Session.getLevel()!=3){
            System.out.println("Illegal Access to Dashboard!");
            System.exit(0);
        }
        
        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        show();
        no.setCellValueFactory(new PropertyValueFactory<>("no"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        time_login.setCellValueFactory(new PropertyValueFactory<>("time_login"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        is_login.setCellValueFactory(new PropertyValueFactory<>("is_login"));
        tableView1.setItems(Log);

        logout.setOnAction(e -> {
            Session.clearSession();
            try {
                logout.getScene().getWindow().hide();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }
}
package app.Dosen;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    @FXML private TableColumn<Log, Integer> is_login;
    @FXML private TableColumn<Log, Integer> no;
    @FXML private TableColumn<Log, String> username;
    @FXML private TableColumn<Log, String> time_login;
    @FXML private TableColumn<Log, String> islog;
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
            String query = "SELECT a.id, m.nama, a.tanggal, mt.nama as matakuliah_name, a.hadir FROM absensi a  JOIN mahasiswa m ON a.mahasiswa_id = m.id  JOIN dosen_matakuliah dm ON a.matakuliah_id = dm.matakuliah_id  JOIN matakuliah mt ON dm.matakuliah_id = mt.id  WHERE dm.dosen_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.valueOf(Session.userId));
            try(ResultSet res = statement.executeQuery()){
                int i = 0;
                while(res.next()){
                    Log.add(new Log(res.getInt("id"),i+1,res.getString("nama"),String.valueOf(res.getDate("tanggal")),res.getString("matakuliah_name")));
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
        if(!Session.isValid()||Session.getLevel()!=1){
            System.out.println("Illegal Access to Dashboard!");
            System.exit(0);
        }
        
        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        show();
        no.setCellValueFactory(new PropertyValueFactory<>("no"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        time_login.setCellValueFactory(new PropertyValueFactory<>("time_login"));
        is_login.setCellValueFactory(new PropertyValueFactory<>("is_login"));
        tableView1.setItems(Log);

        logout.setOnAction(e -> {
            try {
                logout.getScene().getWindow().hide();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }
}
package app.Dosen;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import app.config.Conn;
import app.data.JadwalDosen;
import app.data.Session;



public class DashboardDosenController implements Initializable {

    private static DashboardDosenController instance;

    public static DashboardDosenController getInstance() {
        return instance;
    }

    public DashboardDosenController() {
        instance = this;
    }

    Connection connection = null;
    String uname; 
    
    private ObservableList<JadwalDosen> JadwalDosens = FXCollections.observableArrayList();
    @FXML private TableView<JadwalDosen> tableView1;
    @FXML private TableColumn<JadwalDosen, Integer> id;
    @FXML private TableColumn<JadwalDosen, Integer> no;
    @FXML private TableColumn<JadwalDosen, String> matkul;
    @FXML private TableColumn<JadwalDosen, String> sks;
    @FXML private TableColumn<JadwalDosen, String> kelas;
    @FXML private TableColumn<JadwalDosen, String> jam;
    @FXML private TableColumn<JadwalDosen, String> ruang;
    @FXML private TableColumn<JadwalDosen, Integer> status;
    @FXML private Label nim;
    @FXML private Label name;
    @FXML private Label alert;
    @FXML private Button logout;
    @FXML private Button addData;
    @FXML private Button hitungAK;
    @FXML private Button log_absen;
    @FXML private TableColumn<JadwalDosen, Void> presensi;
    @FXML private TableColumn<JadwalDosen, Void> actions;

    PreparedStatement statement;
    String statuss;

    public void refreshTableView() {
        show(); // Call the show method to refresh the data
        tableView1.refresh(); // Refresh the TableView
    }

    public boolean show(){
        JadwalDosens.clear();
        try{
            connection = Conn.getConnection();
            String query = "SELECT m.nama AS matkul, m.sks AS sks, m.jam AS jam, m.ruang AS ruang, m.kelas AS kelas, m.id AS id, m.active AS status FROM dosen d JOIN dosen_matakuliah dm ON d.id = dm.dosen_id JOIN matakuliah m ON dm.matakuliah_id = m.id WHERE d.id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, Session.userId);
            try(ResultSet res = statement.executeQuery()){
                int i = 1;
                while(res.next()){
                    if (res.getInt("status") == 1) {
                        statuss = "Active";
                    }else{
                        statuss = "Non Active";                        
                    }
                    JadwalDosens.add(new JadwalDosen(res.getInt("id"),i,res.getString("matkul"),res.getString("sks"),res.getString("kelas"),statuss,res.getString("ruang"),res.getString("jam")));
                    i++;
                }
                    return (true);
                }
            }catch(SQLException e){
                System.out.println(e);
                return false;
            }
    }

    public ArrayList<Integer> getListActive(){
        try{
            connection = Conn.getConnection();
            String query = " SELECT m.id  FROM dosen d  JOIN dosen_matakuliah dm ON d.id = dm.dosen_id  JOIN matakuliah m ON dm.matakuliah_id = m.id  WHERE d.id = ? AND m.active = 1;";
            statement = connection.prepareStatement(query);
            statement.setString(1, Session.userId);
            try(ResultSet res = statement.executeQuery()){
                ArrayList<Integer> idArray = new ArrayList<>();
                while(res.next()){
                    idArray.add(res.getInt("id"));
                }
                    return idArray;
                }
            }catch(SQLException e){
                System.out.println(e);
            }
        return null;
    }

    public boolean toggle(int id){
        // LocalDate today = LocalDate.now();
        // String dateString = today.toString();
        try{
            connection = Conn.getConnection();
            String query = " UPDATE matakuliah SET active = CASE WHEN active = 1 THEN 0 ELSE 1 END WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if(affectedRows>0){
                return true;
            }
            }catch(SQLException e){
                System.out.println(e);
            }
        
        return false;
    }
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //Cek Apakah User Yang Masuk Telah Memiliki Sesi Yang Valid
        if(!Session.isValid()){
            System.out.println("Illegal Access to Dashboard!");
            System.exit(0);
        }
        show();
                    
        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        no.setCellValueFactory(new PropertyValueFactory<>("no"));
        matkul.setCellValueFactory(new PropertyValueFactory<>("matkul"));
        sks.setCellValueFactory(new PropertyValueFactory<>("sks"));
        kelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
        ruang.setCellValueFactory(new PropertyValueFactory<>("ruang"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        jam.setCellValueFactory(new PropertyValueFactory<>("jam"));
        //Function untuk menambahkan button pada kolom presensi
        presensi.setCellFactory((Callback<TableColumn<JadwalDosen, Void>, TableCell<JadwalDosen, Void>>) new Callback<TableColumn<JadwalDosen, Void>, TableCell<JadwalDosen, Void>>() {
            @Override
            public TableCell<JadwalDosen, Void> call(final TableColumn<JadwalDosen, Void> presensi) {
                final TableCell<JadwalDosen, Void> cell = new TableCell<JadwalDosen, Void>() {
                    final Button btn = new Button("Toggle");
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            JadwalDosen JadwalDosen = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                System.out.println("Interaksi Terhadap ID : " + JadwalDosen.getId() + " & Matkul : " + JadwalDosen.getMatkul());
                                if(toggle(JadwalDosen.getId())){
                                    if(JadwalDosen.getStatus().equals("Active")){
                                        JadwalDosen.setStatus("Non Active");
                                    } else {
                                        JadwalDosen.setStatus("Active");
                                    }
                                    alert.setTextFill(Color.GREEN);
                                    alert.setText("Berhasil Dilakukan");
                                }else{
                                    alert.setTextFill(Color.FIREBRICK);
                                    alert.setText("Anda Sudah Melakukan Presensi");
                                    tableView1.refresh();
                                }
                                tableView1.refresh();
                            });
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });
        actions.setCellFactory((Callback<TableColumn<JadwalDosen, Void>, TableCell<JadwalDosen, Void>>) new Callback<TableColumn<JadwalDosen, Void>, TableCell<JadwalDosen, Void>>() {
            @Override
            public TableCell<JadwalDosen, Void> call(final TableColumn<JadwalDosen, Void> actions) {
                final TableCell<JadwalDosen, Void> cell = new TableCell<JadwalDosen, Void>() {
                    final Button btn = new Button("Edit");
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            JadwalDosen JadwalDosen = getTableView().getItems().get(getIndex());
                            btn.setOnAction(event -> {
                                System.out.println("Interaksi Terhadap ID : " + JadwalDosen.getId() + " & Matkul : " + JadwalDosen.getMatkul());
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("editData.fxml"));
                                    Parent root;
                                    root = (Parent) loader.load();
                                    editData controller = loader.getController();
                                    controller.initData(Integer.valueOf(JadwalDosen.getId()),JadwalDosen.getMatkul(),Integer.valueOf(JadwalDosen.getSks()),JadwalDosen.getKelas(), JadwalDosen.getRuang(), JadwalDosen.getJam());
                                    Stage stage = new Stage();
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.setTitle("Edit Data");
                                    stage.show();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                tableView1.refresh();
                            });
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });
        tableView1.setItems(JadwalDosens);
        
        nim.setText(Session.getNim());
        name.setText(Session.getName());

        logout.setOnAction(e -> {
            Session.clearSession();
            try {
                logout.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Dosen Login");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        addData.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addData.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Tambah Data");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        hitungAK.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hitungAK.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Program Penghitung Nilai Akhir");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        log_absen.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("log.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Log Absen");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        
    }

}
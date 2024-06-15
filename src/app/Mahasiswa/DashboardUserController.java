package app.Mahasiswa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import app.data.Books;
import app.data.Student;


public class DashboardUserController implements Initializable {
    @FXML private TableView<Books> tableView1;
    @FXML private TableColumn<Books, Integer> no;
    @FXML private TableColumn<Books, String> id;
    @FXML private TableColumn<Books, String> title;
    @FXML private TableColumn<Books, String> author;
    @FXML private TableColumn<Books, String> category;
    @FXML private TableColumn<Books, Integer> stock;
    @FXML private Button submitData;
    @FXML private AnchorPane add_books;
    @FXML private TextField noTextfield;
    @FXML private Label res_buku;
    @FXML private Label username;
    @FXML private Button logout;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //Books
        no.setCellValueFactory(new PropertyValueFactory<>("no"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
        tableView1.setItems(Books.Books);
        

        //setName
        String uname = "";
        for (Student students : Student.Student) {
            if(login.getUser().equals(students.getNim())){
                uname = students.getNamaMahasiswa();
                break;
            }
        }
        username.setText(uname);

        submitData.setOnAction(e ->{

            try {
                if(noTextfield.getText() != null){
                    for (Student students : Student.Student) {
                        if(login.getUser().equals(students.getNim())){
                            students.setTotalBukuDipinjam(students.getTotalBukuDipinjam()+1);
                            break;
                        }
                    }
                    
                    int tmp_check = 0;
                    for (Books books : Books.Books) {
                        if(Integer.valueOf(noTextfield.getText()) == (books.getNo())){
                            if (books.getStock() != 0){
                                books.setStock(books.getStock()-1);
                                res_buku.setTextFill(Color.GREEN);
                                res_buku.setText("Buku Dengan Judul "+books.getTitle()+" Berhasil Dipinjam!");
                                tmp_check = 1;
                                break;
                            }else{
                                res_buku.setTextFill(Color.FIREBRICK);
                                res_buku.setText("Stok Buku Dengan Judul "+books.getTitle()+" Sedang Kosong!");
                                tmp_check = 1;
                                break;
                            }
                        }
                    }
                    if (tmp_check==0){
                        res_buku.setTextFill(Color.FIREBRICK);
                        res_buku.setText("Buku Yang Diminta Tidak Ada!");
                    }else{
                        tableView1.refresh();
                    }
                }else{
                    res_buku.setTextFill(Color.FIREBRICK);
                    res_buku.setText("Gagal Meminjam Buku!");
                }
            } catch (Exception er) {
                res_buku.setTextFill(Color.FIREBRICK);
                res_buku.setText("Tidak Ada Data Yang Dimasukkan!");
            }

        });

        logout.setOnAction(e -> {
            try {
                logout.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("User Login");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


    }

}
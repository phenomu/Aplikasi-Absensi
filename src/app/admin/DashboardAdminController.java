package app.admin;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import app.data.Student;
import app.data.Books;


public class DashboardAdminController implements Initializable {
    @FXML private TableView<Student> tableView;
    @FXML private TableColumn<Student, Integer> noColumn;
    @FXML private TableColumn<Student, String> namaMahasiswaColumn;
    @FXML private TableColumn<Student, String> nimColumn;
    @FXML private TableColumn<Student, String> jurusan;
    @FXML private TableColumn<Student, String> prodi;
    @FXML private TableColumn<Student, Integer> totalBukuDipinjamColumn;
    @FXML private TableView<Books> tableView1;
    @FXML private TableColumn<Books, Integer> no;
    @FXML private TableColumn<Books, String> id;
    @FXML private TableColumn<Books, String> title;
    @FXML private TableColumn<Books, String> author;
    @FXML private TableColumn<Books, String> category;
    @FXML private TableColumn<Books, Integer> stock;
    @FXML private Button add_book;
    @FXML private Button add_student;
    @FXML private Button add_data_students;
    @FXML private Button add_data_books;
    @FXML private AnchorPane add_students;
    @FXML private AnchorPane add_books;
    @FXML private TextField namaTextfield;
    @FXML private TextField nimTextfield;
    @FXML private TextField jurusanTextfield;
    @FXML private TextField prodiTextfield;
    @FXML private TextField titleTextfield;
    @FXML private TextField authorTextfield;
    @FXML private TextField categoryTextfield;
    @FXML private TextField stockTextfield;
    @FXML private Label res_student;
    @FXML private Label res_buku;
    @FXML private Button logout;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Students
        noColumn.setCellValueFactory(new PropertyValueFactory<>("no"));
        namaMahasiswaColumn.setCellValueFactory(new PropertyValueFactory<>("namaMahasiswa"));
        nimColumn.setCellValueFactory(new PropertyValueFactory<>("nim"));
        jurusan.setCellValueFactory(new PropertyValueFactory<>("jurusan"));
        prodi.setCellValueFactory(new PropertyValueFactory<>("prodi"));
        totalBukuDipinjamColumn.setCellValueFactory(new PropertyValueFactory<>("totalBukuDipinjam"));
        
        //Books
        no.setCellValueFactory(new PropertyValueFactory<>("no"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        

        tableView.setItems(Student.Student);
        tableView1.setItems(Books.Books);

        // Take User Info setiap data
        // Student selectedStudent = null;
        // for (Student student : Student.Student) {
        //     if (student.getNo() == 3) {
        //         selectedStudent = student;
        //         break;
        //     }
        // }
        // System.out.println(selectedStudent.getNo());

        add_student.setOnAction(e -> {
            if(add_books.isVisible()){
                add_books.setVisible(!add_books.isVisible());
                add_books.setManaged(!add_books.isManaged());
            }
            add_students.setVisible(!add_students.isVisible());
            add_students.setManaged(!add_students.isManaged());
        });

        add_book.setOnAction(e -> {
            if(add_students.isVisible()){
                add_students.setVisible(!add_students.isVisible());
                add_students.setManaged(!add_students.isManaged());
            }
            add_books.setVisible(!add_books.isVisible());
            add_books.setManaged(!add_books.isManaged());
        });

        
        add_data_students.setOnAction(e -> {
            Student selectedStudent = null;
            for (Student student : Student.Student) {
                selectedStudent = student;
            }
            int no_tmp = selectedStudent.getNo();
            if(nimTextfield.getText().length() != 15){
                res_student.setTextFill(Color.FIREBRICK);
                res_student.setText("Panjang Nim Harus 15 Digit!");
            }else{
                int check = 0;
                for (Student student : Student.Student) {
                    if(student.getNim().equals(nimTextfield.getText())){
                        res_student.setTextFill(Color.FIREBRICK);
                        res_student.setText("NIM Sudah Terdaftar!");
                        check = 1;
                        break;
                    }
                }
                if(check != 1){
                    res_student.setTextFill(Color.GREEN);
                    res_student.setText("Data Berhasil Ditambahkan!");
                    Student.Student.add(new Student(no_tmp+1, namaTextfield.getText(), nimTextfield.getText(), jurusanTextfield.getText(), prodiTextfield.getText(), 0));
                }
            }
        });

        add_data_books.setOnAction(e -> {
            Books selectedBooks = null;
            for (Books books : Books.Books) {
                selectedBooks = books;
            }
            int no_tmp_2 = selectedBooks.getNo();
            String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            int length = 14;
            for(int i = 0; i < length; i++) {
                int index = random.nextInt(alphabet.length());
                char randomChar = alphabet.charAt(index);
                if(i==4||i==9){
                    sb.append("-");
                }else{
                    sb.append(randomChar);
                }
            }
            String id = sb.toString();
            try {
                res_buku.setTextFill(Color.GREEN);
                res_buku.setText("Data Berhasil Ditambahkan!");
                Books.Books.add(new Books(no_tmp_2+1, id, titleTextfield.getText(), authorTextfield.getText(), categoryTextfield.getText(), Integer.valueOf(stockTextfield.getText())));
            } catch (Exception d) {
                res_buku.setTextFill(Color.FIREBRICK);
                res_buku.setText("Data Gagal Ditambahkan!");
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
                stage.setTitle("Admin Login");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

}
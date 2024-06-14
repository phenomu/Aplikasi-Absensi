package app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import app.data.Books;
import app.data.Student;

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
        try {
            if(Student.get() != 0){
                System.out.println("oke");
            }else{
                //add temp Students
                Student.Student.add(new Student(1, "Hadrian Rakha", "202310370311483", "Teknik", "Informatika", 5));
                Student.Student.add(new Student(2, "Bilal Nyetut", "202310370311484", "Teknik", "Informatika", 2));
                Student.Student.add(new Student(3, "Udin Kopling", "202310370311485", "Teknik", "Informatika", 0));

                //add Books
                Books.Books.add(new Books(1,"6234-e241-ab1c", "World War II", "Phenom", "Sejarah",5));
                Books.Books.add(new Books(2,"388c-e681-9152","Bloom Into You","Hakuze","Novel",4));
                Books.Books.add(new Books(3,"d95e-0c4a-9523","Okaeri Alice","Hakuze","Manga",2));
                Books.Books.add(new Books(4,"ed90-be30-5cdb","Sejarah Udin Kopling","Phenom","Sejarah",1));
            };
        } catch (Exception e) {
            // passed
        }
        launch(args);
    }
}
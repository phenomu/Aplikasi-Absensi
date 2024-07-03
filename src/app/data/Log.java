package app.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class Log {
    private int id;
    private int no;
    private String time_login;
    private String username;
    private String is_login;

    @FXML
    public static ObservableList<Jadwal> Log = FXCollections.observableArrayList();

    public Log(int id, int no, String username, String time_login, String is_login) {
        this.id = id;
        this.no = no;
        this.username = username;
        this.time_login = time_login;
        this.is_login = is_login;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getIs_login() {
        return is_login;
    }

    public void setIs_login(String is_login) {
        this.is_login = is_login;
    }

    public void setTime_login(String time_login) {
        this.time_login = time_login;
    }

    public String getTime_login() {
        return time_login;
    }
    
}

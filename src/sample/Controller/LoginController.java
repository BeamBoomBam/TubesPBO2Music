package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.DAO.UserDAO;
import sample.Main.Main;
import sample.Model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    public AnchorPane rootPane;
    public TextField username;
    public PasswordField password;

    public void buttonlogin(ActionEvent actionEvent) throws IOException {
        UserDAO user = new UserDAO();
        String textuser = username.getText();
        String passtext = password.getText();

        int Result = user.Login(textuser, passtext);
        if (Result == 1){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
            rootPane.getChildren().setAll(pane);
        }
        else {
            System.out.println("Salah Username / Password!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import sample.Main.Main;

import java.io.IOException;

public class LoginController {


    public void buttonlogin(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Main.class.getResource("../View/sample.fxml"));
        Stage thirdpage = new Stage();
        thirdpage.setTitle("Login");
        thirdpage.setScene(new Scene(root));
        thirdpage.show();

    }
}

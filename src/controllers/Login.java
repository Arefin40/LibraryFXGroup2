package controllers;

import application.Loader;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Login {
   private Stage stage;

   @FXML
   void login(ActionEvent event) {
      String type = ((Button) event.getSource()).getText();
      Loader.isAdmin = type.equals("Login as a Librarian");

      try {
         Parent root = FXMLLoader.load(getClass().getResource("../resources/SidebarNavigation.fxml"));
         Scene scene = new Scene(root);
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}

package controllers;

import application.Store;
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
      String page = "";

      if (type.equals("Login as a Librarian")) {
         Store.isAdmin = true;
         page = "AdminPanel";
      } else {
         Store.isAdmin = false;
         page = "UserPanel";
      }

      try {
         Parent root = FXMLLoader.load(getClass().getResource("../resources/" + page + ".fxml"));
         Scene scene = new Scene(root);
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}

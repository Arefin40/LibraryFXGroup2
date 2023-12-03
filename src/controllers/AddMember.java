package controllers;

import application.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddMember {

   @FXML
   private TextField memberId, memberName;

   @FXML
   void clickAddButton(ActionEvent event) {
      String id = memberId.getText();
      String name = memberName.getText();
      if (isValid(id) && isValid(name)) {
         Store.library.addMember(id, name);
      }
      memberId.clear();
      memberName.clear();
   }

   private boolean isValid(String text) {
      return !(text == null || text.isBlank());
   }
}

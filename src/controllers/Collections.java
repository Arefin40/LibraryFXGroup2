package controllers;

import library.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Store;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Collections implements Initializable {

   @FXML
   private TableView<Item> table;

   @FXML
   private TableColumn<Item, String> title, category, authors, publiser;

   @FXML
   private TableColumn<Item, Integer> pyear;

   @FXML
   private TableColumn<Item, Button> action;

   @FXML
   private Button searchBtn;

   @FXML
   private TextField searchByAuthor;

   @FXML
   private TextField searchByTitle;

   private ObservableList<Item> getItems() {
      ObservableList<Item> items = FXCollections.observableArrayList();
      switch (Store.currentPage) {
         case "Book":
            items.addAll(Store.library.findItems("Book"));
            break;
         case "Publication":
            items.addAll(Store.library.findItems("Publication"));
            break;
         case "Movie":
            items.addAll(Store.library.findItems("Movie"));
            break;
         default:
            items.addAll(Store.library.getItems());
            break;
      }
      return items;
   }

   public void loadItems() {
      title.setCellValueFactory(new PropertyValueFactory<>("title"));
      category.setCellValueFactory(new PropertyValueFactory<>("category"));
      authors.setCellValueFactory(cellData -> {
         String authorString = cellData.getValue().getAuthors().toString();
         return new SimpleStringProperty(authorString.substring(1, authorString.length() - 1));
      });
      pyear.setCellValueFactory(new PropertyValueFactory<>("publishYear"));
      publiser.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
      action.setCellFactory(column -> new ViewDetailsButton());
      table.setItems(getItems());
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      loadItems();
   }
}

class ViewDetailsButton extends javafx.scene.control.TableCell<Item, Button> {
   private final Button btn;

   public ViewDetailsButton() {
      this.btn = new Button("View Details");

      this.btn.setOnAction(event -> {
         Item currentItem = getTableView().getItems().get(getIndex());

         FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/ViewDetails.fxml"));
         try {
            Parent page = loader.load();
            ViewDetails controller = loader.getController();
            controller.display(currentItem.getItemId());
            Store.nevigateTo(page);
         } catch (IOException e) {
            e.printStackTrace();
         }
      });
   }

   @Override
   protected void updateItem(Button item, boolean empty) {
      super.updateItem(item, empty);
      if (empty) {
         setGraphic(null);
      } else {
         setGraphic(btn);
      }
   }
}
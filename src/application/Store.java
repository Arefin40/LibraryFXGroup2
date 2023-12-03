package application;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import library.DataHandler;
import library.Library;

public class Store {
   public static Library library = null;
   public static VBox rootContainer = null;
   public static String currentPage = "Home";
   public static boolean isAdmin = false;

   public static void startServer() {
      try {
         System.out.println("Loaded successfully!");
         library = DataHandler.loadData();
      } catch (Exception e) {
         System.out.println("Failed to load! Created new one.");
         library = new Library("Java Library");
      }
   }

   public static void closeServer() {
      boolean isSuccessful = DataHandler.saveData(library);
      System.out.println(isSuccessful ? "Saved successfully!" : "Failed to save data");
   }

   public static void nevigateTo(Parent page) {
      rootContainer.getChildren().clear();
      rootContainer.getChildren().add(page);
   }
}

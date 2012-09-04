package moneytrackerfxclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import moneytrackerconsoleclient.*;

public class MoneyTrackerFXClient extends Application {

    private static MoneyTrackerFXClient instance;

    public MoneyTrackerFXClient() {
        instance = this;
    }

    public static MoneyTrackerFXClient getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) {




        StackPane root = new StackPane();
        //root.getChildren().add(btn);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("MoneyTracker Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

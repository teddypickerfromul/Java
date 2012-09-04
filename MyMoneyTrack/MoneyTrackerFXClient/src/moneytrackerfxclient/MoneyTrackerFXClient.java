package moneytrackerfxclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import moneytrackerconsoleclient.*;

public class MoneyTrackerFXClient extends Application {

    private Stage stage;
    private static MoneyTrackerFXClient instance;

    public MoneyTrackerFXClient() {
        instance = this;
    }

    public static MoneyTrackerFXClient getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            gotoLoginForm();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(MoneyTrackerFXClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(MoneyTrackerFXClient.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 600, 480);
            //TODO: редирект на login_form.fxml
            //scene.getStylesheets().add(MoneyTrackerFXClient.class.getResource("demo.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }

    private void gotoLoginForm() {
        try {
            replaceSceneContent("forms/login_form.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MoneyTrackerFXClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoRegistrationForm() {
        try {
            //TODO: Загрузка из пакета
            replaceSceneContent("forms/reg_form.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MoneyTrackerFXClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

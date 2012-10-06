package moneytrackerfxclient;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import moneytrackerconsoleclient.methods.*;
import moneytrackerfxclient.utils.*;

public class MoneyTrackerFXClient extends Application {

    private Stage stage;

    public Stage getStage() {
        return stage;
    }
    private static MoneyTrackerFXClient instance;
    private User currentUser;
    MoneyTrackerController clientController;

    public MoneyTrackerController getClientController() {
        return clientController.getInstance();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public MoneyTrackerFXClient() {
        instance = this;

    }

    public static MoneyTrackerFXClient getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            /*MoneyTrackerController clientController;*/
            currentUser = null;
            stage = primaryStage;
            gotoLoginForm();
            primaryStage.show();

        } catch (Exception ex) {
            Logger.getLogger(MoneyTrackerFXClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //TODO: погонять
    public void resetCurrentUser() {
        currentUser = null;
    }

    //TODO: убрать хардкод
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
        stage.setResizable(false);
        //TODO: scene.centerOnScreen
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        getStage().setX(gd.getDisplayMode().getWidth() / 2 - getStage().getWidth() / 2);
        getStage().setY(gd.getDisplayMode().getHeight() / 2 - getStage().getHeight() / 2);
        return page;
    }

    public void gotoLoginForm() {
        try {
            getStage().setTitle("MoneyTracker");
            stage.getIcons().add(new Image("moneytrackerfxclient/forms/logo_icon.png"));
            replaceSceneContent("forms/login_form.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MoneyTrackerFXClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoProductsForm() {
        try {
            getStage().setTitle("MoneyTracker::Товары");
            replaceSceneContent("forms/products_form.fxml");
            getStage().setWidth(1024.0);
            getStage().setHeight(800.0);
            //TODO: вынести в AppSettings
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            getStage().setX(gd.getDisplayMode().getWidth() / 2 - getStage().getWidth() / 2);
            getStage().setY(gd.getDisplayMode().getHeight() / 2 - getStage().getHeight() / 2);
        } catch (Exception ex) {
            Logger.getLogger(MoneyTrackerFXClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //TODO: перенести все размеры в AppSettings
    public void resetStageSize() {
        getStage().setWidth(600.0);
        getStage().setHeight(480.0);
    }

    public boolean login(/*MoneyTrackerController controller,*/String login, String password) throws LoginErrorException_Exception {

        long currentUser_id = this.clientController.getInstance().getClientPort().loginUser(login, password);
        System.out.println(currentUser_id);
        if (currentUser_id == -1) {
            gotoFailForm();
            return false;
        } else {
            currentUser = this.clientController.getInstance().getClientPort().getUserById(currentUser_id);
            gotoAppForm();
            return true;
        }
    }

    public boolean register(/*MoneyTrackerController controller,*/String login, String password, String email) throws IOException_Exception, InvalidFileFormatException_Exception, RegErrorException_Exception {
        currentUser = this.clientController.getInstance().getClientPort().registerUser(login, password, email);
        if (currentUser == null) {
            gotoFailForm();
            return false;
        } else {
            gotoAppForm();
            return true;
        }
    }

    public void gotoRegistrationForm() {
        try {
            getStage().setTitle("MoneyTracker::Регистрация");
            replaceSceneContent("forms/reg_form.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MoneyTrackerFXClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoFailForm() {
        try {
            getStage().setTitle("MoneyTracker::Ошибка");
            replaceSceneContent("forms/err_form.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MoneyTrackerFXClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoAppForm() {
        try {
            getStage().setTitle("MoneyTracker::Статистика");
            replaceSceneContent("forms/app_stub.fxml");
            getStage().setWidth(1024.0);
            getStage().setHeight(800.0);
            //TODO: вынести в AppSettings
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            getStage().setX(gd.getDisplayMode().getWidth() / 2 - getStage().getWidth() / 2);
            getStage().setY(gd.getDisplayMode().getHeight() / 2 - getStage().getHeight() / 2);
        } catch (Exception ex) {
            Logger.getLogger(MoneyTrackerFXClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

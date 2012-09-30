package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import moneytrackerconsoleclient.methods.LoginErrorException_Exception;
import moneytrackerfxclient.MoneyTrackerFXClient;
import moneytrackerfxclient.utils.MoneyTrackerController;

public class Login_formController implements Initializable {

    @FXML
    private TextField login_field;
    @FXML
    private PasswordField pass_field;
    @FXML
    private AnchorPane login_pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerOnEnterHandler();
    }

    public String getLogin() {
        return login_field.getText().trim();
    }

    public String getPassword() {
        return pass_field.getText().trim();
    }

    @FXML
    protected boolean checkCredentials() throws LoginErrorException_Exception {
        if (!MoneyTrackerFXClient.getInstance().login(this.getLogin(), this.getPassword())) {
            return false;
        } else {
            return true;
        }
    }

    @FXML
    protected void registerUser() {
        MoneyTrackerFXClient.getInstance().gotoRegistrationForm();
    }

    @FXML
    private void registerOnEnterHandler() {
        pass_field.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (!(login_field.getText().isEmpty()) && !pass_field.getText().isEmpty()) {
                        try {
                            checkCredentials();
                        } catch (LoginErrorException_Exception ex) {
                            Logger.getLogger(Login_formController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
    }
}


package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import moneytrackerconsoleclient.methods.LoginErrorException_Exception;
import moneytrackerfxclient.MoneyTrackerFXClient;
import moneytrackerfxclient.utils.MoneyTrackerController;

public class Login_formController implements Initializable {

    @FXML
    private TextField login_field;
    @FXML
    private PasswordField pass_field;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public String getLogin() {
        return login_field.getText().trim();
    }

    public String getPassword() {
        return pass_field.getText().trim();
    }

    @FXML
    protected boolean checkCredentials() throws LoginErrorException_Exception {
        if (!MoneyTrackerFXClient.getInstance().login(/*MoneyTrackerController.getInstance(),*/ this.getLogin(), this.getPassword())) {
            return false;
        } else {
            return true;
        }
    }
}

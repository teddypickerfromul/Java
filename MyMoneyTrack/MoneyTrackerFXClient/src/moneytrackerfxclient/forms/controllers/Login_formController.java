/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author teddy
 */
public class Login_formController implements Initializable {

    @FXML
    private TextField login_field;
    @FXML
    private PasswordField pass_field;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public String getLogin() {
        return login_field.getText().trim();
    }
    
    public String getPassword(){
        return pass_field.getText().trim();
    }
}

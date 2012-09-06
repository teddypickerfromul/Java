/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import moneytrackerconsoleclient.methods.IOException_Exception;
import moneytrackerconsoleclient.methods.InvalidFileFormatException_Exception;
import moneytrackerconsoleclient.methods.RegErrorException_Exception;
import moneytrackerfxclient.*;

public class Reg_formController implements Initializable {

    @FXML
    private TextField reg_login_field;
    @FXML
    private PasswordField reg_pass_field;
    @FXML
    private TextField email_reg_field;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void Back() {
        MoneyTrackerFXClient.getInstance().gotoLoginForm();
    }

    public String getLogin() {
        return reg_login_field.getText().trim();
    }

    public String getPassword() {
        return reg_pass_field.getText().trim();
    }

    public String getEmail() {
        return email_reg_field.getText().trim();
    }

    @FXML
    protected boolean registerUser() {
        try {
            if (!MoneyTrackerFXClient.getInstance().register(/*MoneyTrackerController.getInstance(),*/this.getLogin(), this.getPassword(), this.getEmail())) {
                return false;
            } else {
                return true;
            }
        } catch (IOException_Exception ex) {
            Logger.getLogger(Reg_formController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InvalidFileFormatException_Exception ex) {
            Logger.getLogger(Reg_formController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (RegErrorException_Exception ex) {
            Logger.getLogger(Reg_formController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

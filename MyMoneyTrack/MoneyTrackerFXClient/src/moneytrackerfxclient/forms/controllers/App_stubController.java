
package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import moneytrackerfxclient.*;

public class App_stubController implements Initializable {

    @FXML
    private Label UserName;
    
    @FXML
    private Button exit_button;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        greetUser();
    }

    @FXML
    protected void greetUser(){
        System.out.println(MoneyTrackerFXClient.getInstance().getCurrentUser().getLogin());
        UserName.setText(MoneyTrackerFXClient.getInstance().getCurrentUser().getLogin());
    }
    
    @FXML
    protected void exit(){
        MoneyTrackerFXClient.getInstance().gotoLoginForm();
    }
}

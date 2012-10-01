
package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import moneytrackerfxclient.*;

public class Err_formController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void Back(){
        MoneyTrackerFXClient.getInstance().gotoLoginForm();
    }
}

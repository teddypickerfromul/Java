/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import moneytrackerfxclient.*;

public class App_stubController implements Initializable {

    @FXML
    private Label name_label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        test();
    }

    public void test() {
        name_label.setText(MoneyTrackerFXClient.getInstance().getCurrentUser().getLogin());
    }
}

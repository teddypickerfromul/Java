package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import moneytrackerfxclient.MoneyTrackerFXClient;

public class Outlays_formController implements Initializable {

    @FXML
    private Label UserName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected void exit() {
        MoneyTrackerFXClient.getInstance().gotoLoginForm();
        MoneyTrackerFXClient.getInstance().resetCurrentUser();
    }

    @FXML
    protected void gotoStatsForm() {
        MoneyTrackerFXClient.getInstance().gotoAppForm();
    }

    @FXML
    protected void greetUser() {
        UserName.setText(MoneyTrackerFXClient.getInstance().getCurrentUser().getLogin());
    }
}

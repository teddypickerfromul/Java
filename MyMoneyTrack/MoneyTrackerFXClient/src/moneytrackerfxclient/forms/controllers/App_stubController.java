package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import moneytrackerfxclient.*;

public class App_stubController implements Initializable {

    @FXML
    private Label UserName;
    @FXML
    private Button exit_button;
    @FXML
    private PieChart status_chart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        greetUser();
        status_chart.setData(prepareChartData());
        status_chart.setLegendVisible(false);
    }

    @FXML
    protected void greetUser() {
        System.out.println(MoneyTrackerFXClient.getInstance().getCurrentUser().getLogin());
        UserName.setText(MoneyTrackerFXClient.getInstance().getCurrentUser().getLogin());
    }

    @FXML
    protected void exit() {
        MoneyTrackerFXClient.getInstance().gotoLoginForm();
        MoneyTrackerFXClient.getInstance().resetCurrentUser();
    }
    
    
    protected ObservableList<PieChart.Data> prepareChartData(){
        ObservableList<PieChart.Data> statusChartData =
                FXCollections.observableArrayList(new PieChart.Data("Потрачено", calc()), new PieChart.Data("Осталось", getCurrentUserBudgetsAmount()-calc()));
        return statusChartData;
    }

    //TODO: пересмотреть логику
    protected double calc() {
        return getCurrentUserIncomesAmount() - getCurrentUserOutlaysAmount();
    }

    protected double getCurrentUserBudgetsAmount() {
        return MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getOverralUserBudgetSumByOwner(MoneyTrackerFXClient.getInstance().getCurrentUser().getId());
    }

    protected double getCurrentUserIncomesAmount() {
        return MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getOverralUserIncomeSumByUser(MoneyTrackerFXClient.getInstance().getCurrentUser().getId());
    }

    protected double getCurrentUserOutlaysAmount() {
        return MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getOverralUserOutlaySumByUser(MoneyTrackerFXClient.getInstance().getCurrentUser().getId());
    }
}

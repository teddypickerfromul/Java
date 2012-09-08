package moneytrackerfxclient.forms.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import moneytrackerfxclient.*;

public class App_stubController implements Initializable {

    private double CurrentUserBudgetsAmount;
    private double CurrentUserIncomesAmount;
    private double CurrentUserOutlaysAmount;
    private double calculatedUserBudget;
    private List userOutlayList;
    private List userIncomeList;
    @FXML
    private Label UserName;
    @FXML
    private Button exit_button;
    @FXML
    private PieChart status_chart;
    @FXML
    private Label percentage_label;
    @FXML
    private Label usermoney;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        greetUser();
        updateOverralDataStatus();
        status_chart.setData(prepareChartData());
        status_chart.setLegendVisible(false);
        status_chart.setLabelLineLength(0);
        updateUserMoneyLabel();
        updateUserPercLabel();

    }

    @FXML
    protected void greetUser() {
        UserName.setText(MoneyTrackerFXClient.getInstance().getCurrentUser().getLogin());
    }

    @FXML
    protected void exit() {
        MoneyTrackerFXClient.getInstance().gotoLoginForm();
        MoneyTrackerFXClient.getInstance().resetCurrentUser();
    }

    //из-за бага со скрытием легенды
    protected ObservableList<PieChart.Data> prepareChartData() {
        //updateOverralDataStatus();
        ObservableList<PieChart.Data> statusChartData =
                FXCollections.observableArrayList(new PieChart.Data("", this.CurrentUserOutlaysAmount), new PieChart.Data("", this.CurrentUserBudgetsAmount - this.CurrentUserOutlaysAmount));
        return statusChartData;
    }

    protected double calc() {
        this.calculatedUserBudget = this.CurrentUserIncomesAmount - this.CurrentUserOutlaysAmount;
        return this.calculatedUserBudget;
    }

    protected double getCurrentUserBudgetsAmount() {
        this.CurrentUserBudgetsAmount = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getOverralUserBudgetSumByOwner(MoneyTrackerFXClient.getInstance().getCurrentUser().getId());
        return this.CurrentUserBudgetsAmount;
    }

    protected double getCurrentUserIncomesAmount() {
        this.CurrentUserIncomesAmount = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getOverralUserIncomeSumByUser(MoneyTrackerFXClient.getInstance().getCurrentUser().getId());
        return this.CurrentUserIncomesAmount;
    }

    protected double getCurrentUserOutlaysAmount() {
        this.CurrentUserOutlaysAmount = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getOverralUserOutlaySumByUser(MoneyTrackerFXClient.getInstance().getCurrentUser().getId());
        return this.CurrentUserOutlaysAmount;
    }

    @FXML
    protected void updateUserMoneyLabel() {
        Double roundedResult = new BigDecimal(this.CurrentUserOutlaysAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        usermoney.setText(roundedResult.toString());
    }

    @FXML
    protected void updateUserPercLabel() {
        Integer percentage = (int) ((this.CurrentUserOutlaysAmount) / (this.CurrentUserBudgetsAmount) * 100.0);
        percentage_label.setText(percentage.toString());
    }

    protected void updateOverralDataStatus() {
        getCurrentUserBudgetsAmount();
        getCurrentUserIncomesAmount();
        getCurrentUserOutlaysAmount();
        calc();
    }

    protected int getCurrentUserOutlaysCount() {
    }
}

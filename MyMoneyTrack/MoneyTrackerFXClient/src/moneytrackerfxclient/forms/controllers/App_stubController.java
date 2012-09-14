package moneytrackerfxclient.forms.controllers;

import moneytrackerfxclient.utils.CustomFormattedDateTime;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import moneytrackerfxclient.*;
import moneytrackerfxclient.utils.CustomFormattedDateTime;
import moneytrackerfxclient.utils.MoneyTrackerController;

public class App_stubController implements Initializable {

    private double CurrentUserBudgetsAmount;
    private double CurrentUserIncomesAmount;
    private double CurrentUserOutlaysAmount;
    private double calculatedUserBudget;
    private List userOutlayList;
    private List userIncomeList;
    private int userProductsCount;
    private int CurrentUserYearOperations;
    private int CurrentUserMounthOperations;
    private CustomFormattedDateTime datetime;
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
    @FXML
    private Label ov_incomes_label;
    @FXML
    private Label ov_outlays_label;
    @FXML
    private Label ov_products_label;
    @FXML
    private Label ov_last_year;
    @FXML
    private Label ov_last_mounth;
    @FXML
    private AnchorPane content_pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.datetime = new CustomFormattedDateTime();
        greetUser();
        updateUI();
    }

    public void updateUI() {
        updateOverralDataStatus();
        status_chart.setData(prepareChartData());
        status_chart.setLegendVisible(false);
        status_chart.setLabelLineLength(0);
        fadeOutContentPane();
        updateUserMoneyLabel();
        updateUserPercLabel();
        updateUserIncomeCountLabel();
        updateUserOutlayCountLabel();
        updateProductsCountLabel();
        updateCurrentUserYearOperationsLabel();
        updateCurrentUserMounthOperationsLabel();
        //MoneyTrackerFXClient.getInstance().getClientController().getClientPort().goodbye();
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

    @FXML
    protected void updateUserIncomeCountLabel() {
        ov_incomes_label.setText(Integer.toString(this.userIncomeList.size()));
    }

    @FXML
    protected void updateUserOutlayCountLabel() {
        ov_outlays_label.setText(Integer.toString(this.userOutlayList.size()));
    }

    @FXML
    protected void updateProductsCountLabel() {
        ov_products_label.setText(Integer.toString(this.userProductsCount));
    }

    @FXML
    private void updateCurrentUserYearOperationsLabel() {
        ov_last_year.setText(Integer.toString(this.CurrentUserYearOperations));
    }

    @FXML
    private void updateCurrentUserMounthOperationsLabel() {
        ov_last_mounth.setText(Integer.toString(this.CurrentUserMounthOperations));
    }

    @FXML
    protected void gotoProductsForm() {
        MoneyTrackerFXClient.getInstance().gotoProductsForm();
    }

    protected void updateOverralDataStatus() {
        getCurrentUserBudgetsAmount();
        getCurrentUserIncomesAmount();
        getCurrentUserOutlaysAmount();
        calc();
        getCurrentUserIncomesCount();
        getCurrentUserOutlaysCount();
        getCurrentUserProductsCount();
        getCurrentUserYearOperations();
        getCurrentUserMounthOperations();
    }

    protected int getCurrentUserOutlaysCount() {
        this.userOutlayList = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getUserOutlaysByUser(MoneyTrackerFXClient.getInstance().getCurrentUser().getId());
        System.out.println("outlays = " + MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getUserOutlaysByUser(MoneyTrackerFXClient.getInstance().getCurrentUser().getId()));
        return this.userOutlayList.size();
    }

    protected int getCurrentUserIncomesCount() {
        this.userIncomeList = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getUserIncomesByUser(MoneyTrackerFXClient.getInstance().getCurrentUser().getId());
        System.out.println("incomes = " + MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getUserIncomesByUser(MoneyTrackerFXClient.getInstance().getCurrentUser().getId()));
        return this.userIncomeList.size();
    }

    protected int getCurrentUserProductsCount() {
        this.userProductsCount = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getProductsCountUsedByUser(MoneyTrackerFXClient.getInstance().getCurrentUser().getId()).size();
        System.out.println("products = " + this.userProductsCount);
        return this.userProductsCount;
    }

    protected int getCurrentUserYearOperations() {
        this.CurrentUserYearOperations = MoneyTrackerController.getInstance().getClientPort().getAllUserIncomesByUserAndYear(MoneyTrackerFXClient.getInstance().getCurrentUser().getId(), this.datetime.now()).size();
        this.CurrentUserYearOperations += MoneyTrackerController.getInstance().getClientPort().getAllUserOutlaysByUserAndYear(MoneyTrackerFXClient.getInstance().getCurrentUser().getId(), this.datetime.now()).size();
        return this.CurrentUserYearOperations;
    }

    protected int getCurrentUserMounthOperations() {
        this.CurrentUserMounthOperations = MoneyTrackerController.getInstance().getClientPort().getAllUserIncomesByUserAndMounth(MoneyTrackerFXClient.getInstance().getCurrentUser().getId(), this.datetime.now()).size();
        this.CurrentUserMounthOperations += MoneyTrackerController.getInstance().getClientPort().getAllUserOutlaysByUserAndMounth(MoneyTrackerFXClient.getInstance().getCurrentUser().getId(), this.datetime.now()).size();
        return this.CurrentUserMounthOperations;
    }

    protected void fadeOutContentPane() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), this.content_pane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
}

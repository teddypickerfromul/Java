package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import moneytrackerconsoleclient.methods.UserOutlay;
import moneytrackerfxclient.MoneyTrackerFXClient;
import moneytrackerfxclient.forms.controllers.custom.EditingIntCell;
import moneytrackerfxclient.forms.controllers.custom.EditingStringCell;
import moneytrackerfxclient.forms.controllers.custom.ProductWrapper;
import moneytrackerfxclient.forms.controllers.custom.UserOutlayWrapper;
import org.apache.log4j.Logger;

public class Outlays_formController implements Initializable {

    public static final Logger LOG = Logger.getLogger(Outlays_formController.class);
    
    @FXML
    private Label UserName;
    @FXML
    private TableView outlays_table;
    @FXML
    private TableColumn outlay_datetime_column;
    @FXML
    private TableColumn outlay_product_column;
    @FXML
    private TableColumn outlay_count_column;
    @FXML
    private TableColumn outlay_overral_column;
    
    private List plainOutlaysList = new ArrayList() {};
    
    private final ObservableList<UserOutlay> observableOutlaysList = FXCollections.observableArrayList();

    public void updateOutlaysList() {
        plainOutlaysList = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getUserOutlaysByUser(MoneyTrackerFXClient.getInstance().getCurrentUser().getId());
        Iterator it = plainOutlaysList.iterator();
        while (it.hasNext()) {
            observableOutlaysList.add(new UserOutlayWrapper((UserOutlay) it.next()));
            //plainOutlaysList.remove(it);
        }
    }

    public void setOutlaysTableCellValueFactories() {
        outlay_datetime_column.setCellValueFactory(new PropertyValueFactory<UserOutlayWrapper, String>("datetime"));
        outlay_product_column.setCellValueFactory(new PropertyValueFactory<UserOutlayWrapper, String>("productName"));
        outlay_overral_column.setCellValueFactory(new PropertyValueFactory<UserOutlayWrapper, Double>("overralSum"));
        outlay_count_column.setCellValueFactory(new PropertyValueFactory<UserOutlayWrapper, Integer>("products_count"));
        outlays_table.setItems(observableOutlaysList);
    }

    private Callback<TableColumn, TableCell> createEditableCellFactory() {
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingStringCell();
            }
        };
        return cellFactory;
    }
    
    private Callback<TableColumn, TableCell> createEditableIntCellFactory() {
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingIntCell();
            }
        };
        return cellFactory;
    }    
    
    protected void registerColumnHandlers() {
        outlay_datetime_column.setCellFactory(createEditableCellFactory());
        //outlay_product_column.setCellFactory(createEditableCellFactory());
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        outlay_datetime_column.setEditable(true);
        //outlay_product_column.setEditable(true);
        registerColumnHandlers();
        setOutlaysTableCellValueFactories();
        updateOutlaysList();
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

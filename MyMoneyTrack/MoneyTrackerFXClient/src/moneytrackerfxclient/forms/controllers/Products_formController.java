package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import moneytrackerconsoleclient.methods.Product;
import moneytrackerfxclient.MoneyTrackerFXClient;

public class Products_formController implements Initializable {
    
    @FXML
    private AnchorPane content_pane;
    @FXML
    private Label UserName;
    @FXML
    private TableView products_table;
    @FXML
    private TableColumn products_table_name;
    @FXML
    private TableColumn products_table_desc;
    @FXML
    private TableColumn products_table_cost;
    private List<Product> ls = new ArrayList<Product>() {
    };
    private final ObservableList<Product> data = FXCollections.observableArrayList();
    
    public void updateProductsList() {
        ls = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getAllProducts();
        Iterator it = ls.iterator();
        while (it.hasNext()) {
            data.add((Product) it.next());
        }
    }
    
    public void setProductsTableCellFactories() {
        products_table_name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        products_table_desc.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        products_table_cost.setCellValueFactory(new PropertyValueFactory<Product, Double>("cost"));
        products_table.setItems(data);
    }
    
    protected void clearLists(){
        ls.clear();
        data.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setProductsTableCellFactories();
        updateProductsList();
        fadeOutContentPane();
    }
    
    public void updateUI() {
        clearLists();
        setProductsTableCellFactories();
        updateProductsList();
        fadeOutContentPane();
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
    
    protected void fadeOutContentPane() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), this.content_pane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    
    @FXML
    protected void greetUser() {
        UserName.setText(MoneyTrackerFXClient.getInstance().getCurrentUser().getLogin());
    }
}

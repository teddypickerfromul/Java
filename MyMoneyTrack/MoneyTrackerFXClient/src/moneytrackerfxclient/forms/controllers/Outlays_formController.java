package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import moneytrackerconsoleclient.methods.Product;
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
    private final ObservableList<String> observableNamesList = FXCollections.observableArrayList();

    public void updateOutlaysList() {
        plainOutlaysList = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getUserOutlaysByUser(MoneyTrackerFXClient.getInstance().getCurrentUser().getId());
        Iterator it = plainOutlaysList.iterator();
        while (it.hasNext()) {
            UserOutlayWrapper item = new UserOutlayWrapper((UserOutlay) it.next());
            observableOutlaysList.add(/*new UserOutlayWrapper((UserOutlay) it.next())*/ item);
            //observableNamesList.add(item.getProduct().getName());
            //plainOutlaysList.remove(it);
        }
    }

    public void updateProductsList(){
        List plainProductsList = new ArrayList();
        plainProductsList = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getAllProducts();
        Iterator it = plainProductsList.iterator();
        while (it.hasNext()) {
            Product item = (Product) it.next();
            observableNamesList.add(item.getName());
        }
    }
    
    public void setOutlaysTableCellValueFactories() {
        outlay_datetime_column.setCellValueFactory(new PropertyValueFactory<UserOutlayWrapper, String>("datetime"));
        outlay_product_column.setCellValueFactory(new PropertyValueFactory<UserOutlayWrapper, String>("productName"));
        outlay_overral_column.setCellValueFactory(new PropertyValueFactory<UserOutlayWrapper, Double>("overralSum"));
        outlay_count_column.setCellValueFactory(new PropertyValueFactory<UserOutlayWrapper, Integer>("productsCount"));
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
        outlay_count_column.setCellFactory(createEditableIntCellFactory());
        //outlay_product_column.setCellFactory(createEditableCellFactory());
        outlay_product_column.setCellFactory(new Callback<TableColumn<UserOutlayWrapper, String>,TableCell<UserOutlayWrapper, String>>(){        
            @Override
            public TableCell<UserOutlayWrapper, String> call(TableColumn<UserOutlayWrapper, String> param) {                
                TableCell<UserOutlayWrapper, String> cell = new TableCell<UserOutlayWrapper, String>(){
                    @Override
                    public void updateItem(String item, boolean empty) {
                        if(item!=null){
                           ChoiceBox choice = new ChoiceBox(observableNamesList);                                                      
                           choice.getSelectionModel().select(observableNamesList.indexOf(item));
                           setGraphic(choice);
                           System.out.println(item);
                        } 
                    }
                    
                    //@Override 
                    public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
                        System.out.println("Selected: " + newValue);
                    }
                };                           
               return cell;
            }            
        });
        
//        outlay_product_column.setOnEditCommit(
//            /*new ChangeListener<String>()*/ new EventHandler <TableColumn.CellEditEvent<Product, String>>() {
//                @Override public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
//                    System.out.println("Selected: " + newValue);
//                }
//
//            }
//        );
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        outlay_datetime_column.setEditable(true);
        //outlay_product_column.setEditable(true);
        registerColumnHandlers();
        setOutlaysTableCellValueFactories();
        updateOutlaysList();
        updateProductsList();
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

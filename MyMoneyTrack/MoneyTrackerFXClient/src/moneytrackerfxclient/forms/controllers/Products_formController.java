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
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import moneytrackerconsoleclient.methods.Product;
import moneytrackerfxclient.MoneyTrackerFXClient;
import moneytrackerfxclient.forms.controllers.custom.CheckBoxCell;
import moneytrackerfxclient.forms.controllers.custom.EditingDoubleCell;
import moneytrackerfxclient.forms.controllers.custom.EditingStringCell;

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
    @FXML
    private TableColumn products_table_remove;
    @FXML
    private TextField product_name_field;
    @FXML
    private TextField product_price_field;
    @FXML
    private TextArea product_desc_text_area;
    private List<Product> plainProductList = new ArrayList<Product>() {};
    
    private final ObservableList <Boolean> productsToRemove = FXCollections.observableArrayList();
    
    private final ObservableList <Product> observableProductList = FXCollections.observableArrayList();

    public void updateProductsList() {
        plainProductList = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getAllProducts();
        Iterator it = plainProductList.iterator();
        while (it.hasNext()) {
            observableProductList.add((Product) it.next());
            
            productsToRemove.add(Boolean.FALSE);
        }
    }


    //TODO: исправить привязку к типу поля Product
    public void setProductsTableCellValueFactories() {
        products_table_name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        products_table_desc.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        products_table_cost.setCellValueFactory(new PropertyValueFactory<Product, Double>("cost"));
        products_table_remove.setCellValueFactory(new PropertyValueFactory("selected"));
        products_table.setItems(observableProductList);
    }

    protected void clearLists() {
        plainProductList.clear();
        observableProductList.clear();
        productsToRemove.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        products_table.setEditable(true);
        products_table_name.setEditable(true);
        products_table_desc.setEditable(true);
        products_table_cost.setEditable(true);
        products_table_remove.setEditable(true);
        registerColumnHandlers();
        setProductNameColumnCellHandler();
        setProductDescColumnCellHandler();
        setProductCostColumnCellHandler();

        setProductsTableCellValueFactories();
        updateProductsList();
        fadeOutContentPane();
    }

    protected void registerColumnHandlers() {
        products_table_name.setCellFactory(createEditableCellFactory());
        products_table_desc.setCellFactory(createEditableCellFactory());
        products_table_cost.setCellFactory(createEditableDoubleCellFactory());
        products_table_remove.setCellFactory(createCheckBoxCellFactory());
    }

    public void updateUI() {
        clearLists();
        setProductsTableCellValueFactories();
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
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), this.content_pane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    @FXML
    protected boolean addNewProduct() {
        int product_name_length = product_name_field.getText().length();
        int product_desc_length = product_desc_text_area.getText().length();
        if (product_name_length < 85 && product_name_length > 0 && product_desc_length < 1000 && product_desc_length > 0 && product_price_field.getText().length() != 0) {
            MoneyTrackerFXClient.getInstance().getClientController().getClientPort().createNewProduct(product_name_field.getText(), product_desc_text_area.getText(), new Double(product_price_field.getText()));
            updateUI();
            return true;
        } else {
            return false;
        }
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

    private Callback<TableColumn, TableCell> createEditableDoubleCellFactory() {
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {

            @Override
            public TableCell call(TableColumn p) {
                return new EditingDoubleCell();
            }
        };
        return cellFactory;       
    }
    
    
    private Callback<TableColumn, TableCell> createCheckBoxCellFactory() {
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new CheckBoxCell();
            }
        };
        return cellFactory;
    }

    private void setProductNameColumnCellHandler() {
        products_table_name.setOnEditCommit(new EventHandler<CellEditEvent<Product, String>>() {
            @Override
            public void handle(CellEditEvent<Product, String> t) {
                if (!(t.getNewValue().equals(t.getOldValue()))) {
                    if (t.getNewValue().length() <= 85) {
                        String old_name = t.getOldValue();
                        Product updated = (Product) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        updated.setName(t.getNewValue());
                        MoneyTrackerFXClient.getInstance().getClientController().getClientPort().updateProductByAllParams(old_name, updated.getName(), updated.getDescription(), updated.getCost());
                    } else {
                        System.out.println("Product's name length must be less or equal to 85 characters!");
                    }
                }
            }
        });
    }

    private void setProductDescColumnCellHandler() {
        products_table_desc.setOnEditCommit(new EventHandler<CellEditEvent<Product, String>>() {
            @Override
            public void handle(CellEditEvent<Product, String> t) {
                if (!(t.getNewValue().equals(t.getOldValue()))) {
                    if (t.getNewValue().length() <= 1000) {
                        Product updated = (Product) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        System.out.println(updated.getName() + " : " + t.getNewValue());
                        MoneyTrackerFXClient.getInstance().getClientController().getClientPort().updateProductDescriptionByName(updated.getName(), t.getNewValue());
                    } else {
                        System.out.println("Product's description length must be less or equal to 1000 characters!");
                    }
                }
            }
        });
    }

    private void setProductCostColumnCellHandler() {
        products_table_cost.setOnEditCommit(new EventHandler<CellEditEvent<Product, Double>>() {
            @Override
            public void handle(CellEditEvent<Product, Double> t) {
                if (!(t.getNewValue().equals(t.getOldValue()))) {
                    if (Double.valueOf(t.getNewValue()) >= 0.0) {
                        Product updated = (Product) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        MoneyTrackerFXClient.getInstance().getClientController().getClientPort().updateProductPriceByName(updated.getName(), Double.valueOf(t.getNewValue()));
                    } else {
                        System.out.println("Product's description length must be less or equal to 1000 characters!");
                    }
                }
            }
        });
    }

    @FXML
    protected void greetUser() {
        UserName.setText(MoneyTrackerFXClient.getInstance().getCurrentUser().getLogin());
    }
}

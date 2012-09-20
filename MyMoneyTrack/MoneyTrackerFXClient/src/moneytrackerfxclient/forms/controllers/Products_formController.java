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
    private TextField product_name_field;
    @FXML
    private TextField product_price_field;
    @FXML
    private TextArea product_desc_text_area;
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

    protected void clearLists() {
        ls.clear();
        data.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        products_table.setEditable(true);
        products_table_name.setEditable(true);
        products_table_desc.setEditable(true);
        products_table_cost.setEditable(true);
        //createCustomCellFactoty();
        handleProductNameColumnCell();
        products_table_name.setCellFactory(createEditableCellFactoty());
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
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), this.content_pane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    @FXML
    protected boolean addNewProduct() {

        if (product_name_field.getText().length() < 85 && product_name_field.getText().length() != 0 && product_desc_text_area.getText().length() < 1000 && product_desc_text_area.getText().length() != 0 && product_price_field.getText().length() != 0) {

            MoneyTrackerFXClient.getInstance().getClientController().getClientPort().createNewProduct(product_name_field.getText(), product_desc_text_area.getText(), new Double(product_price_field.getText()));
            updateUI();
            return true;
        } else {
            return false;
        }
    }

    private Callback<TableColumn, TableCell> createEditableCellFactoty() {
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingStringCell();
            }
        };
        return cellFactory;
    }

    private void handleProductNameColumnCell() {
        products_table_name.setOnEditCommit(new EventHandler<CellEditEvent<Product, String>>() {
            @Override
            public void handle(CellEditEvent<Product, String> t) {
                if (t.getNewValue().equals(t.getOldValue())) {
                    System.out.println("exact : nothing to do");
                } else {
                    if (t.getNewValue().length() <= 85) {
                        String old_name = t.getOldValue();
                        Product updated = (Product) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        updated.setName(t.getNewValue());
                        System.out.println("updating for product " + updated + "...");
                        System.out.println(updated.getName());
                        MoneyTrackerFXClient.getInstance().getClientController().getClientPort().updateProductByAllParams(old_name, updated.getName(), updated.getDescription(), updated.getCost());
                        System.out.println("update completed!");
                    }
                    else {
                        System.out.println("Product name's length must be less or equal to 85 characters!");
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

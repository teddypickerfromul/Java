package moneytrackerfxclient.forms.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import moneytrackerconsoleclient.methods.Product;
import moneytrackerfxclient.MoneyTrackerFXClient;
import moneytrackerfxclient.forms.controllers.custom.EditingDoubleCell;
import moneytrackerfxclient.forms.controllers.custom.EditingStringCell;
import moneytrackerfxclient.forms.controllers.custom.ProductWrapper;

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
    @FXML
    private Button removeProductsButton;
    private List<Product> plainProductList = new ArrayList<Product>() {
    };
    private final ObservableList<ProductWrapper> observableProductList = FXCollections.observableArrayList();
    private List<String> toRemoveList = new ArrayList<String>();

    public List<String> getToRemoveList() {
        return toRemoveList;
    }

    public void updateProductsList() {
        plainProductList = MoneyTrackerFXClient.getInstance().getClientController().getClientPort().getAllProducts();
        Iterator it = plainProductList.iterator();
        while (it.hasNext()) {
            observableProductList.add(new ProductWrapper((Product) it.next()));
            plainProductList.remove(it);
        }
    }

    //TODO: исправить привязку к типу поля Product
    public void setProductsTableCellValueFactories() {

        products_table_name.setCellValueFactory(new PropertyValueFactory<ProductWrapper, String>("name"));
        products_table_desc.setCellValueFactory(new PropertyValueFactory<ProductWrapper, String>("description"));
        products_table_cost.setCellValueFactory(new PropertyValueFactory<ProductWrapper, Double>("cost"));
        products_table_remove.setCellValueFactory(new PropertyValueFactory<ProductWrapper, BooleanProperty>("to_delete"));
        products_table.setItems(observableProductList);
    }

    protected void clearLists() {
        plainProductList.clear();
        observableProductList.clear();
        toRemoveList.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this.removeProductsButton.setDisable(true);
        products_table.setEditable(true);
        products_table_name.setEditable(true);
        products_table_desc.setEditable(true);
        products_table_cost.setEditable(true);
        products_table_remove.setEditable(true);
        greetUser();
        registerColumnHandlers();
        setProductNameColumnCellHandler();

        setProductCostColumnCellHandler();

        //setProductDeleteCellHandler();
        setProductDescColumnCellHandler();
        setProductsTableCellValueFactories();
        updateProductsList();
        fadeOutContentPane();
    }

    protected void registerColumnHandlers() {
        products_table_name.setCellFactory(createEditableCellFactory());
        products_table_desc.setCellFactory(createEditableCellFactory());
        products_table_cost.setCellFactory(createEditableDoubleCellFactory());
        products_table_remove.setCellFactory(CheckBoxTableCell.forTableColumn(products_table_remove));
    }

    public void updateUI() {
        //this.removeProductsButton.setDisable(true);
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
    protected void clearAllTextFields() {
        this.product_price_field.clear();
        this.product_desc_text_area.clear();
        this.product_name_field.clear();
    }

    @FXML
    protected boolean addNewProduct() {
        int product_name_length = product_name_field.getText().length();
        int product_desc_length = product_desc_text_area.getText().length();
        if (product_name_length < 85 && product_name_length > 0 && product_desc_length < 1000 && product_desc_length > 0 && product_price_field.getText().length() != 0) {
            MoneyTrackerFXClient.getInstance().getClientController().getClientPort().createNewProduct(product_name_field.getText(), product_desc_text_area.getText(), new Double(product_price_field.getText()));
            updateUI();
            clearAllTextFields();
            return true;
        } else {
            return false;
        }
    }

    @FXML
    protected synchronized void processDelete() {
        Iterator it = observableProductList.iterator();
        while (it.hasNext()) {
            ProductWrapper item = (ProductWrapper) it.next();
            if (item.to_deleteProperty().getValue().booleanValue() == true) {
                //this.removeProductsButton.setDisable(false);
                MoneyTrackerFXClient.getInstance().getClientController().getClientPort().deleteProductByName(item.getName());
                it.remove();
                System.out.println("item " + item.getName() + " was deleted");
            }
        }
        updateUI();
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

    private void setProductNameColumnCellHandler() {
        products_table_name.setOnEditCommit(new EventHandler<CellEditEvent<ProductWrapper, String>>() {
            @Override
            public void handle(CellEditEvent<ProductWrapper, String> t) {
                if (!(t.getNewValue().equals(t.getOldValue()))) {
                    if (t.getNewValue().length() <= 85) {
                        String old_name = t.getOldValue();
                        ProductWrapper updated = (ProductWrapper) t.getTableView().getItems().get(t.getTablePosition().getRow());
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
        products_table_desc.setOnEditCommit(new EventHandler<CellEditEvent<ProductWrapper, String>>() {
            @Override
            public void handle(CellEditEvent<ProductWrapper, String> t) {
                if (!(t.getNewValue().equals(t.getOldValue()))) {
                    if (t.getNewValue().length() <= 1000) {
                        ProductWrapper updated = (ProductWrapper) t.getTableView().getItems().get(t.getTablePosition().getRow());
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
        products_table_cost.setOnEditCommit(new EventHandler<CellEditEvent<ProductWrapper, Double>>() {
            @Override
            public void handle(CellEditEvent<ProductWrapper, Double> t) {
                if (!(t.getNewValue().equals(t.getOldValue()))) {
                    if (Double.valueOf(t.getNewValue()) >= 0.0) {
                        ProductWrapper updated = (ProductWrapper) t.getTableView().getItems().get(t.getTablePosition().getRow());
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

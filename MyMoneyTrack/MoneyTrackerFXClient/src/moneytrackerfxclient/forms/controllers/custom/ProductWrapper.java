package moneytrackerfxclient.forms.controllers.custom;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import moneytrackerconsoleclient.methods.Product;

public class ProductWrapper extends Product {
    
    final Product _product;
    private BooleanProperty to_delete;
    
    public ProductWrapper(Product product) {
        _product = product;
        this.to_delete = new SimpleBooleanProperty(false);
        this.to_delete.addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                setDeleted(t1);
            }
        });
    }
    
    public BooleanProperty to_deleteProperty() {
        return to_delete;
    }
    
    public void setDeleted(Boolean _deleted) {
        this.to_delete.setValue(_deleted);
    }
    
    public double getCost() {
        return _product.getCost();
    }
    
    public void setCost(double value) {
        _product.setCost(value);
    }
    
    public String getDescription() {
        return _product.getDescription();
    }
    
    public void setDescription(String value) {
        _product.setDescription(value);
    }
    
    public String getName() {
        return _product.getName();
    }
    
    public void setName(String value) {
        _product.setName(value);
    }
    
    public Product getProduct() {
        return _product;
    }
    
    public ProductWrapper getInstance() {
        return this;
    }
    
}

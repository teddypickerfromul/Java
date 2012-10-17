package moneytrackerfxclient.forms.controllers.custom;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import moneytrackerconsoleclient.methods.Product;
import moneytrackerconsoleclient.methods.User;
import moneytrackerconsoleclient.methods.UserOutlay;

public class UserOutlayWrapper extends UserOutlay {

    private final UserOutlay _outlay;
    private BooleanProperty to_delete;
    //TODO:Убрать 
    private String productName;
    private Double overralSum;

    public UserOutlayWrapper(UserOutlay outlay) {
        this._outlay = outlay;
        this.overralSum = this._outlay.getOverral();
        this.productName = this._outlay.getProduct().getName();
        //this.overralSum = this.getProduct().getCost() * this._outlay.getProductsCount();
        this.to_delete = new SimpleBooleanProperty(false);
        this.to_delete.addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                setDeleted(t1);
            }
        });
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void updateOverralSum(int count) {
        this.overralSum = count * this.getProduct().getCost();
    }

    public double getOverralSum() {
        return this.overralSum;
    }

    public void setOverralSum(Double newSum) {
        this.overralSum = newSum;
    }

    public String getDatetime() {
        return this._outlay.getDatetime();
    }

    public void setDatetime(String value) {
        _outlay.setDatetime(value);
    }

    public String _product_nameProperty() {
        /*return this._product_name;*/
        return this.product.getName();
    }

    public Product /*String*/ getProduct() {
        return this._outlay.getProduct();
    }

    public void setProduct(Product value) {
        _outlay.setProduct(value);
    }

    public int getProductsCount() {
        return this._outlay.getProductsCount();
    }

    public void setProductsCount(int value) {
        _outlay.setProductsCount(value);
    }

    public User getUser() {
        return this._outlay.getUser();
    }

    public void setUser(User value) {
        _outlay.setUser(value);
    }

    public BooleanProperty to_deleteProperty() {
        return this.to_delete;
    }

    public void setDeleted(Boolean _deleted) {
        this.to_delete.setValue(_deleted);
    }
}

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

    public UserOutlayWrapper(UserOutlay outlay) {
        _outlay = outlay;
        this.to_delete = new SimpleBooleanProperty(false);
        this.to_delete.addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                setDeleted(t1);
            }
        });
    }

    public String getDatetime() {
        return _outlay.getDatetime();
    }

    public void setDatetime(String value) {
        _outlay.setDatetime(value);
    }

    public Product getProduct() {
        return _outlay.getProduct();
    }

    public void setProduct(Product value) {
        _outlay.setProduct(value);
    }

    public int getProductsCount() {
        return _outlay.getProductsCount();
    }

    public void setProductsCount(int value) {
        _outlay.setProductsCount(value);
    }

    public User getUser() {
        return _outlay.getUser();
    }

    public void setUser(User value) {
        _outlay.setUser(value);
    }

    
    public BooleanProperty to_deleteProperty() {
        return to_delete;
    }

    public void setDeleted(Boolean _deleted) {
        this.to_delete.setValue(_deleted);
    }
}

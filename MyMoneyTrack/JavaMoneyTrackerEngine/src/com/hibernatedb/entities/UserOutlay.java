
//TODO: OnetoOne ???
//TODO: 

package com.hibernatedb.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.cfg.*;

@Entity
@Table(name = "USERS_OUTLAYS",uniqueConstraints = {@UniqueConstraint(columnNames={"USER_OUTLAY_ID"})})
public class UserOutlay {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "USER_OUTLAY_ID", nullable=false)
    private long id;
    
    @Column(name="PRODUCTS_AMOUNT", nullable=false)
    private int products_count;    

    @Column(name="USER_OUTLAY_DATETIME", nullable=false)
    private String datetime;
    
    @OneToOne() @NotNull
    //@Column(name="USER")
    private User user;
    
    @OneToOne() @NotNull
    //@Column(name="PRODUCT")
    private Product product;
    
    @Column(name="OVERRAL_OUTLAY") @NotNull
    private double overral;

    public double getOverral() {
        return overral;
    }

    public void setOverral() {
        this.overral = product.getCost()*this.products_count;
    }


    public void setProducts_count(int products_count) {
        this.products_count = products_count;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public int getProducts_count() {
        return products_count;
    }

    public String getDatetime() {
        return datetime;
    }

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "UserOutlay {" + "id=" + id + ", amount=" + products_count + ", datetime=" + datetime + ", user=" + Long.toString(user.getId()) + ", product=" + Long.toString(product.getId()) + '}';
    }
        
}

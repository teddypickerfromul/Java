//TODO:Перенести валидацию полей в класс Validator (обьявив их в классе AppSettings)
//TODO:поддержка нескольких валют

package com.hibernatedb.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.cfg.*;

@Entity
@Table(name = "PRODUCTS",uniqueConstraints = {@UniqueConstraint(columnNames={"PRODUCT_ID", "PRODUCT_NAME"})})
public class Product {
    	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="PRODUCT_ID")
    private long id;
   
    @Column(name="PRODUCT_NAME", length=85, nullable=false)
    private String name;
  
    @Column(name="PRODUCT_DESCRIPTION", columnDefinition="mediumtext", length=1000)
    private String description;
   
    @Column(name="PRODUCT_COST", nullable=false)
    private double cost;

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("id : "+Long.toString(this.getId()));
        result.append(" name : ").append(this.getName());
        result.append(" desc : "+this.getDescription());
        result.append(" cost : ").append(this.getCost());
        return result.toString();
    }
    
    
    
}

//TODO:Перенести валидацию полей в класс Validator (обьявив их в классе AppSettings)
//TODO:поддержка нескольких валют

package com.hibernatedb.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.cfg.*;

@Entity
@Table(name = "USERS_BUDGETS",uniqueConstraints = {@UniqueConstraint(columnNames={"BUDGET_ID"})})
public class UserBudget {
    	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="BUDGET_ID")
    private long id;
   
    @Column(name="BUDGET_NAME", length=85, nullable=false)
    private String name;
  
    @Column(name="BUDGET_DESCRIPTION", columnDefinition="mediumtext", length=1000)
    private String description;
   
    @Column(name="BUDGET_AMOUNT", nullable=false)
    private double amount;
    
    @OneToOne() @NotNull
    private User owner;

    public double getAmount() {
        return amount;
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setOwner(User owner){
        this.owner = owner;
    }
    
    public User getOwner(){
        return this.owner;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Budget : [");
        result.append(" id : "+Long.toString(this.getId()));
        result.append(" name : ").append(this.getName());
        result.append(" desc : "+this.getDescription());
        result.append(" amount : ").append(this.getAmount());
        result.append(" ]");
        return result.toString();
    }
 
}


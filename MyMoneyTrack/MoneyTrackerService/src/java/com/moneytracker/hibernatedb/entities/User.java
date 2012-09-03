
package com.moneytracker.hibernatedb.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.*;

@Entity
@Table(name = "USERS",uniqueConstraints = {@UniqueConstraint(columnNames={"USER_LOGIN", "USER_EMAIL"})})
public class User {
    
    @Column(name = "USER_LOGIN", length=80, nullable=false)
	private String login;
    
    @Column(name = "USER_PASS", length=80, nullable=false)
	private String password;
    
    @Column(name = "USER_EMAIL", length=80, nullable=false)
	private String email;
		
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "USER_ID", nullable=false)
	private Long id;

	public String getEmail() {
		return this.email;
	}

	public String getLogin() {
		return this.login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setEmail(String string) {
		email = string;
	}

	public void setLogin(String string) {
		login = string;
	}

	public void setPassword(String string) {
		password = string;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long l) {
		id = l;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("id : " + "'").append(Long.toString(this.getId())).append("'");
		result.append(" user : " + "'").append(this.getLogin()).append("'");
		result.append(" password : " + "'").append(this.getPassword()).append("'");
		result.append(" email : " + "'").append(this.getEmail()).append("'");
		return result.toString();
	}
}

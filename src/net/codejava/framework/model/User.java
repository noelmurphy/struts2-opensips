package net.codejava.framework.model;

import java.io.Serializable;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -4656759219348212715L;

  
  private Long id;
  

  private String username;
  
  
  private String email;
  

  private String password;
  

  private String salt;


  private String roleName;

  

  @Id
  @GeneratedValue
  @Column(name="id")
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

@Column(name="username")
public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

@Column(name="email")
public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

@Column(name="password")
public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Column(name="salt")
public String getSalt() {
	return salt;
}

public void setSalt(String salt) {
	this.salt = salt;
}

@Column(name="rolename")
public String getRoleName() {
    return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}

}
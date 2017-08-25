package net.codejava.framework.model;

import java.io.Serializable;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="userrole")
public class UserRole implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -4656759219348212715L;

  
  private Integer id;
  private String roleName;
  private String email;


  

  @Id
  @GeneratedValue
  @Column(name="id")
public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

@Column(name="roleName")
public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName=roleName;
}

@Column(name="email")
public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}
}
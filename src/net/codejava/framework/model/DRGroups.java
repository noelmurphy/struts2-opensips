package net.codejava.framework.model;

/*
+-----------+--------------+------+-----+-------------------+-----------------------------+
| Field     | Type         | Null | Key | Default           | Extra                       |
+-----------+--------------+------+-----+-------------------+-----------------------------+
| uuid      | varchar(64)  | NO   | MUL |                   |                             |
| username  | varchar(100) | NO   |     | 0                 |                             |
| domain    | varchar(128) | NO   |     |                   |                             |
| attribute | varchar(32)  | NO   |     |                   |                             |
| value     | varchar(64)  | NO   |     |                   |                             |
| type      | int(11)      | NO   |     | 0                 |                             |
| modified  | timestamp    | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
+-----------+--------------+------+-----+-------------------+-----------------------------+
 */

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="new_dr_groups")
public class DRGroups {
	
	private Long uuid;
	private String username;
	private String domain;
	private String attribute;
	private String value;
	private int type;
	private Timestamp modified;
	
	@Id
	@GeneratedValue
	@Column(name="uuid")
	public Long getId() {
		return uuid;
	}
	public void setId(Long id) {
		this.uuid = id;
	}
	
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="domain")
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Column(name="attribute")
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	@Column(name="value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Column(name="type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Column(name="modified")
	public Timestamp getModified() {
		return modified;
	}
	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

}

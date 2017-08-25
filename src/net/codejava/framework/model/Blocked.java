package net.codejava.framework.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
+---------------+------------------+------+-----+---------------------+----------------+
| Field         | Type             | Null | Key | Default             | Extra          |
+---------------+------------------+------+-----+---------------------+----------------+
| id            | int(10) unsigned | NO   | PRI | NULL                | auto_increment |
| username      | char(64)         | NO   | MUL |                     |                |
| domain        | char(64)         | NO   |     |                     |                |
| grp           | char(64)         | NO   |     |                     |                |
| last_modified | datetime         | NO   |     | 1900-01-01 00:00:01 |                |
+---------------+------------------+------+-----+---------------------+----------------+
*/

@Entity
@Table(name="grp")
public class Blocked {

	private Long id;
	private String username;
	private String domain;
	private String grp;
	private Timestamp last_modified;
	
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
	
	@Column(name="domain")
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Column(name="grp")
	public String getGrp() {
		return grp;
	}
	public void setGrp(String grp) {
		this.grp = grp;
	}
	
	@Column(name="last_modified")
	public Timestamp getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(Timestamp last_modified) {
		this.last_modified = last_modified;
	}
	
}

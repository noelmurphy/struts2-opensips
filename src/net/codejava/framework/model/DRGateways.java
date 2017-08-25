package net.codejava.framework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dr_gateways")
public class DRGateways {
	
	/*
	+-------------+------------------+------+-----+---------+----------------+
	| Field       | Type             | Null | Key | Default | Extra          |
	+-------------+------------------+------+-----+---------+----------------+
	| gwid        | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
	| type        | int(11) unsigned | NO   |     | 0       |                |
	| address     | char(128)        | NO   |     | NULL    |                |
	| strip       | int(11) unsigned | NO   |     | 0       |                |
	| pri_prefix  | char(16)         | YES  |     | NULL    |                |
	| attrs       | char(255)        | YES  |     | NULL    |                |
	| description | char(128)        | NO   |     |         |                |
	+-------------+------------------+------+-----+---------+----------------+
	*/

	private Long gwid;
	private int type;
	private String address;
	private int strip;
	private String pri_prefix;
	private String attrs;
	private String description;
	
	@Id
	@GeneratedValue
	@Column(name="gwid")
	public Long getGwid() {
		return gwid;
	}
	public void setGwid(Long gwid) {
		this.gwid = gwid;
	}
	
	@Column(name="type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="strip")
	public int getStrip() {
		return strip;
	}
	public void setStrip(int strip) {
		this.strip = strip;
	}
	
	@Column(name="pri_prefix")
	public String getPri_prefix() {
		return pri_prefix;
	}
	public void setPri_prefix(String pri_prefix) {
		this.pri_prefix = pri_prefix;
	}
	
	@Column(name="attrs")
	public String getAttrs() {
		return attrs;
	}
	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}

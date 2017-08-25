package net.codejava.framework.model;

/*
 * +--------------+--------------+------+-----+---------+----------------+
| Field        | Type         | Null | Key | Default | Extra          |
+--------------+--------------+------+-----+---------+----------------+
| id           | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| from_pattern | varchar(255) | YES  |     | NULL    |                |
| proto        | varchar(255) | YES  |     | NULL    |                |
| src_ip       | varchar(255) | YES  |     | NULL    |                |
| tag          | varchar(255) | YES  |     | NULL    |                |
+--------------+--------------+------+-----+---------+----------------+
 */

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="trusted")
public class Trusted {
	
	private Long id;
	private String src_ip;
	private String proto;
	private String from_pattern;
	private String tag;
	 
	@Id
	@GeneratedValue
	@Column(name="id")    
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="src_ip")
	public String getSrc_ip() {
		return src_ip;
	}

	public void setSrc_ip(String src_ip) {
		this.src_ip = src_ip;
	}

	@Column(name="proto")
	public String getProto() {
		return proto;
	}

	public void setProto(String proto) {
		this.proto = proto;
	}

	@Column(name="from_pattern")
	public void setFrom_pattern(String from_pattern) {
		this.from_pattern = from_pattern;
	}

	public String getFrom_pattern() {
		return from_pattern;
	}
	
	@Column(name="tag")
	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}
	
}

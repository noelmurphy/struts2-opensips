package net.codejava.framework.model;

/*
 * +-----------+------------------+------+-----+---------+----------------+
| Field     | Type             | Null | Key | Default | Extra          |
+-----------+------------------+------+-----+---------+----------------+
| id        | String(10) unsigned | NO   | PRI | NULL    | auto_increment |
| dpid      | String(11)          | NO   |     | NULL    |                |
| pr        | String(11)          | NO   |     | NULL    |                |
| match_op  | String(11)          | NO   |     | NULL    |                |
| match_exp | char(64)         | NO   |     | NULL    |                |
| match_len | String(11)          | NO   |     | NULL    |                |
| subst_exp | char(64)         | NO   |     | NULL    |                |
| repl_exp  | char(32)         | NO   |     | NULL    |                |
| attrs     | char(32)         | NO   |     | NULL    |                |
+-----------+------------------+------+-----+---------+----------------+
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="dialplan")
public class Dialplan {
	
	private Long id;
	private String dpid;
	private String pr;
	private String match_op;
	private String match_exp;
	private String match_len;
	private String subst_exp;
	private String repl_exp;
	private String attrs;
	 
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
		
	@Column(name="dpid")
	public String getDpid() {
		return dpid;
	}

	public void setDpid(String dpid) {
		this.dpid = dpid;
	}

	@Column(name="pr")
	public String getPr() {
		return pr;
	}

	public void setPr(String pr) {
		this.pr = pr;
	}

	@Column(name="match_op")
	public String getMatch_op() {
		return match_op;
	}

	public void setMatch_op(String match_op) {
		this.match_op = match_op;
	}

	@Column(name="match_exp")
	public String getMatch_exp() {
		return match_exp;
	}

	public void setMatch_exp(String match_exp) {
		this.match_exp = match_exp;
	}

	@Column(name="match_len")
	public String getMatch_len() {
		return match_len;
	}

	public void setMatch_len(String match_len) {
		this.match_len = match_len;
	}

	@Column(name="subst_exp")
	public String getSubst_exp() {
		return subst_exp;
	}

	public void setSubst_exp(String subst_exp) {
		this.subst_exp = subst_exp;
	}

	@Column(name="repl_exp")
	public String getRepl_exp() {
		return repl_exp;
	}

	public void setRepl_exp(String repl_exp) {
		this.repl_exp = repl_exp;
	}

	@Column(name="attrs")
	public String getAttrs() {
		return attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}
	
}

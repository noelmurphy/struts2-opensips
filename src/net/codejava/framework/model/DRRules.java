package net.codejava.framework.model;

/*
+-------------+------------------+------+-----+---------+----------------+
| Field       | Type             | Null | Key | Default | Extra          |
+-------------+------------------+------+-----+---------+----------------+
| ruleid      | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
| groupid     | char(255)        | NO   |     | NULL    |                |
| prefix      | char(64)         | NO   |     | NULL    |                |
| timerec     | char(255)        | NO   |     | NULL    |                |
| priority    | int(11)          | NO   |     | 0       |                |
| routeid     | int(11) unsigned | NO   |     | 0       |                |
| gwlist      | char(255)        | NO   |     | NULL    |                |
| description | char(128)        | NO   |     |         |                |
+-------------+------------------+------+-----+---------+----------------+
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dr_rules")
public class DRRules {
	
	private Long ruleid;
	private String groupid;
	private String prefix;
	private String timerec;
	private int priority;
	private int routeid;
	private String gwlist;
	private String description;
	

	@Id
	@GeneratedValue
	@Column(name="ruleid")
	public Long getRuleid() {
		return ruleid;
	}
	public void setRuleid(Long id) {
		this.ruleid = id;
	}
	
	@Column(name="groupid")
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	@Column(name="prefix")
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Column(name="timerec")
	public String getTimerec() {
		return timerec;
	}
	public void setTimerec(String timerec) {
		this.timerec = timerec;
	}
	
	@Column(name="priority")
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Column(name="routeid")
	public int getRouteid() {
		return routeid;
	}
	public void setRouteid(int routeid) {
		this.routeid = routeid;
	}
	
	@Column(name="gwlist")
	public String getGwlist() {
		return gwlist;
	}
	public void setGwlist(String gwlist) {
		this.gwlist = gwlist;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

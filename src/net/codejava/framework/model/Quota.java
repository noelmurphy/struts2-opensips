package net.codejava.framework.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/*
+-------------+---------------------+------+-----+-------------------+-----------------------------+
| Field       | Type                | Null | Key | Default           | Extra                       |
+-------------+---------------------+------+-----+-------------------+-----------------------------+
| id          | int(10) unsigned    | NO   | PRI | NULL              | auto_increment              |
| reseller_id | int(10) unsigned    | NO   | MUL | NULL              |                             |
| datasource  | varchar(50)         | NO   | MUL | NULL              |                             |
| account     | varchar(128)        | NO   |     |                   |                             |
| domain      | varchar(64)         | YES  |     | NULL              |                             |
| quota       | int(11) unsigned    | NO   |     | NULL              |                             |
| blocked     | enum('0','1')       | NO   |     | 0                 |                             |
| notified    | datetime            | NO   |     | NULL              |                             |
| calls       | int(10) unsigned    | NO   |     | NULL              |                             |
| duration    | bigint(20) unsigned | NO   |     | NULL              |                             |
| cost        | decimal(10,4)       | NO   |     | NULL              |                             |
| cost_today  | decimal(10,4)       | NO   |     | NULL              |                             |
| traffic     | varchar(50)         | NO   |     | NULL              |                             |
| change_date | timestamp           | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
+-------------+---------------------+------+-----+-------------------+-----------------------------+
+-------------+---------------------+------+-----+-------------------+-----------------------------+
| Field       | Type                | Null | Key | Default           | Extra                       |
+-------------+---------------------+------+-----+-------------------+-----------------------------+
| id          | int(10) unsigned    | NO   | PRI | NULL              | auto_increment              |
| reseller_id | int(10) unsigned    | NO   | MUL | NULL              |                             |
| datasource  | varchar(50)         | NO   | MUL | NULL              |                             |
| account     | varchar(128)        | NO   |     |                   |                             |
| domain      | varchar(64)         | YES  |     | NULL              |                             |
| quota       | int(11) unsigned    | NO   |     | NULL              |                             |
| 80percent   | enum('0','1')       | NO   |     | 0                 |                             |
| blocked     | enum('0','1')       | NO   |     | 0                 |                             |
| notified    | datetime            | NO   |     | NULL              |                             |
| calls       | int(10) unsigned    | NO   |     | NULL              |                             |
| duration    | bigint(20) unsigned | NO   |     | NULL              |                             |
| cost        | decimal(10,4)       | NO   |     | NULL              |                             |
| traffic     | varchar(50)         | NO   |     | NULL              |                             |
| change_date | timestamp           | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
+-------------+---------------------+------+-----+-------------------+-----------------------------+
*/

@Entity
@Table(name="quota_usage")
public class Quota {
	private Long id;
	private int reseller_id;
	private String datasource;
	private String account;
	private String domain;
	private int quota;
	private String percent;
	private String blocked;
	private Timestamp notified;
	private int calls;
	private int duration;
	private float cost;
	private String traffic;
	private Timestamp change_date;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="reseller_id")
	public int getReseller_id() {
		return reseller_id;
	}
	public void setReseller_id(int reseller_id) {
		this.reseller_id = reseller_id;
	}
	
	@Column(name="datasource")
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	
	@Column(name="account")
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name="domain")
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Column(name="quota")
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
	}
	
	@Column(name="80percent")
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	
	@Column(name="blocked")
	public String getBlocked() {
		return blocked;
	}
	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}
	
	@Column(name="notified")
	public Timestamp getNotified() {
		return notified;
	}
	public void setNotified(Timestamp notified) {
		this.notified = notified;
	}
	
	@Column(name="calls")
	public int getCalls() {
		return calls;
	}
	public void setCalls(int calls) {
		this.calls = calls;
	}
	
	@Column(name="duration")
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@Column(name="cost")
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}

	@Column(name="traffic")
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	
	@Column(name="change_date")
	public Timestamp getChange_date() {
		return change_date;
	}
	public void setChange_date(Timestamp change_date) {
		this.change_date = change_date;
	}
	
}

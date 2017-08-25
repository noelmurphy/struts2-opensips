package net.codejava.framework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
+-------------------+----------------------+------+-----+---------+----------------+
| Field             | Type                 | Null | Key | Default | Extra          |
+-------------------+----------------------+------+-----+---------+----------------+
| id                | bigint(20) unsigned  | NO   | PRI | NULL    | auto_increment |
| reseller_id       | int(10) unsigned     | NO   | MUL | NULL    |                |
| gateway           | varchar(15)          | NO   | MUL |         |                |
| domain            | varchar(64)          | NO   |     |         |                |
| subscriber        | varchar(128)         | NO   | MUL |         |                |
| profile_name1     | varchar(25)          | NO   |     | NULL    |                |
| profile_name1_alt | varchar(25)          | NO   |     | NULL    |                |
| profile_name2     | varchar(25)          | NO   |     | NULL    |                |
| profile_name2_alt | varchar(25)          | NO   |     | NULL    |                |
| timezone          | varchar(128)         | NO   |     |         |                |
| increment         | smallint(5) unsigned | NO   |     | NULL    |                |
| min_duration      | smallint(5) unsigned | NO   |     | NULL    |                |
+-------------------+----------------------+------+-----+---------+----------------+*/


@Entity
@Table(name="billing_customers")
public class BillingCustomers {

	private Long id;
	private String reseller_id;
	private String gateway;
	private String domain;
	private String subscriber;
	private String profile_name1;
	private String profile_name1_alt;
	private String profile_name2;
	private String profile_name2_alt;
	private String timezone;
	private String increment;
	private String min_duration;
	
	 
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
	public String getReseller_id() {
		return reseller_id;
	}
	public void setReseller_id(String reseller_id) {
		this.reseller_id = reseller_id;
	}
	
	//*fetched from trusted src_ip
	@Column(name="gateway")   
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	
	@Column(name="domain")   
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Column(name="subscriber")  
	public String getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}
	
	@Column(name="profile_name1")  
	public String getProfile_name1() {
		return profile_name1;
	}
	public void setProfile_name1(String profile_name1) {
		this.profile_name1 = profile_name1;
	}
	
	@Column(name="profile_name1_alt")  
	public String getProfile_name1_alt() {
		return profile_name1_alt;
	}
	public void setProfile_name1_alt(String profile_name1_alt) {
		this.profile_name1_alt = profile_name1_alt;
	}
	
	@Column(name="profile_name2")  
	public String getProfile_name2() {
		return profile_name2;
	}
	public void setProfile_name2(String profile_name2) {
		this.profile_name2 = profile_name2;
	}
	
	@Column(name="profile_name2_alt")  
	public String getProfile_name2_alt() {
		return profile_name2_alt;
	}
	public void setProfile_name2_alt(String profile_name2_alt) {
		this.profile_name2_alt = profile_name2_alt;
	}
	
	@Column(name="timezone")
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	@Column(name="increment")  
	public String getIncrement() {
		return increment;
	}
	public void setIncrement(String increment) {
		this.increment = increment;
	}
	
	@Column(name="min_duration")  
	public String getMin_duration() {
		return min_duration;
	}
	public void setMin_duration(String min_duration) {
		this.min_duration = min_duration;
	}
}

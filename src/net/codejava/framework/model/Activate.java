package net.codejava.framework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activate")
public class Activate {
	private Long id;
	private String reload;
	private String value;
	 
	
	 
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
	
	@Column(name="reload")
	public String getReload()
	{
		return reload;
	}
	
	public void setReload(String reload)
	{
		this.reload = reload;
	}
	
	@Column(name="value")
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
}

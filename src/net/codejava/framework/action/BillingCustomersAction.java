package net.codejava.framework.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;

import org.hibernate.exception.ConstraintViolationException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.codejava.framework.dao.BillingCustomersDAO;
import net.codejava.framework.dao.BillingCustomersDAOImpl;
import net.codejava.framework.dao.Constants;
import net.codejava.framework.dao.TrustedDAO;
import net.codejava.framework.dao.TrustedDAOImpl;
import net.codejava.framework.dao.UserDAOImpl;
import net.codejava.framework.model.BillingCustomers;
import net.codejava.framework.model.Trusted;
import net.codejava.framework.model.User;

public class BillingCustomersAction extends ActionSupport implements ModelDriven<BillingCustomers> , ServletContextAware, Constants  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BillingCustomersDAO customersDAO = new BillingCustomersDAOImpl();
	private ServletContext context;
	private BillingCustomers customer = new BillingCustomers();

	@SkipValidation
	public String saveOrUpdate() throws ConstraintViolationException
	{	

		HttpServletRequest request = ServletActionContext.getRequest();

        customer.setReseller_id("0");
        /** 
         * fetch trusted src_ip 
         */
        customer.setGateway((String)request.getAttribute("src_ip"));
        
        
        customer.setDomain(" ");
        customer.setSubscriber(" ");
        customer.setProfile_name1(" ");
        customer.setProfile_name1_alt(" ");
        customer.setProfile_name2("welltel_weekend");
        customer.setProfile_name2_alt(" ");
        customer.setTimezone("Europe/Dublin");
        customer.setIncrement("0");
        customer.setMin_duration("0");
		
		customersDAO.saveOrUpdateBillingCustomer(customer);

		return SUCCESS;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}
	
	public ServletContext getServletContext()
	{
		return context;
	}
	@SkipValidation
	public String execute() {
	
		return SUCCESS;
	}

	@Override
	public BillingCustomers getModel() {
		return customer;
	}
	
	public static void main(String[] args)
	{
		BillingCustomers customer = new BillingCustomers();
		BillingCustomersAction x = new BillingCustomersAction();
		x.saveOrUpdate();
	}

}

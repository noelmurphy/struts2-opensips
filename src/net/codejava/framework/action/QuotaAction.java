package net.codejava.framework.action;

import java.io.File;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.codejava.framework.dao.ActivateDAOImpl;
import net.codejava.framework.dao.BlockedDAO;
import net.codejava.framework.dao.BlockedDAOImpl;
import net.codejava.framework.dao.Constants;
import net.codejava.framework.dao.QuotaDAO;
import net.codejava.framework.dao.QuotaDAOImpl;
import net.codejava.framework.model.Activate;
import net.codejava.framework.model.Quota;
import net.codejava.framework.opensips.Opensipsctl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class QuotaAction extends ActionSupport implements ModelDriven<Quota> , ServletContextAware, Constants {

	private static final long serialVersionUID = 1L;

	private final static Logger cat = LoggerFactory.getLogger(QuotaAction.class);
    
    protected Logger getLogger (){
    	return cat;
    }
    
    private Quota quota = new Quota();
	private List<Quota> listQuota = new ArrayList<Quota>();
	private QuotaDAO quotaDAO = new QuotaDAOImpl();
	private ServletContext context;
	private ActivateDAOImpl activateDAO = new ActivateDAOImpl();
	private List<Activate> listActivate = new ArrayList<Activate>();
	private Opensipsctl opensipsctl = new Opensipsctl();
	private String url = "";

	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	/*
	 * Universal reload command
	 */
	@SkipValidation
	public String doReload() {		
		String contextPath = context.getRealPath(File.separator);
		String configfile = contextPath + "/" + CMDOPENSIPS_FILE_NAME;
		opensipsctl.allReload(configfile);
		setUrl("/WEB-INF/views/QuotaList.jsp");
		list();
		return SUCCESS;
	}
	@SkipValidation
	public List<Activate> getListActivate() {
		return listActivate;
	}
	
	public void setListActivate(List<Activate> listActivate) {
		this.listActivate = listActivate;
	}
	
	
	@Override
	public void setServletContext(ServletContext context) {
		this.setContext(context);
		
	}

	@Override
	public Quota getModel() {
		
		return quota;
	}

	@SkipValidation
	public String list() {
		
		listQuota = quotaDAO.listQuota();
		listActivate = activateDAO.listActivate();
		return SUCCESS;
	}

	@SkipValidation
	public String execute() {
		listActivate = activateDAO.listActivate();

		listQuota = quotaDAO.listQuota();
		return SUCCESS;
	}
	
	//save customer
	public String addQuota() {
			
		//save it
		quotaDAO.addQuota(quota);
		 
		//reload the blocked list
		listQuota = null;
		listQuota = quotaDAO.listQuota();
			
		return "success";
		
	}
	
	/**
	 * To save or update Quota.
	 * @return String
	 */
	/* */

	public String saveOrUpdate()
	{	
		
		quotaDAO.saveOrUpdateQuota(quota);
		activateDAO.setActivate("1");

		//reload the blocked list
		listQuota = null;
		listQuota = quotaDAO.listQuota();

		return SUCCESS;
	}
	
	@SkipValidation
	public String edit()
	{
		listActivate = activateDAO.listActivate();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		quota = quotaDAO.listQuotaById(Long.parseLong(request.getParameter("id")));

		return SUCCESS;
	}
	
	@SkipValidation
	public String delete()
	{
		listActivate = activateDAO.listActivate();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		quotaDAO.deleteQuota(Long.parseLong(request.getParameter("id")));
		
		//reload the blocked list
		listQuota = null;
		listQuota = quotaDAO.listQuota();
		
		return SUCCESS;
	}
	
	public Quota getQuota() {
		return quota;
	}

	public void setQuota(Quota quota) {
		this.quota = quota;
	}

	public List<Quota> getListQuota() {
		return listQuota;
	}

	public void setListQuota(List<Quota> listQuota) {
		this.listQuota = listQuota;
	}

	public QuotaDAO getQuotaDAO() {
		return quotaDAO;
	}

	public void setQuotaDAO(QuotaDAO quotaDAO) {
		this.quotaDAO = quotaDAO;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

}

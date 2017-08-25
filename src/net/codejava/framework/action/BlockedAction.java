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
import net.codejava.framework.model.Activate;
import net.codejava.framework.model.Blocked;
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

public class BlockedAction extends ActionSupport implements ModelDriven<Blocked> , ServletContextAware, Constants {

	private static final long serialVersionUID = 1L;

	private final static Logger cat = LoggerFactory.getLogger(BlockedAction.class);
    
    protected Logger getLogger (){
    	return cat;
    }
    
    private Blocked blocked = new Blocked();
	private List<Blocked> listBlocked = new ArrayList<Blocked>();
	private BlockedDAO blockedDAO = new BlockedDAOImpl();
	private ServletContext context;
	private List<Activate> listActivate = new ArrayList<Activate>();
	private Opensipsctl opensipsctl = new Opensipsctl();
	private ActivateDAOImpl activateDAO = new ActivateDAOImpl();
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
		setUrl("/WEB-INF/views/BlockedList.jsp");
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
		this.context = context;
	}

	@Override
	public Blocked getModel() {
		return blocked;
	}

	@SkipValidation
	public String list() {
		
		listBlocked = blockedDAO.listBlocked();
		listActivate = activateDAO.listActivate();
		return SUCCESS;
	}

	@SkipValidation
	public String execute() {
		listActivate = activateDAO.listActivate();

		listBlocked = blockedDAO.listBlocked();
		return SUCCESS;
	}
	
	//save customer
	@SkipValidation
	public String addBlocked() {
			
		//save it
		blockedDAO.addBlocked(blocked);
		 
		//reload the blocked list
		listBlocked = null;
		listBlocked = blockedDAO.listBlocked();
			
		return SUCCESS;
		
	}
	
	//edit customer
	@SkipValidation
	public String updateBlocked() {
		
		cat.info("UpdateBlocked id parameter value is : " + blocked.getId());
		cat.info("UpdateBlocked last_modified parameter value is : " + blocked.getLast_modified());
		cat.info("UpdateBlocked domain parameter value is : " + blocked.getDomain());
		cat.info("UpdateBlocked group parameter value is : " + blocked.getGrp());
		cat.info("UpdateBlocked username parameter value is : " + blocked.getUsername());
		
		//save it
		blockedDAO.updateBlocked(blocked);
		 
		//reload the blocked list
		listBlocked = null;
		listBlocked = blockedDAO.listBlocked();
			
		return SUCCESS;
		
	}
	
	/**
	 * To save or update blocked.
	 * @return String
	 */
	/* */
	@SkipValidation
	public String saveOrUpdate()
	{
		
		Subject currentUser = SecurityUtils.getSubject();
		blockedDAO.saveOrUpdateBlocked(blocked);
		activateDAO.setActivate("1");
		//reload the blocked list
		listBlocked = null;
		listBlocked = blockedDAO.listBlocked();

		return SUCCESS;
	}
	
	@SkipValidation
	public String edit()
	{
		listActivate = activateDAO.listActivate();

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		blocked = blockedDAO.listBlockedById(Long.parseLong(request.getParameter("id")));

		return INPUT;
	}
	
	@SkipValidation
	public String delete()
	{
		listActivate = activateDAO.listActivate();

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		blockedDAO.deleteBlocked(Long.parseLong(request.getParameter("id")));
		
		//reload the blocked list
		listBlocked = null;
		listBlocked = blockedDAO.listBlocked();
		
		return SUCCESS;
	}
	
	@SkipValidation
	public String search() {
		
		cat.info("search username parameter value is : " + blocked.getUsername());
		listActivate = activateDAO.listActivate();
		listBlocked = blockedDAO.searchBlocked(blocked);
		return SUCCESS;
	}
	
	public Blocked getBlocked() {
		return blocked;
	}

	public void setBlocked(Blocked blocked) {
		this.blocked = blocked;
	}

	public List<Blocked> getListBlocked() {
		return listBlocked;
	}

	public void setListBlocked(List<Blocked> listBlocked) {
		this.listBlocked = listBlocked;
	}

	public BlockedDAO getBlockedDAO() {
		return blockedDAO;
	}

	public void setBlockedDAO(BlockedDAO blockedDAO) {
		this.blockedDAO = blockedDAO;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

}

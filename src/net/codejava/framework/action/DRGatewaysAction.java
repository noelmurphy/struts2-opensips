package net.codejava.framework.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.codejava.framework.dao.ActivateDAOImpl;
import net.codejava.framework.dao.Config;
import net.codejava.framework.dao.Constants;
import net.codejava.framework.dao.DRGatewaysDAO;
import net.codejava.framework.dao.DRGatewaysDAOImpl;
import net.codejava.framework.model.Activate;
import net.codejava.framework.model.DRGateways;
import net.codejava.framework.opensips.Opensipsctl;

import org.apache.commons.configuration.ConfigurationException;
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

public class DRGatewaysAction extends ActionSupport implements ModelDriven<DRGateways> , ServletContextAware, Constants {

	private static final long serialVersionUID = 1L;

	private final static Logger cat = LoggerFactory.getLogger(DRGatewaysAction.class);
    
    protected Logger getLogger (){
    	return cat;
    }
    
	private DRGateways drgateways = new DRGateways();
	private List<DRGateways> listDRGateways = new ArrayList<DRGateways>();
	private DRGatewaysDAO drgatewaysDAO = new DRGatewaysDAOImpl();
	private ServletContext context;
	private ActivateDAOImpl activateDAO = new ActivateDAOImpl();
	private List<Activate> listActivate = new ArrayList<Activate>();
	private Opensipsctl opensipsctl = new Opensipsctl();
	
	
	/*
	 * Universal reload command
	 */
	private String url = "";

	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	@SkipValidation
	public String doReload() {		
		String contextPath = context.getRealPath(File.separator);
		String configfile = contextPath + "/" + CMDOPENSIPS_FILE_NAME;
		opensipsctl.allReload(configfile);
		setUrl("/WEB-INF/views/DRGatewaysList.jsp");
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
	
	
	@SkipValidation
	public void setServletContext(ServletContext context) {
		
		this.context = context;
		
	}
	
	@SkipValidation
	public DRGateways getModel() {
		
		return drgateways;
	}

	
	public DRGateways getDrgateways() {
		return drgateways;
	}

	@SkipValidation
	public void setDrgateways(DRGateways drgateways) {
		this.drgateways = drgateways;
	}
	
	
	public List<DRGateways> getListDRGateways() {
		return listDRGateways;
	}

	@SkipValidation
	public void setListDRGateways(List<DRGateways> listDRGateways) {
		this.listDRGateways = listDRGateways;
	}

	
	public String list() {
		listActivate = activateDAO.listActivate();
		listDRGateways = drgatewaysDAO.listDRGateways();
		return SUCCESS;
	}

	@SkipValidation
	public String execute() {
		
		listActivate = activateDAO.listActivate();

		listDRGateways = drgatewaysDAO.listDRGateways();
		return SUCCESS;
	}
	
	// save customer
	@SkipValidation
	public String addDRGateways() {

		// save it
		drgatewaysDAO.addDRGateways(drgateways);

		// reload the dr_gateways list
		listDRGateways = null;
		listDRGateways = drgatewaysDAO.listDRGateways();

		return "success";

	}

	@SkipValidation
	public String saveOrUpdate()
	{	
		//Subject currentUser = SecurityUtils.getSubject();
		drgatewaysDAO.saveOrUpdateDRGateways(drgateways);

		activateDAO.setActivate("1");

		//reload the drgateways
		listDRGateways = null;
		listDRGateways = drgatewaysDAO.listDRGateways();

		return SUCCESS;
	}

	@SkipValidation
	public String delete()
	{
		listActivate = activateDAO.listActivate();

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		drgatewaysDAO.deleteDRGateways(Long.parseLong(request.getParameter("gwid")));
		
		//reload the trusted list
		listDRGateways = null;
		listDRGateways = drgatewaysDAO.listDRGateways();
		
		return SUCCESS;
	}
	
	@SkipValidation
	public String edit()
	{
		listActivate = activateDAO.listActivate();

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		drgateways = drgatewaysDAO.listDRGatewaysById(Long.parseLong(request.getParameter("gwid")));

		return SUCCESS;
	}
	
	
	public DRGatewaysDAO getDrgatewaysDAO() {
		return drgatewaysDAO;
	}

	public void setDrgatewaysDAO(DRGatewaysDAO drgatewaysDAO) {
		this.drgatewaysDAO = drgatewaysDAO;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	private String regex;
	private Pattern pattern;
	private Matcher matcher;
	
	public void validate() {
		listActivate = activateDAO.listActivate();

		// Get the root path from the context
		String contextPath = context.getRealPath(File.separator);
		String configfile = contextPath + CONFIG_FILE_NAME;
		cat.info("configfile path name value is : " + configfile);

		// Initialise parameters from the config.ini file
		Config config = Config.getInstance();
		try {
			config.setConfigurationFile(configfile);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Check IPV4 address
		regex = Config.getConfigRegexIp();
		cat.info("Config regex paramter value is : " + regex);
		pattern = Pattern.compile(regex);

		
		// Check Protocol
		regex = Config.getConfigRegexProt();
		cat.info("Config regex paramter value is : " + regex);
		pattern = Pattern.compile(regex);
	}
}

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
import net.codejava.framework.dao.DRRulesDAO;
import net.codejava.framework.dao.DRRulesDAOImpl;
import net.codejava.framework.model.Activate;
import net.codejava.framework.model.DRRules;
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


public class DRRulesAction extends ActionSupport implements ModelDriven<DRRules> , ServletContextAware, Constants {
	private static final long serialVersionUID = 1L;
	
	private final static Logger cat = LoggerFactory.getLogger(DRRulesAction.class);
    
    protected Logger getLogger (){
    	return cat;
    }
    
	private DRRules drrules = new DRRules();
	private List<DRRules> listDRRules = new ArrayList<DRRules>();
	private DRRulesDAO drrulesDAO = new DRRulesDAOImpl();
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
		setUrl("/WEB-INF/views/DRRulesList.jsp");
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
	
	
	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	@SkipValidation
	public void setDRRulesDAO(DRRulesDAO drrulesDAO) {
		
		this.drrulesDAO = drrulesDAO;
	}
		
	@SkipValidation
	public DRRules getModel() {
		
		return drrules;
	}
	
	@SkipValidation
	public List<DRRules> getListDRRules() {
		return listDRRules;
	}

	@SkipValidation
	public void setListDRRules(List<DRRules> listDRRules) {
		this.listDRRules = listDRRules;
	}
	
	@SkipValidation
	public String list() {
		listActivate = activateDAO.listActivate();

		listDRRules = drrulesDAO.listDRRules();
		return SUCCESS;
	}

	@SkipValidation
	public String execute() {
		listActivate = activateDAO.listActivate();

		listDRRules = drrulesDAO.listDRRules();
		return SUCCESS;
	}
	
	public DRRules getDrrules() {
		
		return drrules;
	}
	
	public void setDrrules(DRRules drrules) {
		
		this.drrules = drrules;		
	}

	//save customer
	public String addDRRules() {
		
		//save it
		drrulesDAO.addDRRules(drrules);
	 
		//reload the trusted list
		listDRRules = null;
		listDRRules = drrulesDAO.listDRRules();
		
		return "success";
	
	}
	
	/**
	 * To save or update user.
	 * @return String
	 */
	/* */

	public String saveOrUpdate()
	{	
		Subject currentUser = SecurityUtils.getSubject();

		drrulesDAO.saveOrUpdateDRRules(drrules);
		activateDAO.setActivate("1");

		//reload the trusted list
		listDRRules = null;
		listDRRules = drrulesDAO.listDRRules();
		/*
		if (drrules.getTag() == null)
		{
			trusted.setTag(trusted.getSrc_ip().replace(".", ""));
		}
		*/
		return SUCCESS;
	}
	/* */
	
	/**
	 * To edit a single dr_rule by ruleid.
	 * @return String
	 */
	/* */
	@SkipValidation
	public String edit()
	{
		listActivate = activateDAO.listActivate();

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		drrules = drrulesDAO.listDRRulesById(Long.parseLong(request.getParameter("ruleid")));

		return SUCCESS;
	}
	/* */
	
	/**
	 * To delete a single dr_rules by Id.
	 * @return String
	 */
	/* */
	@SkipValidation
	public String delete()
	{
		listActivate = activateDAO.listActivate();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		drrulesDAO.deleteDRRules(Long.parseLong(request.getParameter("ruleid")));
		
		//reload the trusted list
		listDRRules = null;
		listDRRules = drrulesDAO.listDRRules();
		
		return SUCCESS;
	}

	
	
	@Override
	public void setServletContext(ServletContext context) {
		
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
		/*
		matcher = pattern.matcher(trusted.getSrc_ip());		
		if (!matcher.matches()) {
			addFieldError("src_ip", "IP must be valid.");
		}
		 */
		// Check Protocol
		regex = Config.getConfigRegexProt();
		cat.info("Config regex paramter value is : " + regex);
		pattern = Pattern.compile(regex);
		/*
		matcher = pattern.matcher(trusted.getProto());		
		if (!matcher.matches()) {
			addFieldError("proto", "Protocol must be valid.");
		}
		*/
		// Check Tag
		/*
		String tagsetter = trusted.getSrc_ip().replace(".", "");
		if (!trusted.getTag().equals(tagsetter) && !trusted.getTag().equals("")) {
			addFieldError("tag",
					"Enter valid tag or leave me blank for auto-generate");
		} else if (trusted.getTag().equals("")) {
			trusted.setTag(tagsetter);
		}
		*/
	}
}

package net.codejava.framework.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.codejava.framework.dao.ActivateDAOImpl;
import net.codejava.framework.dao.Config;
import net.codejava.framework.dao.Constants;
import net.codejava.framework.dao.DialplanDAO;
import net.codejava.framework.dao.DialplanDAOImpl;
import net.codejava.framework.model.Activate;
import net.codejava.framework.model.Dialplan;
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

public class DialplanAction extends ActionSupport implements ModelDriven<Dialplan> , ServletContextAware, Constants {
	
	private static final long serialVersionUID = 1L;
	
	private final static Logger cat = LoggerFactory.getLogger(DialplanAction.class);
    
    protected Logger getLogger (){
    	return cat;
    }
    
	private Dialplan dialplan = new Dialplan();
	private Activate activate = new Activate();
	private List<Dialplan> listDialplan = new ArrayList<Dialplan>();
	private DialplanDAO dialplanDAO = new DialplanDAOImpl();
	private List<Activate> listActivate = new ArrayList<Activate>();
	private ActivateDAOImpl activateDAO = new ActivateDAOImpl();
	private ServletContext context;
	private HttpServletRequest request;
	private Opensipsctl opensipsctl = new Opensipsctl();
	
	/*
	 * Universal reload command
	 */
	
	
	/*
	 * dynamic redirect for doReload, check struts.xml doReload redirect
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
	
	public String search() {
		
		cat.info("search id parameter value is : " + dialplan.getId());
		listActivate = activateDAO.listActivate();
		listDialplan = dialplanDAO.searchDialplan(dialplan);
		return SUCCESS;
	}
	
	public String doReload() {		
		String contextPath = context.getRealPath(File.separator);
		String configfile = contextPath + "/" + CMDOPENSIPS_FILE_NAME;
		opensipsctl.allReload(configfile);
		setUrl("/WEB-INF/views/DialplanList.jsp");
		list();
		return SUCCESS;
	}
	
	@SkipValidation
	public List<Activate> getListActivate() {
		return listActivate;
	}

	@SkipValidation
	public void setListActivate(List<Activate> listActivate) {
		this.listActivate = listActivate;
	}
	
	//DI via Spring
	@SkipValidation
	public void setDialplanDAO(DialplanDAO dialplanDAO) {
		
		this.dialplanDAO = dialplanDAO;
	}
		
	@SkipValidation
	public Dialplan getModel() {
		
		return dialplan;
	}

	
	@SkipValidation
	public List<Dialplan> getListDialplan() {
		return listDialplan;
	}
	
	@SkipValidation
	public void setListDialplan(List<Dialplan> listDialplan) {
		this.listDialplan = listDialplan;
	}
	
	@SkipValidation
	public String list() {
		listActivate = activateDAO.listActivate();
		listDialplan = dialplanDAO.listDialplan();
		return SUCCESS;
	}

	@SkipValidation
	public String execute() {
		listActivate = activateDAO.listActivate();

		listDialplan = dialplanDAO.listDialplan();
		
		return SUCCESS;
	}
	
	public Dialplan getDialplan() {
		
		return dialplan;
	}
	
	public void setDialplan(Dialplan dialplan) {
		
		this.dialplan = dialplan;		
	}

	//save customer
	public String addDialplan() {
		
		//save it
		dialplanDAO.addDialplan(dialplan);
	 
		//reload the trusted list
		listDialplan = null;
		listDialplan = dialplanDAO.listDialplan();
		
		return "success";
	
	}
	
	/**
	 * To save or update user.
	 * @return String
	 */
	/* */
	
	
	
	public String saveOrUpdate()
	{	
		//Subject currentUser = SecurityUtils.getSubject();
		/*
		 * set parameters for checking
		 */
		request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		dialplanDAO.saveOrUpdateDialplan(dialplan);
		/*
		/*set reload value on activate table
		**/
		activateDAO.setActivate("1");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("subst_exp", dialplan.getSubst_exp());
		
		//reload the trusted list
		listDialplan = null;
		listDialplan = dialplanDAO.listDialplan();

		return SUCCESS;
	}
	/* */
	
	/**
	 * To list a single trusted by Id.
	 * @return String
	 */
	/* */
	@SkipValidation
	public String edit()
	{
		listActivate = activateDAO.listActivate();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		dialplan = dialplanDAO.listDialplanById(Long.parseLong(request.getParameter("id")));
		String matchExp = dialplan.getMatch_exp();
		String subMatchExp = dialplan.getSubst_exp();
		String ddi = "";
		String extension = "";
		/*index for splitting ddi and extension from their regex translations
		 * this (separator) refers to the non-translated starting point of extension
		 * ie the index of the first integer in extension
		 *
		 */
		Integer separator = matchExp.indexOf(")");
		
		/*
		 * translate back to non-regex for ops editing purposes
		 */
		if (matchExp.contains("|"))
		{
			ddi = matchExp.substring(2,matchExp.indexOf("|"));
		}
		else
		{
			ddi = matchExp.substring(2,separator);
		}
		
		
		extension = matchExp.substring(separator+2, matchExp.length()-2);
		extension = extension.replace("][", ",");
		
		/*
		 * save original ddi and extension in object
		 */
		dialplan.setMatch_exp(ddi);
		dialplan.setSubst_exp(extension);
		return SUCCESS;
	}
	
	/**
	 * To list a single trusted by Id.
	 * @return String
	 */
	/* */
	@SkipValidation
	public String delete()
	{
		listActivate = activateDAO.listActivate();

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		dialplanDAO.deleteDialplan(Long.parseLong(request.getParameter("id")));
		
		//reload the trusted list
		listDialplan = null;
		listDialplan = dialplanDAO.listDialplan();
		
		return SUCCESS;
	}
	
	
	private String regex;
	private Pattern pattern;
	private Matcher matcher;
	
	public void validate() {
		listActivate = activateDAO.listActivate();

		
		// Get the root path from the context
		// remember to reset config

		String contextPath = context.getRealPath(File.separator);
		String configfile = contextPath + "/" + CONFIG_FILE_NAME;

		cat.info("configfile path name value is : " + configfile);

		// Initialise parameters from the config.ini file
		Config config = Config.getInstance();
		try {
			System.out.println(configfile);
			config.setConfigurationFile(configfile);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//**set matcher for validation purposes**//
		
		
		// Validate match
		regex = Config.getConfigRegexMatch();
		System.out.println(regex);
		cat.info("Config regex parameter value is : " + regex);
		pattern = Pattern.compile(regex);
		
		//trimming the field and saving to new variable
		//to avoid regex validation conflict, original field unchanged
		String MatchExpTrim = dialplan.getMatch_exp().replaceAll("\\s+","");
		boolean valid = true;
		matcher = pattern.matcher(MatchExpTrim);	
		if (!matcher.matches()) 
		{
			addFieldError("match_exp", "Calling line number must be valid.");
			valid = false;
		}
		
		
		// Validate submatch
		regex = Config.getConfigRegexSubMatch();
		//subst_exp
		cat.info("Config regex paramater value is : " + regex);
		pattern = Pattern.compile(regex);
				
		matcher = pattern.matcher(dialplan.getSubst_exp());		
		if (!matcher.matches()) 
		{
			addFieldError("subst_exp", "Subaddress must be valid.");
			valid = false;
		}
		
		if(valid)
		{
				String ddi = dialplan.getMatch_exp();
				
				//pull extension from field
				String extension = dialplan.getSubst_exp();
				//split extension into array for parsing
				String[] extensionSetter = extension.split("");
				//set back to string when parsing complete
				String extGetter = "";
				//resulting regex of ddi + extension
				String matchexp;	
				Integer count = 0;
				if (ddi.charAt(0) == '0')
				{
					ddi = ddi + "|" + ddi.substring(1,ddi.length());
				}
				//counter to prevent too many whitespaces
				for (int i = 0; i < ddi.length(); i++)
				{
					if (ddi.charAt(i) == ' ')
					{
						count++;
					}
				}
				ddi = ddi.replaceAll("\\s+","|");
				if (dialplan.getSubst_exp().length() > 25)
				{
					addFieldError("match_exp", "Length cannot exceed 20.");
					valid = false;
				}
				if (count > 2)
				{
					addFieldError("match_exp", "Invalid entry, check whitespaces.");
				}
				
				//regex formatting
				ddi = "^(" + ddi + ")";
					
				//convert input to regex checking for comma	
				for (int i = 0; i < extension.length(); i++)
				{
					if (extensionSetter[i].equals(","))
					{			
						extensionSetter[i] = "][";
					}
					extGetter += extensionSetter[i];
				}
				//extension regex formatting
				extension = "[" + extGetter + "]$"; 
				//combine ddi regex plus extension regex
				matchexp = ddi + extension;
				//set new regex value in model for database
				dialplan.setMatch_exp(matchexp);
				//reset variables to prevent duplicate character generation
				//in form field before passing validation
				//IE failed submits will keep adding characters to field input even with failed
				//form submit
				dialplan.setSubst_exp(ddi + "(.+)");
				
				
				ddi = "";
				matchexp = "";
				extension = "";
	}
	
				
	
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}
	
	public HttpServletRequest getRequest()
	{
		
		return request;
	}
	
	public void setHttpServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}
	

}

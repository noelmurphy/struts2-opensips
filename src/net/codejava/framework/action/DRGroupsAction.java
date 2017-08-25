package net.codejava.framework.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.codejava.framework.dao.ActivateDAOImpl;
import net.codejava.framework.dao.Config;
import net.codejava.framework.dao.Constants;
import net.codejava.framework.dao.DRGroupsDAO;
import net.codejava.framework.dao.DRGroupsDAOImpl;
import net.codejava.framework.model.Activate;
import net.codejava.framework.model.DRGroups;
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

public class DRGroupsAction extends ActionSupport implements ModelDriven<DRGroups> , ServletContextAware, Constants {

	private static final long serialVersionUID = 1L;

	private final static Logger cat = LoggerFactory.getLogger(DRGroupsAction.class);
    
    protected Logger getLogger (){
    	return cat;
    }
    
    private DRGroups drgroups = new DRGroups();
	private List<DRGroups> listDRGroups = new ArrayList<DRGroups>();
	private DRGroupsDAO drgroupsDAO = new DRGroupsDAOImpl();
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
		setUrl("/WEB-INF/views/DRGroupsList.jsp");
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
	public DRGroups getModel() {
		
		return drgroups;
	}

	public DRGroups getDrgroups() {
		
		return drgroups;
	}

	@SkipValidation
	public void setDrgroups(DRGroups drgroups) {
		this.drgroups = drgroups;
	}

	public List<DRGroups> getListDRGroups() {
		return listDRGroups;
	}

	@SkipValidation
	public void setListDRGroups(List<DRGroups> listDRGroups) {
		this.listDRGroups = listDRGroups;
	}

	@SkipValidation
	public String list() {
		listActivate = activateDAO.listActivate();
		listDRGroups = drgroupsDAO.listDRGroups();
		return SUCCESS;
	}

	@SkipValidation
	public String execute() {
		listActivate = activateDAO.listActivate();
		listDRGroups = drgroupsDAO.listDRGroups();
		return SUCCESS;
	}
	
	//save customer
	public String addDRGroups() {
			
		//save it
		drgroupsDAO.addDRGroups(drgroups);
		 
		//reload the trusted list
		listDRGroups = null;
		listDRGroups = drgroupsDAO.listDRGroups();
			
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
		drgroupsDAO.saveOrUpdateDRGroups(drgroups);

		activateDAO.setActivate("1");

		//reload the DRGroups list
		listDRGroups = null;
		listDRGroups = drgroupsDAO.listDRGroups();

		return SUCCESS;
	}
	
	@SkipValidation
	public String edit()
	{
		listActivate = activateDAO.listActivate();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		drgroups = drgroupsDAO.listDRGroupsById(Long.parseLong(request.getParameter("uuid")));

		return SUCCESS;
	}
	
	@SkipValidation
	public String delete()
	{
		listActivate = activateDAO.listActivate();

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		drgroupsDAO.deleteDRGroups(Long.parseLong(request.getParameter("uuid")));
		
		//reload the trusted list
		listDRGroups = null;
		listDRGroups = drgroupsDAO.listDRGroups();
		
		return SUCCESS;
	}
	
	private Long uuid;
	private String username;
	private String domain;
	private String attribute;
	private String value;
	private int type;
	private Timestamp modified;
	
	
	public DRGroupsDAO getDrgroupsDAO() {
		return drgroupsDAO;
	}

	@SkipValidation
	public void setDrgroupsDAO(DRGroupsDAO drgroupsDAO) {
		this.drgroupsDAO = drgroupsDAO;
	}

	public ServletContext getContext() {
		return context;
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
		matcher = pattern.matcher(dialplan.getSrc_ip());		
		if (!matcher.matches()) {
			addFieldError("src_ip", "IP must be valid.");
		}
		*/
		
		// Check Protocol
		regex = Config.getConfigRegexProt();
		cat.info("Config regex paramter value is : " + regex);
		pattern = Pattern.compile(regex);
		/*
		matcher = pattern.matcher(dialplan.getProto());		
		if (!matcher.matches()) {
			addFieldError("proto", "Protocol must be valid.");
		}
		*/
		// Check Tag
		/*
		String tagsetter = dialplan.getSrc_ip().replace(".", "");
		if (!dialplan.getTag().equals(tagsetter) && !dialplan.getTag().equals("")) {
			addFieldError("tag",
					"Enter valid tag or leave me blank for auto-generate");
		} else if (dialplan.getTag().equals("")) {
			dialplan.setTag(tagsetter);
		}
		*/
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

}

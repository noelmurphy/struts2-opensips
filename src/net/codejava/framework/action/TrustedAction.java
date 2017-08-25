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
import net.codejava.framework.dao.TrustedDAO;
import net.codejava.framework.dao.TrustedDAOImpl;
import net.codejava.framework.dao.UserDAOImpl;
import net.codejava.framework.model.Activate;
import net.codejava.framework.model.Trusted;
import net.codejava.framework.model.User;
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

public class TrustedAction extends ActionSupport implements ModelDriven<Trusted> , ServletContextAware, Constants {
	private static final long serialVersionUID = 1L;
	
	private final static Logger cat = LoggerFactory.getLogger(TrustedAction.class);
    DialplanAction reloadGetter  = new DialplanAction();
    protected Logger getLogger (){
    	return cat;
    }
    
	private Trusted trusted = new Trusted();
	private List<Trusted> listTrusted = new ArrayList<Trusted>();
	private List<Activate> listActivate = new ArrayList<Activate>();
	private TrustedDAO trustedDAO = new TrustedDAOImpl();
	private ServletContext context;
	private User user;
	private UserDAOImpl userDAO;
	private ActivateDAOImpl activateDAO;
	private HttpServletRequest request;
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
	public String doReload()
	{
		//Opensipsctl.trustedReload();
		return SUCCESS;
	}
	
	//DI via Spring
	@SkipValidation
	public void setTrustedDAO(TrustedDAO trustedDAO) {
		
		this.trustedDAO = trustedDAO;
	}
		
	/* */
	@SkipValidation
	public Trusted getModel() {
		
		return trusted;
	}
	/* */

	
	
	@SkipValidation
	public List<Trusted> getListTrusted() {
		return listTrusted;
	}

	@SkipValidation
	public void setListTrusted(List<Trusted> listTrusted) {
		this.listTrusted = listTrusted;
	}
	
	@SkipValidation
	public List<Activate> getListActivate() {
		return listActivate;
	}

	@SkipValidation
	public void setListActivate(List<Activate> listActivate) {
		this.listActivate = listActivate;
	}
	
	
	@SkipValidation
	public String list() {
		
		listTrusted = trustedDAO.listTrusted();
		return SUCCESS;
	}

	@SkipValidation
	public String execute() {
		
		
		listActivate = activateDAO.listActivate();
		listTrusted = trustedDAO.listTrusted();
		return SUCCESS;
	}
	
	public Trusted getTrusted() {
		
		return trusted;
	}
	
	public void setTrusted(Trusted trusted) {
		
		this.trusted = trusted;		
	}

	//save customer
	public String addTrusted() {
		
		//save it
		trustedDAO.addTrusted(trusted);
	 
		//reload the trusted list
		listTrusted = null;
		listTrusted = trustedDAO.listTrusted();
		
		return "success";
	
	}
	
	/**
	 * To save or update user.
	 * @return String
	 */
	/* */
	
	public String saveOrUpdate()
	{	
		trustedDAO.saveOrUpdateTrusted(trusted);
		//reload the trusted list
		listTrusted = null;
		listTrusted = trustedDAO.listTrusted();
		request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		request.setAttribute("src_ip", src_ip);
		if (trusted.getTag() == null)
		{
			trusted.setTag(trusted.getSrc_ip().replace(".", ""));
		}

		return SUCCESS;
	}
	
	public HttpServletRequest getRequest()
	{
		return request;
	}
	
	public void setHttpServletRequest(HttpServletRequest request)
	{
		this.request = request;
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
		trusted = trustedDAO.listTrustedById(Long.parseLong(request.getParameter("id")));

		return SUCCESS;
	}
	/* */
	
	/**
	http://192.168.31.155 * To list a single trusted by Id.
	 * @return String
	 */
	/* */
	@SkipValidation
	public String delete()
	{
		listActivate = activateDAO.listActivate();

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		trustedDAO.deleteTrusted(Long.parseLong(request.getParameter("id")));
		
		//reload the trusted list
		listTrusted = null;
		listTrusted = trustedDAO.listTrusted();
		
		return SUCCESS;
	}
	/* */
	
	/* JavaBeans Properties to Receive Request Parameters */	
	private int id;
	private String src_ip;
	private String proto;
	private String from_pattern;
	private String tag;
	 

	public int getId() 
	{
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSrc_ip() {
		return src_ip;
	}

	
	
	public void setSrc_ip(String src_ip) {
		this.src_ip = src_ip;
	}

	public String getProto() {
		return proto;
	}

	public void setProto(String proto) {
		this.proto = proto;
	}

	public void setFrom_pattern(String from_pattern) {
		this.from_pattern = from_pattern;
	}

	public String getFrom_pattern() {
		return from_pattern;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	
	private String regex;
	private Pattern pattern;
	private Matcher matcher;
	public void validate() {
		
		// Get the root path from the context
		String contextPath = context.getRealPath(File.separator);
		String configfile = contextPath + "/" + CONFIG_FILE_NAME;
		
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
		System.out.println(regex);
		cat.info("Config regex paramter value is : " + regex);
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(trusted.getSrc_ip());		
		if (!matcher.matches()) {
			addFieldError("src_ip", "IP must be valid.");
		}
		System.out.println(regex);

		// Check Protocol
		regex = Config.getConfigRegexProt();
		cat.info("Config regex paramter value is : " + regex);
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(trusted.getProto());		
		if (!matcher.matches()) {
			addFieldError("proto", "Protocol must be valid.");
		}

		// Check Tag
		String tagsetter = trusted.getSrc_ip().replace(".", "");
		if (!trusted.getTag().equals(tagsetter) && !trusted.getTag().equals("")) {
			addFieldError("tag",
					"Enter valid tag or leave me blank for auto-generate");
		} else if (trusted.getTag().equals("")) {
			trusted.setTag(tagsetter);
		}
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}
	
	public ServletContext getServletContext()
	{
		return context;
	}
	
	
}

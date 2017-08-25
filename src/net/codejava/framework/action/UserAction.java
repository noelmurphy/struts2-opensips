package net.codejava.framework.action;

import net.codejava.framework.dao.Constants;
import net.codejava.framework.dao.UserDAO;
import net.codejava.framework.dao.UserDAOImpl;
import net.codejava.framework.model.Trusted;
import net.codejava.framework.model.User;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>, ServletContextAware, Constants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User user = new User();

	private UserDAO userDAO = new UserDAOImpl();
	private ServletContext context;
	private List<User> listUsers = new ArrayList<User>();
	
public void setUserDAO(UserDAO userDAO) {
		
		this.userDAO = userDAO;
	}
	

	
	
	public String create()
	{
		listUsers = userDAO.listUser();
		Subject currentUser = SecurityUtils.getSubject();
			for (int i = 0; i < listUsers.size(); i++)
			{
				if (listUsers.get(i).getEmail().equals(user.getEmail()) )
				{				
					addFieldError("email","Account already exists!");
					
				}
				else
					userDAO.register(user.getEmail(), user.getPassword(), user.getRoleName());
					return SUCCESS;
					
				}
			return ERROR;
		

	}
	
	public String delete()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		userDAO.deleteUser(Long.parseLong(request.getParameter("id")));
		
		
		
		return SUCCESS;
	}
	
	
	public String logout()
	{
		SecurityUtils.getSubject().logout();
		return SUCCESS;
	}
	
	public String login() throws UnknownAccountException, IncorrectCredentialsException, AuthenticationException
	{
		try
		{
		userDAO.login(user);
		}
		catch (UnknownAccountException ex) {
			//username provided was not found
			throw new UnknownAccountException();		
		}
		catch (IncorrectCredentialsException ex) {
			//password provided did not match password found in database
			//for the username provided
			throw new IncorrectCredentialsException();
		}
		catch (AuthenticationException ex){
			throw new IncorrectCredentialsException();
			}
		return SUCCESS;
	}
	
	public void validate()
	{
		try
		{
		userDAO.login(user);
		}
		catch (UnknownAccountException ex)
		{
			addFieldError("email", "Incorrect password/account doesn't exist.");
		}
		catch (IncorrectCredentialsException ex) 
		{
			addFieldError("email", "Incorrect password/account doesn't exist.");
		}
		catch (AuthenticationException ex){
			
				addFieldError("email", "Incorrect password/account doesn't exist.");
			}
		
	}
	
	public String list() {		
		setListUsers(userDAO.listUser());
		return SUCCESS;
	}

public String execute() {
	setListUsers(userDAO.listUser());
	return SUCCESS;
	}
	
	@Override
	public void setServletContext(ServletContext context) {
		this.setContext(context);
	}

	@Override
	public User getModel() {
		return user;
	}
	
	private int id;
	private String username;
	private String email;
	private String password;
	private String roleName;
	 

	public int getId() 
	{
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<User> getListUsers() {
		return listUsers;
	}


	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}
	
	public String getRoleName()
	{
		return roleName;
	}
	

	public ServletContext getContext() {
		return context;
	}


	public void setContext(ServletContext context) {
		this.context = context;
	}


}
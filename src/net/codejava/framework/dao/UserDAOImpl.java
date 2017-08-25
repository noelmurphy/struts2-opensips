package net.codejava.framework.dao;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import net.codejava.framework.model.Trusted;
import net.codejava.framework.model.User;
import net.codejava.framework.model.UserRole;


public class UserDAOImpl implements UserDAO {

	SessionFactory sessionFactory = new AnnotationConfiguration().configure("opensips.cfg.xml").buildSessionFactory();

	@SessionTarget
	Session session = null;

	@TransactionTarget
	Transaction transaction = null;
	public User getCurrentUser() {
		  org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();

		  if (currentUser.isAuthenticated()) {
		    String mail = (String) currentUser.getSession().getAttribute("username");
		    User user = getUserByEmail(mail);
		    return user;
		  } else {
		    return null;
		  }
	}
	
	public User getUserByEmail(String email) {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		transaction.begin();
		User user = (User) session.createQuery("from User where email=?").setString(0, email).uniqueResult();
		session.getTransaction().commit();
		
		session.close();
		return user;
	}

	
	
	
	public void register(String email, String password, String roleName){
		
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			try {
				registrate(session, email, password, roleName);
			} finally {
				session.getTransaction().commit();
				if (session.isOpen()) session.close();
			}
			
	
	}
	
	public void registrate(Session session, String email, String plainTextPassword, String roleName) {
		User user = new User();
		UserRole userRole = new UserRole();
		userRole.setEmail(email);
		userRole.setRoleName(roleName);
		user.setUsername(email);
		user.setEmail(email);
		generatePassword(user, plainTextPassword);
		user.setRoleName(roleName);
		
		
		session.save(user);
		session.save(userRole);
		Query query = session.createQuery("insert into UserRole(roleName, email) select email,roleName from User where roleName =:roleName and email =:email");
		query.setParameter("roleName", roleName);
		query.setParameter("email", email);
		query.executeUpdate();		
	}
	
	public void create(String email, String plainTextPassword) {
		User user = new User();
		user.setUsername(email);
		user.setEmail(email);
		
		generatePassword(user, plainTextPassword);

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();


	}
	
	@SuppressWarnings("unchecked")
	public List<User> listUser() {
	    	
	    	List<User> list = null;
	    	try {
	    		session = sessionFactory.openSession();
	            transaction = session.beginTransaction();
	            transaction.begin();
	    		list = session.createQuery("from User ORDER BY id DESC").list();
	    		
	    	} catch (Exception e) {
				e.printStackTrace();
			}
	    	return list;
	    	
	    }

	public void deleteUser(Long userId)
	{
	User user =null;
	session = sessionFactory.openSession();
    transaction = session.beginTransaction();
    transaction.begin();
	 user = (User) session.get(User.class, userId);
	session.delete(user);
	transaction.commit();

	}
	 public void login(User user) throws AuthenticationException
	 {
				
		//see /login.jsp for these form fields

			
			UsernamePasswordToken token = 
			new UsernamePasswordToken(user.getEmail(), user.getPassword());
		
			Subject subject = SecurityUtils.getSubject();				
				//The use of IniShiroFilter specified in web.xml
				//caused JSecurity to create the DefaultWebSecurityManager object
				//see: http://jsecurity.org/api/org/jsecurity/web/DefaultWebSecurityManager.html
				//This security manager is the default for web-based applications
				//The SecurityUtils was provided that security manager automatically
				//The configuration specified in web.xml caused 
				//a JdbcRealm object to be provided to the SecurityManager
				//so when the login method is called that JdbcRealm
				//object will be used
				//This application uses all the other defaults
				//For example the default authentication query string is
				//"select password from users where username = ?"
				//since the database this application uses (securityDB)
				//has a users table and that table has a column named username
				//and a column named password, the default authentication query
				//string will work
				//The call to login will cause the following to occur
				//Shiro will query the database for a password associated with the 
				//provided username (which is stored in token).  If a password is found 
				//and matches the password
				//provided by the user (also stored in the token), a new Subject will be created that is
				//authenticated.  This subject will be bound to the session for the
				//user who made this request
				//see:  http://shiro.apache.org/static/current/apidocs/org/apache/shiro/authc/Authenticator.html
				//for a list of potential Exceptions that might be generated if
				//authentication fails (e.g. incorrect password, no username found)
				try
				{
				subject.login(token);
				}
				catch (AuthenticationException ex) {
					throw ex;	
				}
				user.setEmail(user.getEmail());
				user.setUsername(user.getUsername());	
				user.setPassword(user.getPassword());
				user.setSalt(user.getSalt());
				token.clear();
	 }
	 
	public void generatePassword(User user, String plainTextPassword) {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		Object salt = rng.nextBytes();

		// Now hash the plain-text password with the random salt and multiple
		// iterations and then Base64-encode the value (requires less space than
		// Hex):
		String hashedPasswordBase64 = new Sha256Hash(plainTextPassword, salt, 1024).toBase64();

		user.setPassword(hashedPasswordBase64);
		user.setSalt(salt.toString());
	}




}

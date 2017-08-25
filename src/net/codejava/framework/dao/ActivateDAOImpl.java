package net.codejava.framework.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import net.codejava.framework.model.Activate;
public class ActivateDAOImpl implements ActivateDAO  {

	
	SessionFactory sessionFactory = new  AnnotationConfiguration().configure("opensips.cfg.xml").buildSessionFactory();
    


	@SessionTarget
		Session session = null;

		
	@TransactionTarget
	Transaction transaction = null;
	    
	    public List<Activate> listActivate() {
	    	
	    	List<Activate> listActivate = null;
	    	try {
	    		session = sessionFactory.openSession();
	            transaction = session.beginTransaction();
	            transaction.begin();
	    		listActivate = session.createQuery("FROM Activate").list();
	    		session.close();
	    	} catch (Exception e) {
				e.printStackTrace();
			}
	    	return listActivate;
	    	
	    }
	    
	    public void setActivate(String value)
	    {

	    	session = sessionFactory.openSession();
	        transaction = session.beginTransaction();
	        transaction.begin();
	        Query query;
	        if (value.equals("1"))
	        {
	        query = session.createQuery("UPDATE Activate SET value=:value WHERE value=0");
	        }
	        else
	        {
		    query = session.createQuery("UPDATE Activate SET value=:value WHERE value=1");

	        }
	        
	        query.setParameter("value", value);
	        query.executeUpdate();
			
			transaction.commit();
			session.close();

	    }
	   
	    

}

package net.codejava.framework.dao;

import java.util.List;

import net.codejava.framework.model.Quota;
import net.codejava.framework.model.Trusted;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
 
public class TrustedDAOImpl implements TrustedDAO {
	
SessionFactory sessionFactory = new  AnnotationConfiguration().configure("opensips.cfg.xml").buildSessionFactory();
    


@SessionTarget
	Session session = null;

	
	@TransactionTarget
	Transaction transaction = null;
    
    public List<Trusted> listTrusted() {
    	
    	List<Trusted> list = null;
    	try {
    		session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.begin();
    		list = session.createQuery("from Trusted ORDER BY id DESC").list();
    		session.close();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    	
    }
        
	//add the customer
	public void addTrusted(Trusted trusted){
				
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(trusted);
		transaction.commit();
		session.close();
		
	}
    /* */
	//add the customer
    @Override
	public void saveOrUpdateTrusted(Trusted trusted){

    	session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(trusted);

			transaction.commit();
			session.close();
	}
    
    @Override
    public Trusted listTrustedById(Long trustedId) {
    	
    	Trusted mTrusted =null;
    	session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mTrusted = (Trusted) session.get(Trusted.class, trustedId);
    	transaction.commit();
    	session.close();
		return mTrusted;
    }

	@Override
	public void deleteTrusted(Long trustedId) {
		
		Trusted mTrusted =null;
    	session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mTrusted = (Trusted) session.get(Trusted.class, trustedId);
		session.delete(mTrusted);
		transaction.commit();
    	session.close();
		
	}
	/* */
}
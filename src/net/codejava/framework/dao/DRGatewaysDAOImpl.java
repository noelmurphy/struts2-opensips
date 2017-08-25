package net.codejava.framework.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import net.codejava.framework.model.DRGateways;

public class DRGatewaysDAOImpl implements DRGatewaysDAO {

SessionFactory sessionFactory = new  AnnotationConfiguration().configure("opensips.cfg.xml").buildSessionFactory();
	
    @SessionTarget
	Session session = null;
	
	@TransactionTarget
	Transaction transaction = null;
	
	public List<DRGateways> listDRGateways() {
		List<DRGateways> list = null;
    	try {
    		session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.begin();
    		list = session.createQuery("from DRGateways ORDER BY gwid DESC").list();
    		session.close();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
	}


	public void addDRGateways(DRGateways drgateways) {
		
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(drgateways);
		transaction.commit();
		session.close();
		
	}


	public void saveOrUpdateDRGateways(DRGateways drgateways) {
		
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(drgateways);
		transaction.commit();
		session.close();
		
	}


	public DRGateways listDRGatewaysById(Long drgatewaysId) {
		
		DRGateways mDRGateways =null;
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mDRGateways = (DRGateways) session.get(DRGateways.class, drgatewaysId);
    	transaction.commit();
		session.close();
		return mDRGateways;
	}

	public void deleteDRGateways(Long drgatewaysId) {
		
		DRGateways mDRGateways =null;
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mDRGateways = (DRGateways) session.get(DRGateways.class, drgatewaysId);
		session.delete(mDRGateways);
	   	transaction.commit();
		session.close();
		
	}
}

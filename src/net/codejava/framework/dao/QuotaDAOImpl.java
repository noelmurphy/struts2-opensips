package net.codejava.framework.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import net.codejava.framework.model.Quota;

public class QuotaDAOImpl implements QuotaDAO {
	
	SessionFactory sessionFactory = new  AnnotationConfiguration().configure("cdrtool.cfg.xml").buildSessionFactory();

	private enum MyStatus { NO,YES };
	
    @SessionTarget
	Session session = null;
	
	@TransactionTarget
	Transaction transaction = null;

	@Override
	public List<Quota> listQuota() {
		List<Quota> list = null;
    	try {
    		session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.begin();
    		list = session.createQuery("from Quota ORDER BY id DESC").list();
    		//transaction.commit();
    		session.close();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
	}

	@Override
	public void addQuota(Quota quota) {
		
		Quota mQuota = quota;
		
		// Set the Notified value
		Timestamp mNotified = Timestamp.valueOf("1970-01-01 00:00:00");
		mQuota.setNotified(mNotified);
		// Set the datasource value
		mQuota.setDatasource("opensips_radius");
		// traffic
		mQuota.setTraffic("0");
		
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(mQuota);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void saveOrUpdateQuota(Quota quota) {
		
		Quota mQuota = quota;
		
		// Set the Notified value
		Timestamp mNotified = Timestamp.valueOf("1970-01-01 00:00:00");
		mQuota.setNotified(mNotified);
		// Set the datasource value
		mQuota.setDatasource("opensips_radius");
		// traffic
		mQuota.setTraffic("0");
		
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(mQuota);
		transaction.commit();
		session.close();
		
	}

	@Override
	public Quota listQuotaById(Long quotaId) {
		Quota mQuota =null;
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mQuota = (Quota) session.get(Quota.class, quotaId);
    	transaction.commit();
		session.close();
		return mQuota;
	}

	@Override
	public void deleteQuota(Long quotaId) {
		Quota mQuota =null;
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mQuota = (Quota) session.get(Quota.class, quotaId);
    	session.delete(mQuota);
    	transaction.commit();
		session.close();
		
	}

}

package net.codejava.framework.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import net.codejava.framework.model.DRGroups;
import net.codejava.framework.model.DRRules;

public class DRRulesDAOImpl implements DRRulesDAO {
	
SessionFactory sessionFactory = new  AnnotationConfiguration().configure("opensips.cfg.xml").buildSessionFactory();
	
    @SessionTarget
	Session session = null;
	
	@TransactionTarget
	Transaction transaction = null;

	@Override
	public List<DRRules> listDRRules() {
		List<DRRules> list = null;
    	try {
    		session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.begin();
    		list = session.createQuery("from DRRules ORDER BY ruleid DESC").list();
    		session.close();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
	}
	

	@Override
	public void addDRRules(DRRules drrules) {
		
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(drrules);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void saveOrUpdateDRRules(DRRules drrules) {
		
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(drrules);
		transaction.commit();
		session.close();
		
	}

	@Override
	public DRRules listDRRulesById(Long drrulesId) {
		
		DRRules mDRRules =null;
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mDRRules = (DRRules) session.get(DRRules.class, drrulesId);
    	transaction.commit();
		session.close();
		return mDRRules;
	}

	@Override
	public void deleteDRRules(Long drrulesId) {
		
		DRRules mDRRules =null;
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mDRRules = (DRRules) session.get(DRGroups.class, drrulesId);
		session.delete(mDRRules);
		transaction.commit();
		session.close();
		
	}

}

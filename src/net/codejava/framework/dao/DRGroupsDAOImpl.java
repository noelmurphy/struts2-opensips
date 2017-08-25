package net.codejava.framework.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import net.codejava.framework.model.DRGroups;


public class DRGroupsDAOImpl implements DRGroupsDAO {

SessionFactory sessionFactory = new  AnnotationConfiguration().configure("opensips.cfg.xml").buildSessionFactory();
	
    @SessionTarget
	Session session = null;
	
	@TransactionTarget
	Transaction transaction = null;
	
	@Override
	public List<DRGroups> listDRGroups() {
		List<DRGroups> list = null;
    	try {
    		session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.begin();
    		list = session.createQuery("from DRGroups ORDER BY modified DESC").list();
    		session.close();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
	}

	@Override
	public void addDRGroups(DRGroups drgroup) {
		
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(drgroup);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void saveOrUpdateDRGroups(DRGroups drgroup) {
		
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(drgroup);
		transaction.commit();
		session.close();
		
	}

	@Override
	public DRGroups listDRGroupsById(Long drgroupsId) {
		
		DRGroups mDRGroups =null;
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mDRGroups = (DRGroups) session.get(DRGroups.class, drgroupsId);
    	transaction.commit();
		session.close();
		return mDRGroups;
	}

	@Override
	public void deleteDRGroups(Long drgroupsId) {
		
		DRGroups mDRGroups =null;
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mDRGroups = (DRGroups) session.get(DRGroups.class, drgroupsId);
		session.delete(mDRGroups);
		transaction.commit();
		session.close();
		
	}
	
}

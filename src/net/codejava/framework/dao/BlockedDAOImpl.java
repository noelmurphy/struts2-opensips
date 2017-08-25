package net.codejava.framework.dao;

import java.util.Calendar;
import java.util.List;

//import net.codejava.framework.action.BlockedAction;
import net.codejava.framework.model.Blocked;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class BlockedDAOImpl implements BlockedDAO {
	
	private final static Logger cat = LoggerFactory.getLogger(BlockedDAOImpl.class);
    
    protected Logger getLogger (){
    	return cat;
    }

	SessionFactory sessionFactory = new  AnnotationConfiguration().configure("opensips.cfg.xml").buildSessionFactory();
	
    @SessionTarget
	Session session = null;
	
	@TransactionTarget
	Transaction transaction = null;
	
	@Override
	public List<Blocked> listBlocked() {
		List<Blocked> list = null;
    	try {
    		session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.begin();
    		list = session.createQuery("from Blocked ORDER BY id DESC").list();
    		session.close();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
	}

	@Override
	public void addBlocked(Blocked blocked) {
		
		// 1) create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// 2) get a java.util.Date from the calendar instance.
		//	  this date will represent the current instant, or "now".
		java.util.Date now = calendar.getTime();

		// 3) a java current time (now) instance
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		
		Blocked mBlocked = blocked;
		//Timestamp NO_DATE = new Timestamp(0L);
		if (mBlocked.getLast_modified() == null) {
			mBlocked.setLast_modified(currentTimestamp);
		}
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.save(mBlocked);
		transaction.commit();
		session.close();
		
	}
	
	@Override
	public void updateBlocked(Blocked blocked) {
		
		cat.info("UpdateBlocked id parameter value is : " + blocked.getId());
		cat.info("UpdateBlocked last_modified parameter value is : " + blocked.getLast_modified());
		cat.info("UpdateBlocked domain parameter value is : " + blocked.getDomain());
		cat.info("UpdateBlocked group parameter value is : " + blocked.getGrp());
		cat.info("UpdateBlocked username parameter value is : " + blocked.getUsername());
		
		// 1) create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// 2) get a java.util.Date from the calendar instance.
		//	  this date will represent the current instant, or "now".
		java.util.Date now = calendar.getTime();

		// 3) a java current time (now) instance
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		
		Blocked mBlocked = blocked;
		if (mBlocked.getLast_modified() == null) {
			mBlocked.setLast_modified(currentTimestamp);
		}
		// Test
		mBlocked.setLast_modified(currentTimestamp);
		
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.update(mBlocked);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void saveOrUpdateBlocked(Blocked blocked) {

		// 1) create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// 2) get a java.util.Date from the calendar instance.
		// this date will represent the current instant, or "now".
		java.util.Date now = calendar.getTime();

		// 3) a java current time (now) instance
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
				now.getTime());

		Blocked mBlocked = blocked;
		if (mBlocked.getLast_modified() == null) {
			mBlocked.setLast_modified(currentTimestamp);
		}
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		transaction.begin();
		session.saveOrUpdate(mBlocked);
		transaction.commit();
		session.close();

	}

	@Override
	public Blocked listBlockedById(Long blockedId) {
		Blocked mBlocked =null;
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mBlocked = (Blocked) session.get(Blocked.class, blockedId);
    	transaction.commit();
    	session.close();
		return mBlocked;
	}

	@Override
	public void deleteBlocked(Long blockedId) {
		Blocked mBlocked =null;
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mBlocked = (Blocked) session.get(Blocked.class, blockedId);
    	session.delete(mBlocked);
    	transaction.commit();
    	session.close();
		
	}
	
	@Override
	public List<Blocked> searchBlocked(Blocked blocked) {
		Blocked mBlocked =null;		
		List<Blocked> list = null;
		String mQueryTable = "from Blocked";
		String mQueryUsername = "%";
		String mQueryDomain = "%";
		String mQueryGroup = "%";
		
		mBlocked = blocked;
		if (!blocked.getUsername().isEmpty()) {
			mQueryUsername = "%"+blocked.getUsername()+"%";
		}
		if (!blocked.getDomain().isEmpty()) {
			mQueryDomain = "%"+blocked.getDomain()+"%";
		}
		if (!blocked.getGrp().isEmpty()) {
			mQueryGroup = "%"+blocked.getGrp()+"%";
		}
		
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();

        /* */
        list = session.createQuery("from Blocked WHERE" +
        		" username LIKE :username" +
        		" AND domain LIKE :domain" +
        		" AND grp LIKE :grp" +
        		" Order By id DESC")
        		.setParameter("username", mQueryUsername)
        		.setParameter("domain", mQueryDomain)
        		.setParameter("grp", mQueryGroup)
        		.list();
       /* */
    	transaction.commit();
    	session.close();
		return list;
	}

}

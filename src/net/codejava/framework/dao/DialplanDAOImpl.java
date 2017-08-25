package net.codejava.framework.dao;

import java.util.ArrayList;
import java.util.List;

import net.codejava.framework.model.Activate;
import net.codejava.framework.model.Blocked;
import net.codejava.framework.model.DRGateways;
import net.codejava.framework.model.Dialplan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
 
public class DialplanDAOImpl implements DialplanDAO {
	
SessionFactory sessionFactory = new  AnnotationConfiguration().configure("opensips.cfg.xml").buildSessionFactory();
	Activate activate = new Activate();
	private List<Activate> activateList = new ArrayList<Activate>();

    @SessionTarget
	Session session = null;
	
	@TransactionTarget
	Transaction transaction = null;

    
    public List<Dialplan> listDialplan() {
    	
    	List<Dialplan> list = null;
    	try {
    		session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.begin();
    		list = session.createQuery("from Dialplan ORDER BY id DESC").list();
    		session.close();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    	
    }
    
    
    
	//add the customer
	public void addDialplan(Dialplan dialplan){
				
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(dialplan);
		transaction.commit();
		session.close();
		
	}
    
	//add the customer
    @Override
	public void saveOrUpdateDialplan(Dialplan dialplan){
				
    	session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
		session.saveOrUpdate(dialplan);
		
		transaction.commit();
    		session.close();

	}
    

    @Override
    public Dialplan listDialplanById(Long dialplanId) {
    	
    	Dialplan mDialplan =null;
    	session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mDialplan = (Dialplan) session.get(Dialplan.class, dialplanId);
    	transaction.commit();
    	session.close();
		return mDialplan;
    }
    
    
    
	public List<Dialplan> searchDialplan(Dialplan dialplan) {
		Dialplan mDialplan =null;		
		List<Dialplan> list = null;
		/**
		dpid
		pr
		match_exp
		subst_exp
		repl_exp
		attrs
		**/

	
	
		mDialplan = dialplan;

		String mQueryTable = "from Dialplan";
		String mQueryDpid = "%";
		String mQueryPr = "%";
		String mQueryMatch_exp = "%";
		String mQuerySubst_exp = "%";
		String mQueryRepl_exp = "%";
		String mQueryAttrs= "%";

		if (!dialplan.getDpid().isEmpty()) {
			mQueryDpid = "%"+dialplan.getDpid()+"%";
		}
		if (!dialplan.getPr().isEmpty()) {
			mQueryPr = "%"+dialplan.getPr()+"%";
		}
		if (!dialplan.getMatch_exp().isEmpty()) {
			mQueryMatch_exp = "%"+dialplan.getMatch_exp()+"%";
		}
		if (!dialplan.getSubst_exp().isEmpty()) {
			mQueryMatch_exp = "%"+dialplan.getSubst_exp()+"%";
		}
		if (!dialplan.getRepl_exp().isEmpty()) {
			mQueryMatch_exp = "%"+dialplan.getRepl_exp()+"%";
		}
		if (!dialplan.getAttrs().isEmpty()) {
			mQueryMatch_exp = "%"+dialplan.getAttrs()+"%";
		}
		
		session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();

        /**
		dpid
		pr
		match_exp
		subst_exp
		repl_exp
		attrs
		**/
        list = session.createQuery("from Dialplan WHERE" +
        		" dpid LIKE :dpid" +
        		" AND pr LIKE :pr" +
        		" AND match_exp LIKE :match_exp" +
        		" AND subst_exp LIKE :subst_exp" +
        		" AND repl_exp LIKE :repl_exp" +
        		" AND attrs LIKE :attrs" +
        		" Order By id DESC")
        		.setParameter("dpid", mQueryDpid)
        		.setParameter("pr", mQueryPr)
        		.setParameter("match_exp", mQueryMatch_exp)
        		.setParameter("subst_exp", mQuerySubst_exp)
        		.setParameter("repl_exp", mQueryRepl_exp)
        		.setParameter("attrs", mQueryAttrs)
        		.list();
        
       /* */
    	transaction.commit();
    	session.close();
		return list;
	}




     

	@Override
	public void deleteDialplan(Long dialplanId) {
		
		Dialplan mDialplan =null;
    	session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();
    	mDialplan = (Dialplan) session.get(Dialplan.class, dialplanId);
		session.delete(mDialplan);
		transaction.commit();
    	session.close();
		
	}

}
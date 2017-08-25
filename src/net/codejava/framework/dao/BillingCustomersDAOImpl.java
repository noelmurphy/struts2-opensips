package net.codejava.framework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import net.codejava.framework.model.BillingCustomers;
import net.codejava.framework.model.Trusted;

public class BillingCustomersDAOImpl implements BillingCustomersDAO {
	SessionFactory sessionFactory = new  AnnotationConfiguration().configure("cdrtool.cfg.xml").buildSessionFactory();
    Trusted trusted = new Trusted();
    
	@SessionTarget
	Session session = null;
	
	@TransactionTarget
	Transaction transaction = null;
	/*
	+-------------------+----------------------+------+-----+---------+----------------+
	| Field             | Type                 | Null | Key | Default | Extra          |
	+-------------------+----------------------+------+-----+---------+----------------+
	| id                | bigint(20) unsigned  | NO   | PRI | NULL    | auto_increment |
	| reseller_id       | int(10) unsigned     | NO   | MUL | NULL    |                |
	| gateway           | varchar(15)          | NO   | MUL |         |                |
	| domain            | varchar(64)          | NO   |     |         |                |
	| subscriber        | varchar(128)         | NO   | MUL |         |                |
	| profile_name1     | varchar(25)          | NO   |     | NULL    |                |
	| profile_name1_alt | varchar(25)          | NO   |     | NULL    |                |
	| profile_name2     | varchar(25)          | NO   |     | NULL    |                |
	| profile_name2_alt | varchar(25)          | NO   |     | NULL    |                |
	| timezone          | varchar(128)         | NO   |     |         |                |
	| increment         | smallint(5) unsigned | NO   |     | NULL    |                |
	| min_duration      | smallint(5) unsigned | NO   |     | NULL    |                |
	+-------------------+----------------------+------+-----+---------+----------------+*/

	
	public void saveOrUpdateBillingCustomer(BillingCustomers customer){

    	session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        transaction.begin();

		session.saveOrUpdate(customer);
		
			transaction.commit();
			session.close();
	}

}

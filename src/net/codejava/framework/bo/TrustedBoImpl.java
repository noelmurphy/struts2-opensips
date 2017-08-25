package net.codejava.framework.bo;

import java.util.List;
import net.codejava.framework.dao.TrustedDAO;
import net.codejava.framework.model.Trusted;

public class TrustedBoImpl implements TrustedBo {

	TrustedDAO trustedDAO;
	
	//DI via Spring
	public void setTrustedDAO(TrustedDAO trustedDAO) {
		
		this.trustedDAO = trustedDAO;
	}
	
	@Override
	public List<Trusted> listTrusted() {
		// TODO Auto-generated method stub
		return trustedDAO.listTrusted();
	}
	
	//call DAO to save customer
	@Override
	public void addTrusted(Trusted trusted){
		
		trustedDAO.addTrusted(trusted);
		
	}

	/* */
	@Override
	public void saveOrUpdateTrusted(Trusted trusted) {
		
		trustedDAO.addTrusted(trusted);
		
	}

	@Override
	public Trusted listTrustedById(Long trustedId) {
		// TODO Auto-generated method stub
		return trustedDAO.listTrustedById(trustedId);
	}

	@Override
	public void deleteTrusted(Long trustedId) {
		
		trustedDAO.deleteTrusted(trustedId);
		
	}
	/* */
}

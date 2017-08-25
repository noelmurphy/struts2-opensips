package net.codejava.framework.dao;

 
import java.util.List;

import net.codejava.framework.model.Quota;
import net.codejava.framework.model.Trusted;
 
public interface TrustedDAO {
	
	List<Trusted> listTrusted();
	
	void addTrusted(Trusted trusted);
	
	/* */

	
	Trusted listTrustedById(Long trustedId);
	
	void deleteTrusted(Long trustedId);
	/* */



	void saveOrUpdateTrusted(Trusted trusted);
}
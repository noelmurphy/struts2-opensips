package net.codejava.framework.bo;

import java.util.List; 
import net.codejava.framework.model.Trusted;

public interface TrustedBo {
	
	List<Trusted> listTrusted();
	
	void addTrusted(Trusted trusted);
	
	/* */
	void saveOrUpdateTrusted(Trusted trusted);

	Trusted listTrustedById(Long trustedId);
	
	void deleteTrusted(Long trustedId);
	/* */
}

package net.codejava.framework.dao;

import java.util.List;


import net.codejava.framework.model.Blocked;


public interface BlockedDAO {

	List<Blocked> listBlocked();
	
	void addBlocked(Blocked blocked);
	
	void updateBlocked(Blocked blocked);
	
	void saveOrUpdateBlocked(Blocked blocked);
	
	Blocked listBlockedById(Long blockedId);
	
	void deleteBlocked(Long blockedId);
	
	List<Blocked> searchBlocked(Blocked blocked);
}

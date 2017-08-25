package net.codejava.framework.dao;

import java.util.List;

import net.codejava.framework.model.Quota;

public interface QuotaDAO {
	
	List<Quota> listQuota();
	
	void addQuota(Quota quota);
	
	void saveOrUpdateQuota(Quota quota);
	
	Quota listQuotaById(Long quotaId);
	
	void deleteQuota(Long quotaId);

}

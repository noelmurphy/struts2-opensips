package net.codejava.framework.dao;

import java.util.List;

import net.codejava.framework.model.DRRules;

public interface DRRulesDAO {

	List<DRRules> listDRRules();
	
	void addDRRules(DRRules drrules);
	
	void saveOrUpdateDRRules(DRRules drrules);
	
	DRRules listDRRulesById(Long drrulesId);
	
	void deleteDRRules(Long drrulesId);
}

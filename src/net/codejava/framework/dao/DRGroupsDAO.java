package net.codejava.framework.dao;

import java.util.List;

import net.codejava.framework.model.DRGroups;

public interface DRGroupsDAO {

	List<DRGroups> listDRGroups();
	
	void addDRGroups(DRGroups drgroup);
	
	void saveOrUpdateDRGroups(DRGroups drgroup);
	
	DRGroups listDRGroupsById(Long drgroupsId);
	
	void deleteDRGroups(Long drgroupsId);
}

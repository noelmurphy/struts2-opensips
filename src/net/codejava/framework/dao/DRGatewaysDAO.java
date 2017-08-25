package net.codejava.framework.dao;

import java.util.List;

import net.codejava.framework.model.DRGateways;

public interface DRGatewaysDAO {

	List<DRGateways> listDRGateways();
	
	void addDRGateways(DRGateways drgateways);
	
	void saveOrUpdateDRGateways(DRGateways drgateways);
	
	DRGateways listDRGatewaysById(Long drgatewaysId);
	
	void deleteDRGateways(Long drgatewaysId);
}
package net.codejava.framework.dao;

import java.util.List;

import net.codejava.framework.model.Activate;
import net.codejava.framework.model.Blocked;
import net.codejava.framework.model.Dialplan;

public interface DialplanDAO {
	
	List<Dialplan> listDialplan();
	
	void addDialplan(Dialplan dialplan);
	
	Dialplan listDialplanById(Long dialplanId);
	
	void deleteDialplan(Long dialplanId);

	void saveOrUpdateDialplan(Dialplan dialplan);
	List<Dialplan> searchDialplan(Dialplan dialplan);
}
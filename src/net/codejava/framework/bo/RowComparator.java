package net.codejava.framework.bo;

import java.util.Comparator;

import net.codejava.framework.model.Trusted;

public class RowComparator implements Comparator<Trusted> {

	@Override
	public int compare(Trusted t0, Trusted t1) {
		
		return (int) (t0.getId() - t1.getId());
	}

}

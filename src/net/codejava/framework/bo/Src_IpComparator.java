 package net.codejava.framework.bo;

import java.util.Comparator;

import net.codejava.framework.model.Trusted;

public class Src_IpComparator implements Comparator<Trusted> {

	@Override
	public int compare(Trusted t0, Trusted t1) {
		
		String mSrc_Ip_1 = t0.getSrc_ip().toUpperCase();
		String mSrc_Ip_2 = t1.getSrc_ip().toUpperCase();
		
		return mSrc_Ip_1.compareTo(mSrc_Ip_2);
	}

}
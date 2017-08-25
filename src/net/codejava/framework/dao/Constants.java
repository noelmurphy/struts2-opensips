package net.codejava.framework.dao;

public interface Constants {
    
	/** JNDI name of the datasource representing the master db */
	//public static final String DEFAULT_BBDD = "default.bbddd";
	public static final String DEFAULT_BBDD = "jdbc/opensips";
	
	/** Proxy configuration*/
	public static final String IS_PARALLEL = "parallel";
	//public static final String IS_PARALLEL = "true";
	public static final String IS_SUPERVISED = "supervised";
	//public static final String IS_SUPERVISED = "false";
	public static final String IS_RECORDROUTE = "record.route";
	//public static final String IS_RECORDROUTE = "false";
	public static final String TIMEOUT_SEQUENTIAL = "timeout.sequential";
	//public static final String TIMEOUT_SEQUENTIAL = "30";
	//A string to be stripped from the Request URI of the initial INVITE
    //in order to obtain the application prefix
	public static final String STRIP_PREFIX ="strip.prefix";
	
	public static final String CONFIG_FILE_NAME ="WEB-INF/config.ini";
	public static final String CMDOPENSIPS_FILE_NAME ="WEB-INF/CMDOpenSips.ini";
}
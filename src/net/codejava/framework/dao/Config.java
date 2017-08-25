package net.codejava.framework.dao;


import java.io.File;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class Config {

	// Dailplan
	/*
	private static final String CONFIG_Divert 				= "wsdivert.";
	private final static String CONFIG_Divert_ENDPOINT    	= CONFIG_Divert + "endpoint";
	private static final String CONFIG_Divert_DPID          = CONFIG_Divert + "dpid";
	private static final String CONFIG_Divert_PR      		= CONFIG_Divert + "pr";
	private static final String CONFIG_Divert_MATCH_OP      = CONFIG_Divert + "match_op";
	private static final String CONFIG_Divert_MATCH_EXP     = CONFIG_Divert + "match_exp";
	private static final String CONFIG_Divert_MATCH_LEN     = CONFIG_Divert + "match_len";
	private final static String CONFIG_Divert_SUBST_EXP     = CONFIG_Divert + "subst_exp";
	private final static String CONFIG_Divert_REPL_EXP      = CONFIG_Divert + "repl_exp";
	private final static String CONFIG_Divert_ATTRS      	= CONFIG_Divert + "attrs";
	// DRGoup
    private static final String CONFIG_Divert_UUID				= CONFIG_Divert	+ "uuid";
    private static final String CONFIG_Divert_USERNAME			= CONFIG_Divert + "username";
    private static final String CONFIG_Divert_DOMAIN			= CONFIG_Divert + "domain";
    private static final String CONFIG_Divert_GROUPID			= CONFIG_Divert + "groupid";
    private static final String CONFIG_Divert_GRPDESCRIPTION	= CONFIG_Divert + "grpdescription";
    */
    // Validation
    private static final String CONFIG_REGEX 				= "regex.";
    private static final String CONFIG_REGEX_IP				= CONFIG_REGEX + "ip";
    private static final String CONFIG_REGEX_TEST			= CONFIG_REGEX + "test";
    private static final String CONFIG_REGEX_PROT 			= CONFIG_REGEX + "proto";
    private static final String CONFIG_REGEX_DP_SUBMATCH = CONFIG_REGEX + "submatch"; 
    private static final String CONFIG_REGEX_DP_MATCH = CONFIG_REGEX + "match"; 
    
    private static final String CONFIG_CMDOPENSIPS	= "cmdopensips.";
    private static final String CONFIG_CMDOPENSIPS_RELOADDIALPLAN = CONFIG_CMDOPENSIPS + "reloaddialplan";
    private static final String CONFIG_CMDOPENSIPS_RELOADLCR = CONFIG_CMDOPENSIPS + "reloadlcr";
    private static final String CONFIG_CMDOPENSIPS_RELOADDOMAIN  = CONFIG_CMDOPENSIPS + "reloaddomain";
    private static final String CONFIG_CMDOPENSIPS_RELOADDROUTING = CONFIG_CMDOPENSIPS + "reloaddrouting"; 
    /*
     * private static final String CONFIG_CMDOPENSIPS_RELOADTRUSTED = CONFIG_CMDOPENSIPS + "reloadtrusted";
     * */

	private static Config INSTANCE;
	private static Configuration mConfiguration;
	private File mConfigFile;
	private boolean mInit = false;
	
	private Config() {

	}

	public static Config getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Config();
		}
		return INSTANCE;
	}

	public void setConfiguration(Configuration configuration) {
		mConfiguration = configuration;
	}

	public Configuration getConfiguration() {
		return mConfiguration;
	}

	public void setConfigurationFile(String file) throws ConfigurationException  {
		if (file == null) {
			throw new NullPointerException(
					"The file used to set the configuration cannot be null");
		}
		setConfigFile(new File(file));
		PropertiesConfiguration config = new PropertiesConfiguration(getConfigFile());
		config.setReloadingStrategy(new FileChangedReloadingStrategy());
		setConfiguration(config);
		setInit(true);
	}

	public void setInit(boolean init) {
		mInit = init;
	}

	public boolean isInit() {
		return mInit;
	}

	private void setConfigFile(File configFile) {
		mConfigFile = configFile;
	}

	private File getConfigFile() {
		return mConfigFile;
	}
/*
	public static String getConfigDivertEndpoint() {
		return CONFIG_Divert_ENDPOINT;
	}

	public static String getConfigDivertDpid() {
		return CONFIG_Divert_DPID;
	}

	public static String getConfigDivertPr() {
		return CONFIG_Divert_PR;
	}

	public static String getConfigDivertMatchOp() {
		return CONFIG_Divert_MATCH_OP;
	}

	public static String getConfigDivertMatchLen() {
		return CONFIG_Divert_MATCH_LEN;
	}

	public static String getConfigDivertSubstExp() {
		return CONFIG_Divert_SUBST_EXP;
	}

	public static String getConfigDivertReplExp() {
		return CONFIG_Divert_REPL_EXP;
	}

	public static String getConfigDivertAttrs() {
		return CONFIG_Divert_ATTRS;
	}

	public static String getConfigDivertMatchExp() {
		return CONFIG_Divert_MATCH_EXP;
	}

	public static String getConfigDivertUuid() {
		return CONFIG_Divert_UUID;
	}

	public static String getConfigDivertUsername() {
		return CONFIG_Divert_USERNAME;
	}

	public static String getConfigDivertDomain() {
		return CONFIG_Divert_DOMAIN;
	}

	public static String getConfigDivertGroupid() {
		return CONFIG_Divert_GROUPID;
	}

	public static String getConfigDivertGrpdescription() {
		return CONFIG_Divert_GRPDESCRIPTION;
	}
*/
	public static String getConfigRegexIp() {
		return mConfiguration.getString(CONFIG_REGEX_IP);
	}

	public static String getConfigRegexTest() {
		return mConfiguration.getString(CONFIG_REGEX_TEST);
	}

	public static String getConfigRegexProt() {
		return mConfiguration.getString(CONFIG_REGEX_PROT);
	}
	
	public static String getConfigRegexMatch(){
		return mConfiguration.getString(CONFIG_REGEX_DP_MATCH);
	}
	
	public static String getConfigRegexSubMatch(){
		return mConfiguration.getString(CONFIG_REGEX_DP_SUBMATCH);
	}
	
	public static String getConfigReloadDialplan(){
		return mConfiguration.getString(CONFIG_CMDOPENSIPS_RELOADDIALPLAN);
	}
	public static String getConfigReloadLCR(){
		return mConfiguration.getString(CONFIG_CMDOPENSIPS_RELOADLCR);
	}
	public static String getConfigReloadDomain(){
		return mConfiguration.getString(CONFIG_CMDOPENSIPS_RELOADDOMAIN);
	}
	public static String getConfigReloadRouting(){
		return mConfiguration.getString(CONFIG_CMDOPENSIPS_RELOADDROUTING);
	}
	
/*	public static String getConfigReloadTrusted(){
		return mConfiguration.getString(CONFIG_CMDOPENSIPS_RELOADTRUSTED);
	}*/
	
	
	
}

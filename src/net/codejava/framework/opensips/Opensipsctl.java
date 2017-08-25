package net.codejava.framework.opensips;
import java.io.*;


import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.codejava.framework.dao.ActivateDAOImpl;
import net.codejava.framework.dao.Config;
import net.codejava.framework.dao.Constants;
import net.codejava.framework.action.TrustedAction;


public class Opensipsctl implements Constants {
	
	private final static Logger cat = LoggerFactory.getLogger(Opensipsctl.class);
	ActivateDAOImpl activateDAO = new ActivateDAOImpl();
    protected Logger getLogger (){
    	return cat;
    }	
    

	public void allReload(String configLocation) {
		/*
		 * set config file location and call reloads separately
		 * thread.sleep for delay to prevent crashing between reloads
		 * for some reason, fetching contextPath from this level
		 * returns null, therefore
		 * fetched from action and pushed here
		 * String contextPath = context.getRealPath(File.separator);
		 * String configfile = contextPath + "/" + CMDOPENSIPS_FILE_NAME;
		 * 
		 */
		dialplanReload(configLocation);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		domainReload(configLocation);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LCRReload(configLocation);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		routingReload(configLocation);
		
		activateDAO.setActivate("0");

	}
	
	/*
	 * configLocation passed from action
	 */
	public String dialplanReload(String configLocation) {		
		Config config = Config.getInstance();
		String s="";
		/*
		 * set config file from configLocation
		 */
		try 
		{
			config.setConfigurationFile(configLocation);
		} 
		catch (ConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * fetchreloadomain command
		 */
		String reloadDialplan = Config.getConfigReloadDialplan();
		cat.info("CMDOPENSIPS configfile path name value is : " + configLocation);
		cat.info("reloadomain command is: " +reloadDialplan);
		
		
		try {
			cat.info("reloadomain command is: " +reloadDialplan);
            Process p = Runtime.getRuntime().exec(reloadDialplan);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the reload dialplan command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the reload dialplan command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		activateDAO.setActivate("0");
		return s;
	}

	public String domainReload(String configLocation) 
	{
		Config config = Config.getInstance();
		String s="";
		/*
		 * set config file from configLocation
		 */
		try 
		{
			config.setConfigurationFile(configLocation);
		} 
		catch (ConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * fetch reloadomain command
		 */
		String reloadDomain = Config.getConfigReloadDomain();
		cat.info("CMDOPENSIPS configfile path name value is : " + configLocation);
		cat.info("reloadDRrouting command is: " + reloadDomain);
		
		
		try {
			cat.info("reloadomain command is: " +reloadDomain);
            Process p = Runtime.getRuntime().exec(reloadDomain);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the reload dialplan command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the reload dialplan command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

		} 
		
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public String LCRReload(String configLocation) 
	{
		Config config = Config.getInstance();
		String s="";
		/*
		 * set config file from configLocation
		 */
		try 
		{
			config.setConfigurationFile(configLocation);
		} 
		catch (ConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * fetch reloadomain command
		 */
		String reloadLCR = Config.getConfigReloadLCR();
		cat.info("CMDOPENSIPS configfile path name value is : " + configLocation);
		cat.info("reloadDRrouting command is: " + reloadLCR);
		
		
		try {
			cat.info("reloadomain command is: " +reloadLCR);
            Process p = Runtime.getRuntime().exec(reloadLCR);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the reload dialplan command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the reload dialplan command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

		} 
		
		
		
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
		
	}
	
	
	public String routingReload(String configLocation) 
	{
		Config config = Config.getInstance();
		String s="";
		/*
		 * set config file from configLocation
		 */
		try 
		{
			config.setConfigurationFile(configLocation);
		} 
		catch (ConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * fetch reloadRouting command
		 */
		String reloadRouting = 	Config.getConfigReloadRouting();
		cat.info("CMDOPENSIPS configfile path name value is : " + configLocation);
		cat.info("reloadDRrouting command is: " + reloadRouting);
		
		
		try {
			cat.info("reloadomain command is: " +reloadRouting);
            Process p = Runtime.getRuntime().exec(reloadRouting);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the reload dialplan command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the reload dialplan command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

		} 
		
		
		
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
		
	}
}


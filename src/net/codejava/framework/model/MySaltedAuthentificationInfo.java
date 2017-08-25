package net.codejava.framework.model;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class MySaltedAuthentificationInfo implements SaltedAuthenticationInfo {


	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String username;
	  private final String password;
	  private final String salt;

	  public MySaltedAuthentificationInfo(String username, String password, String salt) {
	    this.username = username;
	    this.password = password;
	    this.salt = salt;
	  }

	  @Override
	  public PrincipalCollection getPrincipals() {
	    PrincipalCollection coll = new SimplePrincipalCollection(username, username);
	    return coll;
	  }

	  @Override
	  public Object getCredentials() {
	    return password;
	  }

	  @Override
	  public ByteSource getCredentialsSalt() {
	    try {
			return  new SimpleByteSource(Base64.decode(salt));
		} catch (Base64DecodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return null;
	  }

	}
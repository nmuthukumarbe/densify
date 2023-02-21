/**
 * 
 */
package com.walmart.densify.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;


/**
 * @author m0n00qb
 *
 */
public class LoggedInUser extends org.springframework.security.core.userdetails.User{

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public LoggedInUser(java.lang.String username, java.lang.String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, AppUser appUser) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		if(appUser!=null) {
			this.appUser = appUser;
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppUser appUser;
}

/**
 * 
 */
package com.walmart.densify.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.walmart.densify.model.LoggedInUser;


/**
 * @author m0n00qb
 *
 */
@Component
public class UserInfo {

	public LoggedInUser get() {
		LoggedInUser loggedInAppUser = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof LoggedInUser) {
			loggedInAppUser = (LoggedInUser) principal;
		}
		return loggedInAppUser;
	}

}

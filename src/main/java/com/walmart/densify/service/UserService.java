/**
 * 
 */
package com.walmart.densify.service;

import com.walmart.densify.model.AppUser;
import com.walmart.densify.model.RestResponse;

/**
 * @author m0n00qb
 *
 */
public interface UserService {

	RestResponse save(AppUser user);

    AppUser findByMobile(String mobile);
}

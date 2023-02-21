/**
 * 
 */
package com.walmart.densify.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.walmart.densify.model.AppUser;
import com.walmart.densify.model.LoggedInUser;
import com.walmart.densify.model.RestResponse;
import com.walmart.densify.repository.AppUserRepository;
import com.walmart.densify.service.UserInfo;
import com.walmart.densify.service.UserService;


/**
 * @author m0n00qb
 *
 */

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	HttpServletRequest request;

	@Autowired
	private UserInfo userInfo;

	/*@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}*/

	@Override
	public RestResponse save(AppUser appUser) {
		RestResponse restResponse = null;
		if (appUser == null) {
			return restResponse;
		}
		preprocess(appUser);
		try {
			restResponse = new RestResponse();
			appUser = userRepository.save(appUser);
			restResponse.setResponseType(RestResponse.ResponseType.SUCCESS);
			restResponse.setMessage("User Information Saved Successfully");
		} catch (DataIntegrityViolationException ex) {
			ex.printStackTrace();
			restResponse.setResponseType(RestResponse.ResponseType.FAILURE);
			restResponse.setMessage("Phone Number already Exists!");
		} catch (Exception e) {
			e.printStackTrace();
			restResponse.setResponseType(RestResponse.ResponseType.FAILURE);
			restResponse.setMessage("Problem in Saving User Information, Contact Support Team!");
		}
		return restResponse;
	}

	private void preprocess(AppUser appUser) {
		if (appUser.getId() != null && appUser.getId() > 0) {
			Optional<AppUser> appUserPreviousData = userRepository.findById(appUser.getId());
			if (StringUtils.isEmpty(appUser.getPassword())) {
				appUser.setPassword(appUserPreviousData.get().getPassword());
			} else {
				appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
			}
			appUser.setUpdatedAt(new Date());
			appUser.setCreatedAt(appUserPreviousData.get().getCreatedAt());
		} else {
			appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
			appUser.setUpdatedAt(new Date());
			appUser.setCreatedAt(new Date());
			LoggedInUser loggedInAppUser = userInfo.get();
		}

	}

	@Override
	public AppUser findByMobile(String mobile) {
		return userRepository.findByMobile(mobile);
	}

	public AppUser findById(AppUser user) {
		if (user.getId() != null && user.getId() > 0) {
			Optional<AppUser> appUser = userRepository.findById(user.getId());
			if (appUser != null) {
				return appUser.get();
			}
		}
		return null;
	}

	public List<AppUser> findAppUsersByNameAndMobile(AppUser appUser) {
		Pageable pageable = PageRequest.of(0, 20);

		return userRepository.findAppUsers(appUser.getName(), appUser.getMobile(), pageable);
	}

	@Override
	public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
		AppUser user = userRepository.findByMobile(mobile);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		// .withUserDetails(user)
		// return
		// User.withUsername(user.getMobile()).password(user.getPassword()).roles(UserRole.getName(user.getRole())).build();
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		return new LoggedInUser(user.getMobile(), user.getPassword(), true, true, true, true, authorities, user);
	}

	public RestResponse delete(AppUser appUser) {
		RestResponse restResponse = null;
		try {
			restResponse = new RestResponse();
			userRepository.delete(appUser);
			restResponse.setResponseType(RestResponse.ResponseType.SUCCESS);
			restResponse.setMessage("User Information Saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			restResponse.setResponseType(RestResponse.ResponseType.FAILURE);
			restResponse.setMessage("Problem in Saving User Information, Contact Support Team!");
		}
		return restResponse;
	}
	
}
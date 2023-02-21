/**
 * 
 */
package com.walmart.densify.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.walmart.densify.model.AppUser;
/**
 * @author m0n00qb
 *
 */
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    @Query(value = "SELECT au.* from app_user au where au.name like %:name% and au.mobile like %:mobile% "
    		, nativeQuery = true)
    List<AppUser> findAppUsers(String name, String mobile, Pageable pageable);
    
	AppUser findByMobile(String mobile);
    
    
}

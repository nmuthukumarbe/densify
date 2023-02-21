/**
 * 
 */
package com.walmart.densify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.walmart.densify.model.Team;
/**
 * @author m0n00qb
 *
 */
public interface TeamRepository extends CrudRepository<Team, Long> {

	@Query(value = "SELECT cu.* from team cu", nativeQuery = true)
    List<Team> findAll();
}


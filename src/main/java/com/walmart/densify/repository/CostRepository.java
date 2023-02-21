/**
 * 
 */
package com.walmart.densify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.walmart.densify.model.Cost;
/**
 * @author m0n00qb
 *
 */
public interface CostRepository extends CrudRepository<Cost, Long> {

	@Query(value = "SELECT cu.* from cost cu where cu.team_id= :teamId", nativeQuery = true)
    List<Cost> findAllByTeamId(long teamId);
}


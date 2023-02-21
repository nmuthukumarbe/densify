/**
 * 
 */
package com.walmart.densify.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.walmart.densify.model.Cost;
import com.walmart.densify.model.RestResponse;
import com.walmart.densify.model.Team;
import com.walmart.densify.repository.CostRepository;
import com.walmart.densify.repository.TeamRepository;

/**
 * @author m0n00qb
 *
 */

@Service
public class TeamServiceImpl {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private CostRepository costRepository;

	Gson gson = new Gson();

	/*
	 * @Bean public RequestContextListener requestContextListener() { return new
	 * RequestContextListener(); }
	 */

	public RestResponse save(Team team) {
		Team appTeam = team;
		RestResponse restResponse = null;
		if(team==null) {
			restResponse = new RestResponse();
			restResponse.setResponseType(RestResponse.ResponseType.FAILURE);
			restResponse.setMessage("Team Information not saved ");
			return restResponse;
		}
		/*if(team.getId()>0) {
			//appTeam = findById(team.getId());
		} else {
			appTeam = team;
		}*/
		
		if (appTeam == null) {
			return restResponse;
		}
		preprocess(appTeam);
		try {
			restResponse = new RestResponse();
			appTeam = teamRepository.save(appTeam);
			restResponse.setResponseType(RestResponse.ResponseType.SUCCESS);
			restResponse.setMessage("Team Information Saved Successfully");
		} catch (DataIntegrityViolationException ex) {
			ex.printStackTrace();
			restResponse.setResponseType(RestResponse.ResponseType.FAILURE);
			restResponse.setMessage("Phone Number already Exists!");
		} catch (Exception e) {
			e.printStackTrace();
			restResponse.setResponseType(RestResponse.ResponseType.FAILURE);
			restResponse.setMessage("Problem in Saving Team Information, Contact Support Team!");
		}
		return restResponse;
	}

	private void preprocess(Team appTeam) {

	}

	public Team findById(long id) {
		return teamRepository.findById(id).get();
	}

	public List<Team> getTeams() {
		List<Team> appTeams = teamRepository.findAll();
		for(Team team: appTeams) {
			List<Cost> costList = costRepository.findAllByTeamId(team.getId());
			if(!CollectionUtils.isEmpty(costList)) {
				Cost latestCost = costList.get(costList.size()-1);
				team.setCost(latestCost.getWcnp()+latestCost.getCassandra()+latestCost.getMls());
			}
		}
		
		return appTeams;
	}

}
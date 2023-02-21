/**
 * 
 */
package com.walmart.densify.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.walmart.densify.model.Cost;
import com.walmart.densify.model.RestResponse;
import com.walmart.densify.repository.CostRepository;

/**
 * @author m0n00qb
 *
 */

@Service
public class CostServiceImpl {

	@Autowired
	private CostRepository costRepository;

	Gson gson = new Gson();

	/*
	 * @Bean public RequestContextListener requestContextListener() { return new
	 * RequestContextListener(); }
	 */

	public RestResponse save(Cost cost) {
		RestResponse restResponse = null;
		if(cost==null) {
			restResponse = new RestResponse();
			restResponse.setResponseType(RestResponse.ResponseType.FAILURE);
			restResponse.setMessage("Cost Information not saved ");
			return restResponse;
		}
		preprocess(cost);
		try {
			restResponse = new RestResponse();
			cost = costRepository.save(cost);
			restResponse.setResponseType(RestResponse.ResponseType.SUCCESS);
			restResponse.setMessage("Cost Information Saved Successfully");
		} catch (DataIntegrityViolationException ex) {
			ex.printStackTrace();
			restResponse.setResponseType(RestResponse.ResponseType.FAILURE);
			restResponse.setMessage("Phone Number already Exists!");
		} catch (Exception e) {
			e.printStackTrace();
			restResponse.setResponseType(RestResponse.ResponseType.FAILURE);
			restResponse.setMessage("Problem in Saving Cost Information, Contact Support Cost!");
		}
		return restResponse;
	}

	private void preprocess(Cost appCost) {

	}

	public Cost findById(long id) {
		return costRepository.findById(id).get();
	}

	public List<Cost> getCosts(long teamId) {
		List<Cost> appCosts = costRepository.findAllByTeamId(teamId);
		for(Cost cost : appCosts) {
			cost.setWcnp(cost.getIngestion()+cost.getSfnsf());
			cost.setMonth(cost.getMonth()+" "+cost.getYear());
			cost.setCost(cost.getWcnp()+cost.getCassandra()+cost.getMls());
		}
		return appCosts;
	}

}
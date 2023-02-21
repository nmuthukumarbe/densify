package com.walmart.densify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walmart.densify.model.Cost;
import com.walmart.densify.model.RestResponse;
import com.walmart.densify.service.impl.CostServiceImpl;



@Controller
@RequestMapping("cost")
public class CostController {

	@Autowired
	private CostServiceImpl costServiceImpl;
	
	@RequestMapping("add")
	public String showAddCost(final Model model, @RequestBody(required = false) Cost cost) {

		if(cost!=null && cost.getId()!=null && cost.getId()>0) {
			cost = costServiceImpl.findById(cost.getId());
			model.addAttribute("cost", cost);
		}
		return "cost-add";
	}

	@RequestMapping("view")
	public String viewCost(final Model model, @RequestBody(required = false) Cost cost) {

		cost = costServiceImpl.findById(cost.getId());
		model.addAttribute("cost", cost);
		return "cost-view";
	}

	@PutMapping("")
	public ResponseEntity<Object> saveCost(final Model model, @RequestBody(required = false) Cost cost) {

		RestResponse restResponse = costServiceImpl.save(cost);
		return new ResponseEntity<>(restResponse, HttpStatus.OK);
	}

	@RequestMapping("")
	public String searchCost(final Model model, @RequestBody(required = false) Cost cost) {
		
		List<Cost> costs = getCosts(cost);
		model.addAttribute("costList", costs);
		return "cost-list";
	}

	private List<Cost> getCosts(Cost cost) {
		if(cost==null) {
			cost = new Cost();
		}
		List<Cost> costs = costServiceImpl.getCosts(cost.getTeam().getId());
		return costs;
	}

	@RequestMapping("/analysis")
	public String searchCostAnalysis(final Model model, @RequestBody(required = false) Cost cost) {
		
		List<Cost> costs = getCosts(cost);
		model.addAttribute("costList", costs);
		model.addAttribute("latestCost", costs.get(costs.size()-1));
		return "cost-analysis";
	}

}
package com.walmart.densify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walmart.densify.model.Cost;
import com.walmart.densify.service.impl.CostServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private CostServiceImpl costServiceImpl;

	@RequestMapping("/")
	public String index(final Model model) {
		
		//LoggedInUser loggedInAppUser = userInfo.get();
		//AppUser appUser = loggedInAppUser.getAppUser();
		//model.addAttribute("loggedInUser", appUser);
		//model.addAttribute("companyName",companyServiceImpl.findById(appUser.getCmpId()).getName());
		
		List<Cost> costs = getCosts();
		model.addAttribute("costList", costs);
		model.addAttribute("latestCost", costs.get(costs.size()-1));
		
		return "dashboard";
	}
	
	@RequestMapping("/login")
	public String login(final Model model) {
		//model.addAttribute("companyList", companyServiceImpl.findAllCompany());
		return "login";
	}

	private List<Cost> getCosts() {
		List<Cost> costs = costServiceImpl.getCosts(1);
		return costs;
	}
	
}
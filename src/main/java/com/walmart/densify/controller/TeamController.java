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

import com.walmart.densify.model.RestResponse;
import com.walmart.densify.model.Team;
import com.walmart.densify.service.impl.TeamServiceImpl;



@Controller
@RequestMapping("team")
public class TeamController {

	@Autowired
	private TeamServiceImpl teamServiceImpl;

	@RequestMapping("add")
	public String showAddTeam(final Model model, @RequestBody(required = false) Team team) {

		if(team!=null && team.getId()!=null && team.getId()>0) {
			team = teamServiceImpl.findById(team.getId());
			model.addAttribute("team", team);
		}
		return "team-add";
	}

	@RequestMapping("view")
	public String viewTeam(final Model model, @RequestBody(required = false) Team team) {

		team = teamServiceImpl.findById(team.getId());
		model.addAttribute("team", team);
		return "team-view";
	}

	@PutMapping("")
	public ResponseEntity<Object> saveTeam(final Model model, @RequestBody(required = false) Team team) {

		RestResponse restResponse = teamServiceImpl.save(team);
		return new ResponseEntity<>(restResponse, HttpStatus.OK);
	}

	@RequestMapping("")
	public String searchTeam(final Model model, @RequestBody(required = false) Team team) {
		
		List<Team> teams = getTeams(team);
		model.addAttribute("teamList", teams);
		return "team-list";
	}

	private List<Team> getTeams(Team team) {
		if(team==null) {
			team = new Team();
		}
		List<Team> teams = teamServiceImpl.getTeams();
		return teams;
	}
	
}
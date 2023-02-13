package com.moaaz.bug.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.moaaz.bug.model.Bug;
import com.moaaz.bug.service.AdminService;

@RestController
@RequestMapping("/Admin")
public class AdminViewBugController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/allBugs")
	@ResponseStatus(HttpStatus.OK)
	public String viewAllBugs(ModelMap model) {
		model.addAttribute("bugs", adminService.getAllBugs());
		return "adminViewAllBugs";

	}

	@GetMapping("/getAllBugs")
	@ResponseStatus(HttpStatus.OK)
	public List<Bug> getAllBugs() {
		return adminService.getAllBugs();
	}
}

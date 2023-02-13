package com.moaaz.bug.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moaaz.bug.model.Admin;
import com.moaaz.bug.model.Bug;
import com.moaaz.bug.model.Developer;
import com.moaaz.bug.model.Project;
import com.moaaz.bug.model.Tester;
import com.moaaz.bug.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private BugService bugService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private DeveloperService developerService;
	@Autowired
	private TesterService testerService;

	public Admin getAdminByEmailAndPassword(String email, String password) {
		return adminRepo.findByEmailAndPassword(email, password);
	}

	public ArrayList<Bug> getAllBugs() {
		return (ArrayList<Bug>) bugService.getAllBugs();
	}

	public ArrayList<Developer> getAllDevelopers() {
		return (ArrayList<Developer>) developerService.getAllDevelopers();
	}

	public ArrayList<Tester> getAllTesters() {
		return (ArrayList<Tester>) testerService.getAllTesters();
	}

	public void addUser(Developer developer) {
		developerService.addDeveloper(developer);
	}

	public void addUser(Tester tester) {
		testerService.addTester(tester);
	}

	public void updateUser(Developer developer) {
		developerService.updateDeveloper(developer);
	}

	public void updateUser(Tester tester) {
		testerService.updateTester(tester);
	}

	public void deleteDeveloper(int id) {
		developerService.deleteDeveloper(id);
	}

	public void deleteTester(int id) {
		testerService.deleteTesterById(id);
	}

	/* Projects */
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}

	public void addProject(Project project) {
		projectService.addProject(project);
	}

	public void updateProject(Project project) {
		projectService.updateProject(project);
	}

	public void deleteProject(int id) {
		projectService.deleteProject(id);
	}

	public Project getProjectById(int id) {
		return projectService.getProjectById(id);
	}

	public Admin addAdmin(Admin admin){
		return adminRepo.save(admin);
	}


	public Admin getAdminByEmail(String email){
		return adminRepo.findByEmail(email);
	}

	/************************* End Of Projects **********************/
}

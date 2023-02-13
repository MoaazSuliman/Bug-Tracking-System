package com.moaaz.bug.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moaaz.bug.model.Project;
import com.moaaz.bug.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepo;

	public List<Project> getAllProjects() {
		return projectRepo.findAll();
	}

	public void addProject(Project project) {
		projectRepo.save(project);
	}

	public void updateProject(Project project) {
		projectRepo.save(project);
	}

	public void deleteProject(int id) {
		projectRepo.deleteById(id);
	}

	public Project getProjectById(int id) {
		return projectRepo.findById(id).orElse(null);
	}
}

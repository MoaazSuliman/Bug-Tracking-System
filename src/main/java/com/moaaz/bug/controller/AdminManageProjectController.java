package com.moaaz.bug.controller;

import com.moaaz.bug.model.Developer;
import com.moaaz.bug.model.Project;
import com.moaaz.bug.model.Tester;
import com.moaaz.bug.service.AdminService;
import com.moaaz.bug.service.DeveloperService;
import com.moaaz.bug.service.ProjectService;
import com.moaaz.bug.service.TesterService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Admin")
public class AdminManageProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private TesterService testerService;

//    @PostMapping("/addProject")
//    public String addProjectPost(@RequestParam String name, @RequestParam String description, @RequestBody Developer developer, @RequestBody Tester tester, @RequestParam String startDate, @RequestParam String endDate) {
//        Project project = new Project(0, name, description, startDate, endDate, ProjectStatus.InProgress, developer, tester, null);
//        adminService.addProject(project);
//        return "Done";
////		return "redirect:/adminProjects";
//    }

    @PostMapping("/addProject/developer/{developer_id}/tester/{tester_id}")
    public ResponseEntity<Object> addProject(@RequestBody @Valid Project project, @PathVariable int developer_id, @PathVariable int tester_id) throws MethodArgumentNotValidException {
        Developer developer = developerService.getDeveloperById(developer_id);
        if (developer == null)
            return new ResponseEntity<>("There is not Developer with id = " + developer_id, HttpStatus.BAD_REQUEST);
        Tester tester = testerService.getTesterById(tester_id);
        if (tester == null)
            return new ResponseEntity<>("There is not Tester with id = " + tester_id, HttpStatus.BAD_REQUEST);
        project.setDeveloper(developer);
        project.setTester(tester);
        adminService.addProject(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<>(adminService.getAllProjects(), HttpStatus.OK);
    }


    @PutMapping("/updateProject/developer/{developer_id}/tester/{tester_id}")
    public ResponseEntity<Object> updateProject(@RequestBody @Valid @NotNull Project project, @PathVariable int developer_id, @PathVariable int tester_id) {
        if (projectService.getProjectById(project.getId()) == null)
            return new ResponseEntity<>("There Are No Project With Id =" + project.getId(), HttpStatus.BAD_REQUEST);
        Developer developer = developerService.getDeveloperById(developer_id);
        if (developer == null)
            return new ResponseEntity<>("There is not Developer with id = " + developer_id, HttpStatus.BAD_REQUEST);
        Tester tester = testerService.getTesterById(tester_id);
        if (tester == null)
            return new ResponseEntity<>("There is not Tester with id = " + tester_id, HttpStatus.BAD_REQUEST);
        project.setDeveloper(developer);
        project.setTester(tester);
        projectService.updateProject(project);
        return new ResponseEntity<>("Updated Success...", HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable int id) {
        Project project = projectService.getProjectById(id);
        if (project == null)
            return new ResponseEntity<>("There Are No Project Has This ID " + id, HttpStatus.BAD_REQUEST);
        adminService.deleteProject(id);
        return new ResponseEntity<>("Deleted Success...", HttpStatus.ACCEPTED);
    }



}

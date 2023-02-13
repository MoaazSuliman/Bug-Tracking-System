package com.moaaz.bug.controller;


import com.moaaz.bug.model.*;
import com.moaaz.bug.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Tester")
public class TesterController {

    @Autowired
    private TesterService testerService;

    @Autowired
    private BugService bugService;
    @Autowired
    private ProjectService projectService;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/getAllBugs/{tester_id}")
    public ResponseEntity<Object> getAllBugsForTester(@PathVariable int tester_id) {
        Tester tester = testerService.getTesterById(tester_id);
        if (tester == null)
            return new ResponseEntity<>("There Are No Tester With id =" + tester_id, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(tester.getBugs(), HttpStatus.OK);
    }

    @GetMapping("/getAllMessages/{tester_id}")
    public ResponseEntity<Object> getAllMessages(@PathVariable int tester_id) {
        Tester tester = testerService.getTesterById(tester_id);
        if (tester == null)
            return new ResponseEntity<>("There Are No Tester Have Id = " + tester_id, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(tester.getMessages(), HttpStatus.OK);
    }


    @PostMapping("/addBug/tester/{tester_id}/developer/{developer_id}/project/{project_id}")
    public ResponseEntity<Object> addBug(@RequestBody @Valid Bug bug, @PathVariable int tester_id, @PathVariable int developer_id, @PathVariable int project_id) {
        System.out.println(bug.getPriority() + "************************************************************************************************************************");
        Tester tester = testerService.getTesterById(tester_id);
        if (tester == null)
            return new ResponseEntity<>("There Are No Tester With Id = " + tester_id, HttpStatus.BAD_REQUEST);
        Developer developer = developerService.getDeveloperById(developer_id);
        if (developer == null)
            return new ResponseEntity<>("There Are No Developer With Id = " + developer_id, HttpStatus.BAD_REQUEST);
        Project project = projectService.getProjectById(project_id);
        if (project == null)
            return new ResponseEntity<>("There Are No Project With Id = " + project_id, HttpStatus.BAD_REQUEST);
        // Everything are ok let's start.
        // 1- Save bug.
        bug.setTester(tester);
        bug.setDeveloper(developer);
        bug.setProject(project);
        bugService.addBug(bug);
        // 2- Add bonus for tester and update him.
        tester.setBonus(tester.getBonus() + 1);
//        tester.addBugToTesterBugs(bug);
        testerService.updateTester(tester);
        // 3- Create message and Send it for developer.
        Message message = new Message(1, "Bug " + bug.getName() + " had assigned to you by " + tester.getName(), tester_id);
        messageService.saveMessage(message);
        developer.addMessageToDeveloperMessages(message);
//        developer.addBugToDeveloperBugs(bug);
        developerService.updateDeveloper(developer);
        return new ResponseEntity<>(bug, HttpStatus.CREATED);
    }

    @PutMapping("/updateBug")
    public ResponseEntity<Object> updateBug(@RequestBody @Valid Bug bug) {
        if (bugService.getBugById(bug.getId()) == null)
            return new ResponseEntity<>("There are no bug with id = " + bug.getId(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(bug, HttpStatus.ACCEPTED);
    }
    //    @GetMapping("/testerNewBug/{tester_id}")
//    public String newBug(ModelMap model, @PathVariable int tester_id) {
//        model.addAttribute("projects", projectService.getAllProjects());
//        model.addAttribute("developers", developerService.getAllDevelopers());
//        model.addAttribute("tester", tester_id);
//        return "testerNewBug";
//    }


//	@PostMapping("/testerNewBug/{tester_id}")
//	public String addBug(@RequestParam String name, @RequestParam String description, @RequestParam String type,
//			@RequestParam int priority, @RequestParam int level, @RequestParam String source,
//			@RequestParam int developer_id, @PathVariable int tester_id, @RequestParam int project_id) {
//		Tester tester = testerService.getTesterById(tester_id);
//		BugPriority p;
//		if (priority == 0)
//			p = BugPriority.High;
//		else if (priority == 1)
//			p = BugPriority.Medium;
//		else
//			p = BugPriority.Low;
//
//		Developer developer = developerService.getDeveloperById(developer_id);
//		Bug bug = new Bug(0, name, description, type, p, level, "Date In Service ", BugStatus.Open, source, developer,
//				tester, projectService.getProjectById(project_id));
//		// 1- save bug
//		bugService.addBug(bug);
//		// 2- assign bug to tester and developer.
//		tester.addBugForTester(bug);
//		developer.addBugForDeveloper(bug);
//		// 3- update bonus
//		tester.setBonus(tester.getBonus() + 1);
//		// 4- Save Message .
//		Message message = new Message(0, tester.getName() + " Assign to You This Bug : " + bug.getName(),
//				tester.getName());
//		messageService.saveMessage(message);
//		// 5-  ٍSend Message To Developer.
//		developer.addMessageToDeveloperMessages(message);
//
//		// save changes for developer and tester in database.
//		developerService.updateDeveloper(developer);
//		testerService.updateTester(tester);
//		return "redirect:/testerHome";
//	}

    @DeleteMapping("deleteBug/{bug_id}")
    public ResponseEntity<Object> deleteBug(@PathVariable int bug_id) {
        Bug bug = bugService.getBugById(bug_id);
        if (bug == null) return new ResponseEntity<>("There Are NO Bug With Id= " + bug_id, HttpStatus.BAD_REQUEST);

        bugService.deleteBugById(bug_id);
        return new ResponseEntity<>("Deleted Bug Success...", HttpStatus.ACCEPTED);
    }

}

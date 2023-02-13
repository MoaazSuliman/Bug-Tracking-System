package com.moaaz.bug.controller;


import com.moaaz.bug.model.Bug;
import com.moaaz.bug.model.Developer;
import com.moaaz.bug.model.Message;
import com.moaaz.bug.model.Tester;
import com.moaaz.bug.model.types.BugStatus;
import com.moaaz.bug.service.BugService;
import com.moaaz.bug.service.DeveloperService;
import com.moaaz.bug.service.MessageService;
import com.moaaz.bug.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Developer")
public class DeveloperController {

    @Autowired
    TesterService testerService;
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private BugService bugService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/getAllBugs/{developer_id}")
    public ResponseEntity<Object> getAllBugsForDeveloper(@PathVariable int developer_id) {
        Developer developer = developerService.getDeveloperById(developer_id);
        if (developer == null) return new ResponseEntity<>("Not Found Developer", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(developer.getBugs(), HttpStatus.OK);
    }

    @GetMapping("/getAllMessages/{developer_id}")
    public ResponseEntity<Object> getAllMessagesForDeveloper(@PathVariable int developer_id) {
        Developer developer = developerService.getDeveloperById(developer_id);
        return (developer == null) ? new ResponseEntity<>("There Are No Developer With id " + developer_id, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(developer.getMessages(), HttpStatus.OK);
    }

    @PutMapping("/developerCloseBug/developer/{developer_id}/bug/{bug_id}")
    public ResponseEntity<Object> closeBug(@PathVariable int developer_id, @PathVariable int bug_id) {
        Developer developer = developerService.getDeveloperById(developer_id);
        if (developer == null)
            return new ResponseEntity<>("There Are No Developer Has This Id " + developer_id, HttpStatus.NOT_FOUND);
        Bug bug = bugService.getBugById(bug_id);
        if (bug == null) return new ResponseEntity<>("There Are No Bug Has This Id " + bug_id, HttpStatus.NOT_FOUND);
        if (bug.getDeveloper().getId() != developer_id)
            return new ResponseEntity<>("This Bug Isn't For This Developer", HttpStatus.BAD_REQUEST);

        bug.setStatus(BugStatus.Close);
        bugService.updateBug(bug);
        // add bonus for developer because he solved bug.
        developer.setBonus(developer.getBonus() + 1);
        developerService.updateDeveloper(developer);

        // Send Message To Tester To Know That This Developer Is Solved This Bug.
        Message message = new Message(0, "The Bug With Name " + bug.getName() + " Had Been Solved By " + developer.getName(), developer.getId());
        messageService.saveMessage(message);
        Tester tester = bug.getTester();
        tester.addMessageToTesterMessages(message);
        testerService.updateTester(tester);

        return new ResponseEntity<>(bug, HttpStatus.ACCEPTED);

    }

    @PutMapping("/developerUpdateBug/developer/{developer_id}")
    public ResponseEntity<Object> updateBug(@RequestBody Bug bug, @PathVariable int developer_id) {
        if (bugService.getBugById(bug.getId()) == null)
            return new ResponseEntity<>("There Are No Bug With id " + bug.getId(), HttpStatus.BAD_REQUEST);
        Developer developer = developerService.getDeveloperById(developer_id);
        if (developer == null)
            return new ResponseEntity<>("There Are No Developer With id " + developer_id, HttpStatus.BAD_REQUEST);
        bugService.updateBug(bug);
        return new ResponseEntity<>(bug, HttpStatus.ACCEPTED);
    }

}

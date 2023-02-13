package com.moaaz.bug.controller;

import com.moaaz.bug.model.Admin;
import com.moaaz.bug.model.Developer;
import com.moaaz.bug.model.Tester;
import com.moaaz.bug.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BugTracking")
public class ForgetPasswordController {
    @Autowired
    private MailSenderService mailService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private TesterService testerService;

    @PostMapping("/sendPasswordToEmail")
    public ResponseEntity<Object> sendPasswordToEmail(@RequestParam String email) {
        Admin admin = adminService.getAdminByEmail(email);
        Developer developer = developerService.getDeveloperByEmail(email);
        Tester tester = testerService.getTesterByEmail(email);
        System.out.println(email + "*******************************");
        if (admin == null && developer == null && tester == null) {
            return new ResponseEntity<>("Email Isn't In Our Database...", HttpStatus.BAD_REQUEST);
        }
        // send password for email.
        if (admin != null) mailService.sendPasswordToEmail(admin.getEmail(), admin.getPassword());
        else if (developer != null) mailService.sendPasswordToEmail(developer.getEmail(), developer.getPassword());
        else if (tester != null) mailService.sendPasswordToEmail(tester.getEmail(), tester.getPassword());

        return new ResponseEntity<>("The Password Sent Success..", HttpStatus.OK);
    }
}

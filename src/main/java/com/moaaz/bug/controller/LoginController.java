package com.moaaz.bug.controller;


import com.moaaz.bug.model.Admin;
import com.moaaz.bug.model.Developer;
import com.moaaz.bug.model.Tester;
import com.moaaz.bug.service.AdminService;
import com.moaaz.bug.service.DeveloperService;
import com.moaaz.bug.service.LoginService;
import com.moaaz.bug.service.TesterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/BugTracking")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private DeveloperService developerService;

    @Autowired
    private TesterService testerService;


    @PostMapping("/login")
    public ResponseEntity<Object> loginPost(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Object object = loginService.login(email, password);
        if (object instanceof Admin) {// Admin
            Admin admin = (Admin) object;
            session.setAttribute("admin_id", admin.getId());
            System.out.println("admin");
            System.out.println(admin.toString());
            return new ResponseEntity<>(admin, HttpStatus.ACCEPTED);

        } else if (object instanceof Developer) {// Developer
            Developer developer = (Developer) object;
            session.setAttribute("developer_id", developer.getId());
            System.out.println(developer.toString());
            System.out.println("developer");
            return new ResponseEntity<>(developer, HttpStatus.ACCEPTED);
        } else if (object instanceof Tester) {// Tester
            Tester tester = (Tester) object;
            session.setAttribute("tester_id", tester.getId());
            System.out.println("tester");
            System.out.println(tester.toString());
            return new ResponseEntity<>(tester, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("Error Email Or Password...", HttpStatus.BAD_REQUEST);

    }


}

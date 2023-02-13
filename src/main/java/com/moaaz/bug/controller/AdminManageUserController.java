package com.moaaz.bug.controller;

import com.moaaz.bug.model.Admin;
import com.moaaz.bug.model.Developer;
import com.moaaz.bug.model.Tester;
import com.moaaz.bug.service.AdminService;
import com.moaaz.bug.service.DeveloperService;
import com.moaaz.bug.service.TesterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminManageUserController {
    @Autowired
    AdminService adminService;
    @Autowired
    private DeveloperService developerService;

    @Autowired
    private TesterService testerService;


    @GetMapping("/getAllDevelopers")
    @ResponseStatus(HttpStatus.OK)
    public List<Developer> getAllDevelopers() {
        return developerService.getAllDevelopers();
    }

    @GetMapping("/getAllTesters")
    @ResponseStatus(HttpStatus.OK)
    public List<Tester> getAllTesters() {
        return testerService.getAllTesters();
    }

    @PostMapping("/addAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Admin> addDeveloper(@RequestBody @Valid Admin admin) throws HttpMessageNotReadableException {
        adminService.addAdmin(admin);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @PostMapping("/addDeveloper")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Developer> addDeveloper(@RequestBody @Valid Developer developer) throws HttpMessageNotReadableException {
        System.out.println(developer.getId() + "******************************************************************************************");
        developerService.addDeveloper(developer);
        return new ResponseEntity<>(developer, HttpStatus.CREATED);
    }

    @PostMapping("/addTester")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Tester> addTester(@RequestBody @Valid Tester tester) throws HttpMessageNotReadableException {
        testerService.addTester(tester);
        return new ResponseEntity<>(tester, HttpStatus.CREATED);
    }

    @PutMapping("/updateDeveloper")
    public ResponseEntity<Object> updateDeveloper(@RequestBody Developer developer) {
        if (developerService.getDeveloperById(developer.getId()) == null)
            return new ResponseEntity<>("There are no developer with id = " + developer.getId(), HttpStatus.BAD_REQUEST);
        developerService.updateDeveloper(developer);
        return new ResponseEntity<>(developer, HttpStatus.ACCEPTED);

    }

    @PutMapping("/updateTester")
    public ResponseEntity<Object> updateTester(@RequestBody Tester tester) {
        if (testerService.getTesterById(tester.getId()) == null)
            return new ResponseEntity<>("There are no tester with id = " + tester.getId(), HttpStatus.BAD_REQUEST);
        testerService.updateTester(tester);
        return new ResponseEntity<>(tester, HttpStatus.CREATED);
    }


    @DeleteMapping("/deleteDeveloper/{developer_id}")
    public ResponseEntity<Object> deleteDeveloper(@PathVariable int developer_id) {
        Developer developer = developerService.getDeveloperById(developer_id);
        if (developer == null)
            return new ResponseEntity<>("There Are No Developer With Id " + developer_id, HttpStatus.BAD_REQUEST);
        developerService.deleteDeveloper(developer_id);
        return new ResponseEntity<>("Deleted Developer Success...", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteTester/{tester_id}")
    public ResponseEntity<Object> deleteTester(@PathVariable int tester_id) {
        Tester tester = testerService.getTesterById(tester_id);
        if (tester == null)
            return new ResponseEntity<>("There Are No Tester With Id " + tester_id, HttpStatus.BAD_REQUEST);
        testerService.deleteTesterById(tester_id);
        return new ResponseEntity<>("Deleted Tester Success...", HttpStatus.ACCEPTED);
    }

}

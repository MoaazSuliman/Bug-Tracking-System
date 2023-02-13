package com.moaaz.bug.service;

import com.moaaz.bug.model.Admin;
import com.moaaz.bug.model.Developer;
import com.moaaz.bug.model.Tester;
import com.moaaz.bug.repository.AdminRepository;
import com.moaaz.bug.repository.DeveloperRepository;
import com.moaaz.bug.repository.TesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    AdminRepository adminRepo;
    @Autowired
    DeveloperRepository developerRepo;
    @Autowired
    TesterRepository testerRepo;

    public Object login(String email, String password) {
        Admin admin = adminRepo.findByEmailAndPassword(email, password);
        Developer developer = developerRepo.findByEmailAndPassword(email, password);
        Tester tester = testerRepo.findByEmailAndPassword(email, password);

        if (admin != null)
            return admin;
        else if (developer != null)
            return developer;
        else if (tester != null)
            return tester;
       return null;
    }
}

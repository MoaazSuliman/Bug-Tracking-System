package com.moaaz.bug.repository;

import com.moaaz.bug.model.Tester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TesterRepository extends JpaRepository<Tester, Integer> {

    public Tester findByEmailAndPassword(String email, String password);

    public Tester findByEmail(String email);
}

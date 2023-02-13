package com.moaaz.bug.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moaaz.bug.model.Developer;
@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

	public Developer findByEmailAndPassword(String email, String password);
	public Developer findByEmail(String email);
}

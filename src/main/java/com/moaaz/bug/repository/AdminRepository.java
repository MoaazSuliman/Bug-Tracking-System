package com.moaaz.bug.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moaaz.bug.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	public Admin findByEmailAndPassword(String email, String password);
	public Admin findByEmail(String email);

//	public Person findByEmail(String email);
//
//	@Query(value = "SELECT p FROM person WHERE p.role = 0", nativeQuery = true)
//	public ArrayList<Person> getAllDevelopers();
//
//	@Query(value = "SELECT p FROM person WHERE p.role = 1", nativeQuery = true)
//	public ArrayList<Person> getAllTesters();
}

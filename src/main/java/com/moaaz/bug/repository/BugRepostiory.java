package com.moaaz.bug.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moaaz.bug.model.Bug;

@Repository
public interface BugRepostiory extends JpaRepository<Bug, Integer> {
//@Query(value="UPDATE bug SET ")
//	public void closeBug(int id );
}

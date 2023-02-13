package com.moaaz.bug.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moaaz.bug.model.Bug;
import com.moaaz.bug.repository.BugRepostiory;

@Service
public class BugService {

	@Autowired
	private BugRepostiory bugRepo;

	public ArrayList<Bug> getAllBugs() {
		return (ArrayList<Bug>) bugRepo.findAll();
	}

	public Bug getBugById(int id) {
		return bugRepo.findById(id).orElse(null);
	}

	public void updateBug(Bug bug) {
		bugRepo.save(bug);
	}

	public void addBug(Bug bug) {
		setDateForBug(bug);
		bugRepo.save(bug);

	}

	public void deleteBugById(int id) {
		bugRepo.deleteById(id);
	}

	private void setDateForBug(Bug bug) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		bug.setDate(simpleDateFormat.format(new Date()));
	}

}

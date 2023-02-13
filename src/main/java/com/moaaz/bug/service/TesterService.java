package com.moaaz.bug.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moaaz.bug.model.Bug;
import com.moaaz.bug.model.Tester;
import com.moaaz.bug.model.types.BugStatus;
import com.moaaz.bug.repository.TesterRepository;

@Service
public class TesterService {

	@Autowired
	private TesterRepository testerRepo;

	public Tester getTesterByEmailAndPassword(String email, String password) {
		return testerRepo.findByEmailAndPassword(email, password);
	}

	public ArrayList<Tester> getAllTesters() {
		return (ArrayList<Tester>) testerRepo.findAll();
	}

	public void addTester(Tester tester) {
		testerRepo.save(tester);
	}

	public void updateTester(Tester tester) {
		testerRepo.save(tester);
	}

	public void deleteTesterById(int id) {
		testerRepo.deleteById(id);
	}

	public Tester getTesterById(int id) {
		return testerRepo.findById(id).orElse(null);
	}
	public Tester sortTesterBugs(Tester tester) {
		ArrayList<Bug> bugs = (ArrayList<Bug>) tester.getBugs();
		int len = bugs.size();
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				if (bugs.get(i).getStatus() == BugStatus.Close && bugs.get(j).getStatus() == BugStatus.Open) {
					Bug temp = new Bug();
					// put bug1 in temp;
					temp.setId(bugs.get(i).getId());
					temp.setName(bugs.get(i).getName());
					temp.setType(bugs.get(i).getType());
					temp.setPriority(bugs.get(i).getPriority());
					temp.setLevel(bugs.get(i).getLevel());
					temp.setDate(bugs.get(i).getDate());
					temp.setStatus(bugs.get(i).getStatus());
					temp.setSource(bugs.get(i).getSource());
					temp.setDeveloper(bugs.get(i).getDeveloper());
					temp.setTester(bugs.get(i).getTester());
					temp.setProject(bugs.get(i).getProject());
					// put bug2 in bug1
					bugs.get(i).setId(bugs.get(j).getId());
					bugs.get(i).setName(bugs.get(j).getName());
					bugs.get(i).setType(bugs.get(j).getType());
					bugs.get(i).setPriority(bugs.get(j).getPriority());
					bugs.get(i).setLevel(bugs.get(j).getLevel());
					bugs.get(i).setDate(bugs.get(j).getDate());
					bugs.get(i).setStatus(bugs.get(j).getStatus());
					bugs.get(i).setSource(bugs.get(j).getSource());
					bugs.get(i).setDeveloper(bugs.get(j).getDeveloper());
					bugs.get(i).setTester(bugs.get(j).getTester());
					bugs.get(i).setProject(bugs.get(j).getProject());
					// put temp in bug2
					bugs.get(j).setId(temp.getId());
					bugs.get(j).setName(temp.getName());
					bugs.get(j).setType(temp.getType());
					bugs.get(j).setPriority(temp.getPriority());
					bugs.get(j).setLevel(temp.getLevel());
					bugs.get(j).setDate(temp.getDate());
					bugs.get(j).setStatus(temp.getStatus());
					bugs.get(j).setSource(temp.getSource());
					bugs.get(j).setDeveloper(temp.getDeveloper());
					bugs.get(j).setTester(temp.getTester());
					bugs.get(j).setProject(temp.getProject());
				}
			}

		}
		tester.setBugs(bugs);
		return tester;
	}

	public Tester getTesterByEmail(String email){
		return testerRepo.findByEmail( email);
	}
}

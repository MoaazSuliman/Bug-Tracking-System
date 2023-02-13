package com.moaaz.bug.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moaaz.bug.model.types.Gender;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Developer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Developer extends Person{

	private int bonus;

	@OneToMany
	@JoinColumn(name = "developer_id", referencedColumnName = "id")
	private List<Bug> bugs;

	@OneToMany
	@JoinColumn(name = "developer_id", referencedColumnName = "id")
	private List<Message> messages;
	public void addMessageToDeveloperMessages(Message message) {
		this.messages.add(message);
	}

	public void addBugToDeveloperBugs(Bug bug) {
		this.bugs.add(bug);
	}

}

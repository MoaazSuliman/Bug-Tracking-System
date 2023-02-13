package com.moaaz.bug.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "Tester")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tester extends Person {

    private int bonus;
    @OneToMany
    @JoinColumn(name = "tester_id", referencedColumnName = "id")
    private List<Bug> bugs;

    @OneToMany
    @JoinColumn(name = "tester_id", referencedColumnName = "id")
    private List<Message> messages;


    public void addMessageToTesterMessages(Message message) {
        this.messages.add(message);
    }

    public void addBugToTesterBugs(Bug bug) {
        this.bugs.add(bug);
    }


}

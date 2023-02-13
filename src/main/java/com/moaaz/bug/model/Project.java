package com.moaaz.bug.model;

import com.moaaz.bug.model.types.ProjectStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "project")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Project Name Must Not Be Empty...")
    @NotNull(message = "Project Name Must Not Be Null...")
    private String name;
    @NotEmpty(message = "Description Must Not Be Empty...")
    @NotNull(message = "Description Must Not Be Null...")
    private String description;
    @NotEmpty(message = "Start Date Must Not Be Empty...")
    @NotNull(message = "Start Date Must Not Be Null...")
    private String startDate;
    @NotEmpty(message = "End Date Must Not Be Empty...")
    @NotNull(message = "End Date Must Not Be Null...")
    private String endDate;

    private ProjectStatus status;

    @OneToOne
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    private Developer developer;
    @OneToOne
    @JoinColumn(name = "tester_id", referencedColumnName = "id")
    private Tester tester;

    @OneToMany
    @JoinColumn(name = "bug_id", referencedColumnName = "id")
    private List<Bug> bugs;

public void addBugToProject(Bug bug){
    this.bugs.add(bug);
}
}

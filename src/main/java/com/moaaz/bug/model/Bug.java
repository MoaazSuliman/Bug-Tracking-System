package com.moaaz.bug.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moaaz.bug.model.types.BugPriority;
import com.moaaz.bug.model.types.BugStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "Bug")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Bug Name Must Not Be Empty...")
    @NotNull(message = "Bug Name Must Not Be Null...")
    private String name;
    @NotEmpty(message = "Bug Description Must Not Be Empty...")
    @NotNull(message = "Bug Description Must Not Be Null...")
    private String description;
    @NotEmpty(message = "Bug type Must Not Be Empty...")
    @NotNull(message = "Bug type Must Not Be Null...")
    private String type;
//    @NotEmpty(message = "Bug priority Must Not Be Empty...")
    @NotNull(message = "Bug priority Must Not Be Null...")
    private BugPriority priority;

    @NotNull(message = "Bug level Must Not Be Null...")
    private int level;

//    @NotEmpty(message = "Bug Date Must Not Be Empty...")
    @NotNull(message = "Bug Date Must Not Be Null...")
    private String date;
//    @NotEmpty(message = "Bug Status Must Not Be Empty...")
//    @NotNull(message = "Bug Status Must Not Be Null...")
    private BugStatus status;
    private String source;
    @ManyToOne(targetEntity = Developer.class)
    @JsonIgnore
    private Developer developer;
    @ManyToOne(targetEntity = Tester.class)
    @JsonIgnore
    private Tester tester;
    @ManyToOne(targetEntity = Project.class)
    @JsonIgnore
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;


}

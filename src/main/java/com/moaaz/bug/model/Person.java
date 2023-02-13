package com.moaaz.bug.model;

import com.moaaz.bug.model.types.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name Must Not Be Empty...")
    @NotNull(message = "Name Must Not Be Null...")
    private String name;
    @NotEmpty(message = "Address Must Not Be Empty...")
    @NotNull(message = "Address Must Not Be Null...")
    private String address;
    @NotEmpty(message = "Email Must Not Be Empty...")
    @NotNull(message = "Email Must Not Be Null...")
    @Email(message = "This Is Email.!")
    private String email;
    @NotEmpty(message = "Password Must Not Be Empty...")
    @NotNull(message = "Password Must Not Be Null...")
    @Size(min = 8, message = "Password Should Be Greater Than 8 Characters...")
    private String password;

    @Lob
    private byte[] source;


    private Role role;

    private String page;
}

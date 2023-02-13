package com.moaaz.bug.model;


import com.moaaz.bug.model.types.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Admin")
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
public class Admin extends Person {


}

package com.nihanim.rest.webservices.restfulwebservices.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Validation Message Here!")
    private String name;

    @Past
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> post;
}

package com.nihanim.rest.webservices.restfulwebservices.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10, message = "Validation Message Here!")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

package com.nihanim.rest.webservices.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserResource {

    @GetMapping("")
    public List<User> getUsers() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return new User();
    }

    @PostMapping("")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(1L).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void getDeleteUser(@PathVariable long id) {

    }
}
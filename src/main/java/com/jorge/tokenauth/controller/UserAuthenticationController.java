package com.jorge.tokenauth.controller;

import com.jorge.tokenauth.model.result.UserAuthenticationResource;
import com.jorge.tokenauth.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping("/users")
public class UserAuthenticationController {

    private final UserAuthenticationService service;
    
    @Autowired
    public UserAuthenticationController(UserAuthenticationService service) {
        this.service = service;
    }

    @GetMapping("/{id}/token/{token}")
    public HttpEntity<UserAuthenticationResource> validation(@PathVariable Long id,
                                                             @PathVariable String token) {
        service.validation(id, token);
        final Link link = linkTo(UserAuthenticationController.class).slash("home").withRel("home");
        final UserAuthenticationResource resource = new UserAuthenticationResource(link);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resource);
    }

}

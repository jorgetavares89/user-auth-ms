package com.jorge.user.auth.model.result;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;


public class UserAuthenticationResource extends ResourceSupport {

    public UserAuthenticationResource(Link link) {
        add(link);
    }
}


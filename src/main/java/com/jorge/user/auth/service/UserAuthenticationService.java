package com.jorge.user.auth.service;

import com.google.common.collect.Lists;
import com.jorge.user.auth.exception.NotFoundException;
import com.jorge.user.auth.exception.UnauthorizedException;
import com.jorge.user.auth.model.entity.UserAuthentication;
import com.jorge.user.auth.model.factory.UserAuthenticatorFactory;
import com.jorge.user.auth.model.request.UserAuthenticationRequest;
import com.jorge.user.auth.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthenticationService {

    private UserAuthenticationRepository repository;
    private UserAuthenticatorFactory factory;

    @Autowired
    public UserAuthenticationService(UserAuthenticationRepository repository,
                                     UserAuthenticatorFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public String validation(Long userId, String token) {
        return repository.findByUserIdAndToken(userId, token)
                .orElseThrow(() -> new UnauthorizedException("Fail to authentication")).getToken();
    }

    public UserAuthentication save(UserAuthenticationRequest userAuthenticationRequest) {
        final UserAuthentication userAuthentication = factory.create(userAuthenticationRequest);
        return repository.save(userAuthentication);
    }

    public List<UserAuthentication> findAll() {
        List<UserAuthentication> methods = Lists.newArrayList(repository.findAll());
        if (methods.isEmpty()) throw new NotFoundException("Users not found");
        return methods;
    }

}

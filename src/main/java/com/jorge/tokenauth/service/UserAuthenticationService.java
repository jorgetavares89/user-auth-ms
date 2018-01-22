package com.jorge.tokenauth.service;

import com.google.common.collect.Lists;
import com.jorge.tokenauth.exception.NotFoundException;
import com.jorge.tokenauth.exception.UnauthorizedException;
import com.jorge.tokenauth.model.entity.UserAuthentication;
import com.jorge.tokenauth.model.factory.UserAuthenticatorFactory;
import com.jorge.tokenauth.model.request.UserAuthenticationRequest;
import com.jorge.tokenauth.repository.UserAuthenticationRepository;
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

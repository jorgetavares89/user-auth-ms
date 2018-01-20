package com.jorge.tokenauth.service;

import com.jorge.tokenauth.model.entity.UserAuthentication;
import com.jorge.tokenauth.model.factory.UserAuthenticatorFactory;
import com.jorge.tokenauth.model.request.UserAuthenticationRequest;
import com.jorge.tokenauth.model.result.UserCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class SqsUserCreatedListener {

    private UserAuthenticationService userAuthenticationService;
    private UserAuthenticatorFactory userAuthenticatorFactory;

    @Autowired
    public SqsUserCreatedListener(UserAuthenticationService userAuthenticationService,
                                  UserAuthenticatorFactory userAuthenticatorFactory) {
        this.userAuthenticationService = userAuthenticationService;
        this.userAuthenticatorFactory = userAuthenticatorFactory;
    }

    @SqsListener("user-token-auth-queue")
    public void queueListener(UserCreatedEvent userCreatedEvent) {
        final UserAuthenticationRequest userAuthenticationRequest = userAuthenticatorFactory.createRequestByEvent(userCreatedEvent);
        userAuthenticationService.save(userAuthenticationRequest);
    }
}

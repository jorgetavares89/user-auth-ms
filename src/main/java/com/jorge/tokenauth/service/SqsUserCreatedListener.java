package com.jorge.tokenauth.service;

import com.amazonaws.services.sqs.model.Message;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jorge.tokenauth.model.factory.UserAuthenticatorFactory;
import com.jorge.tokenauth.model.request.UserAuthenticationRequest;
import com.jorge.tokenauth.model.result.UserCreatedEvent;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
@EnableSqs
public class SqsUserCreatedListener {

    private UserAuthenticationService userAuthenticationService;
    private UserAuthenticatorFactory userAuthenticatorFactory;

    @Autowired
    public SqsUserCreatedListener(UserAuthenticationService userAuthenticationService,
                                  UserAuthenticatorFactory userAuthenticatorFactory) {
        this.userAuthenticationService = userAuthenticationService;
        this.userAuthenticatorFactory = userAuthenticatorFactory;
    }

    @SqsListener("${amazon.aws.sqs.queue.name.user.created}")
    public void queueListener(String message) {
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent().fromJson(message);
        final UserAuthenticationRequest userAuthenticationRequest = userAuthenticatorFactory.createRequestByEvent(userCreatedEvent);
        userAuthenticationService.save(userAuthenticationRequest);
    }
}

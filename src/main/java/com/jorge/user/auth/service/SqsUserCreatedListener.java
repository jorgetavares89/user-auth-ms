package com.jorge.user.auth.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jorge.user.auth.model.factory.UserAuthenticatorFactory;
import com.jorge.user.auth.model.request.UserAuthenticationRequest;
import com.jorge.user.auth.model.result.UserCreatedEvent;
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
        final UserCreatedEvent userCreatedEvent = getUserCreatedEvent(message);
        final UserAuthenticationRequest userAuthenticationRequest = userAuthenticatorFactory.createRequestByEvent(userCreatedEvent);
        userAuthenticationService.save(userAuthenticationRequest);
    }

    private UserCreatedEvent getUserCreatedEvent(String message) {
        final JsonObject jsonObject = new Gson().fromJson(message, JsonObject.class);
        final JsonElement jsonElement = jsonObject.get("Message");
        return new UserCreatedEvent()
                .fromJson(jsonElement.getAsString());
    }
}

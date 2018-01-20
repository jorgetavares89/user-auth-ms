package com.jorge.tokenauth.model.factory;

import com.jorge.tokenauth.model.entity.UserAuthentication;
import com.jorge.tokenauth.model.request.UserAuthenticationRequest;
import com.jorge.tokenauth.model.result.UserCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticatorFactory {

    public UserAuthenticationRequest createRequest(UserAuthentication userAuthentication) {
        return new UserAuthenticationRequest.Builder()
                .withId(userAuthentication.getId())
                .withToken(userAuthentication.getToken())
                .build();
    }

    public UserAuthenticationRequest createRequestByEvent(UserCreatedEvent userCreatedEvent) {
        return new UserAuthenticationRequest.Builder()
                .withId(userCreatedEvent.getId())
                .withToken(userCreatedEvent.getToken())
                .build();
    }

	public UserAuthentication create(UserAuthenticationRequest userAuthenticationRequest) {
		return new UserAuthentication.Builder()
                .withId(userAuthenticationRequest.getId())
                .withToken(userAuthenticationRequest.getToken())
                .build();
    }

    public UserAuthentication create(UserCreatedEvent userCreatedEvent) {
        return new UserAuthentication.Builder()
                .withId(userCreatedEvent.getId())
                .withToken(userCreatedEvent.getToken())
                .build();
    }

}

package com.jorge.tokenauth.model.factory;

import com.jorge.tokenauth.model.entity.UserAuthentication;
import com.jorge.tokenauth.model.request.UserAuthenticationRequest;
import com.jorge.tokenauth.model.result.UserCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticatorFactory {

    public UserAuthenticationRequest createRequestByEvent(UserCreatedEvent userCreatedEvent) {
        return new UserAuthenticationRequest.Builder()
                .withUserId(userCreatedEvent.getId())
                .withToken(userCreatedEvent.getToken())
                .build();
    }

	public UserAuthentication create(UserAuthenticationRequest userAuthenticationRequest) {
		return new UserAuthentication.Builder()
                .withUserId(userAuthenticationRequest.getId())
                .withToken(userAuthenticationRequest.getToken())
                .build();
    }

    public UserAuthentication create(UserCreatedEvent userCreatedEvent) {
        return new UserAuthentication.Builder()
                .withUserId(userCreatedEvent.getId())
                .withToken(userCreatedEvent.getToken())
                .build();
    }

}

package com.jorge.user.auth.model.factory;

import com.jorge.user.auth.model.entity.UserAuthentication;
import com.jorge.user.auth.model.request.UserAuthenticationRequest;
import com.jorge.user.auth.model.result.UserCreatedEvent;
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

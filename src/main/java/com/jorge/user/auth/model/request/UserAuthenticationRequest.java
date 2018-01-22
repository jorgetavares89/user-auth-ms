package com.jorge.user.auth.model.request;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

public class UserAuthenticationRequest {

    private Long id;
    private Long userId;
    private String token;

    public UserAuthenticationRequest() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class Builder {

        private UserAuthenticationRequest userAuthentication = new UserAuthenticationRequest();

        public Builder withId(Long id) {
            this.userAuthentication.setId(id);
            return this;
        }


        public Builder withUserId(Long userId) {
            this.userAuthentication.setId(userId);
            return this;
        }

        public Builder withToken(String token) {
            this.userAuthentication.setToken(token);
            return this;
        }

        public UserAuthenticationRequest build() {
            return this.userAuthentication;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthenticationRequest that = (UserAuthenticationRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, token);
    }

    @Override
    public String toString() {
        return "UserAuthentication{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}

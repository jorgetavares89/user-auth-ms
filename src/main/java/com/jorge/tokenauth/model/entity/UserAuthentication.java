package com.jorge.tokenauth.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity
public class UserAuthentication {
	
    private Long id;
    private String token;

    public UserAuthentication() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class Builder {

        private UserAuthentication userAuthentication = new UserAuthentication();

        public Builder withId(Long groupId) {
            this.userAuthentication.setId(groupId);
            return this;
        }

        public Builder withToken(String token) {
            this.userAuthentication.setToken(token);
            return this;
        }

        public UserAuthentication build() {
            return this.userAuthentication;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthentication that = (UserAuthentication) o;
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

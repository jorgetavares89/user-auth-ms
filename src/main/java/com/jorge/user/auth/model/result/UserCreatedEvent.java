package com.jorge.user.auth.model.result;

import com.google.gson.Gson;

import java.util.Objects;

public class UserCreatedEvent {

    private Long id;
    private String token;

    public UserCreatedEvent(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public UserCreatedEvent(){}

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

    public UserCreatedEvent fromJson(String json) {
        return new Gson().fromJson(json, UserCreatedEvent.class);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreatedEvent that = (UserCreatedEvent) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token);
    }

    @Override
    public String toString() {
        return "UserCreatedEvent{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }

    public static class Builder {

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();

        public Builder withId(Long id) {
            userCreatedEvent.setId(id);
            return this;
        }

        public Builder withToken(String token) {
            userCreatedEvent.setToken(token);
            return this;
        }

        public UserCreatedEvent build() {
            return userCreatedEvent;
        }
    }
}

package com.jorge.user.auth.model.request;

import com.google.gson.Gson;

public class SubscribeRequest {

    private String endpoint;
    private String protocol;

    public SubscribeRequest() {

    }
    public SubscribeRequest(String endpoint, String protocol) {
        this.endpoint = endpoint;
        this.protocol = protocol;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String toJson() {
        return new Gson().toJson(this, SubscribeRequest.class);
    }
}

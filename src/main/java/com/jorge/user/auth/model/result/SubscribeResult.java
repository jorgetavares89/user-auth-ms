package com.jorge.user.auth.model.result;

import com.google.gson.Gson;

public class SubscribeResult {

    private String subscriptionArn;

    public SubscribeResult(String subscriptionArn) {
        this.subscriptionArn = subscriptionArn;
    }
    public SubscribeResult(){}

    public String getSubscriptionArn() {
        return subscriptionArn;
    }

    public void setSubscriptionArn(String subscriptionArn) {
        this.subscriptionArn = subscriptionArn;
    }

    public String toJson() {
        return new Gson().toJson(this, SubscribeResult.class);
    }

    public SubscribeResult fromJson(String json) {
        return new Gson().fromJson(json, SubscribeResult.class);
    }
}

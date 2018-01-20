package com.jorge.tokenauth.service;

import com.amazonaws.protocol.Protocol;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.jorge.tokenauth.config.SnsSubscriber;
import com.jorge.tokenauth.exception.BadRequestException;
import com.jorge.tokenauth.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SnsSubscribeClientService {

    private RestTemplate restTemplate;
    private String userCreatedQueueUrl;

    @Autowired
    public SnsSubscribeClientService(RestTemplate restTemplate,
                                     String userCreatedQueueUrl) {
        this.restTemplate = restTemplate;
        this.userCreatedQueueUrl = userCreatedQueueUrl;
    }

    public SubscribeResult subscribe() {
        final String subUrl = "http://user-token-auth-ms:8080/sub";
        HttpEntity<SubscribeRequest> request = new HttpEntity<>(createRequest("sqs", userCreatedQueueUrl));
        ResponseEntity<SubscribeResult> response = restTemplate
                .exchange(subUrl, HttpMethod.POST, request, SubscribeResult.class);
        return handleResponse(response);
    }

    private SubscribeResult handleResponse(final ResponseEntity<SubscribeResult> resultResponseEntity) {
        if (resultResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return resultResponseEntity.getBody();
        } else if (resultResponseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new NotFoundException("not found");
        } else {
            throw new BadRequestException("bad request");
        }
    }
    private SubscribeRequest createRequest(String protocol, String endpoint) {
        return new SubscribeRequest()
                .withProtocol(protocol)
                .withEndpoint(endpoint);
    }
}

package com.jorge.user.auth.service;

import com.jorge.user.auth.exception.BadRequestException;
import com.jorge.user.auth.exception.NotFoundException;
import com.jorge.user.auth.model.request.SubscribeRequest;
import com.jorge.user.auth.model.result.SubscribeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
        final String userRegisterEndpoint = "http://user-register-ms:8080";
        final String subPath = "/sub";
        final URI uri = createUri(userRegisterEndpoint, subPath);
        final String subRequest = new SubscribeRequest(userCreatedQueueUrl, "sqs").toJson();
        HttpEntity<String> entity = new HttpEntity<>(subRequest, createHeader());
        ResponseEntity<String> response = restTemplate
                .exchange(uri, HttpMethod.POST, entity, String.class);
        return handleResponse(response);
    }

    private SubscribeResult handleResponse(final ResponseEntity<String> resultResponseEntity) {
        if (resultResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return new SubscribeResult().fromJson(resultResponseEntity.getBody());
        } else if (resultResponseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new NotFoundException("not found");
        } else {
            throw new BadRequestException("bad request");
        }
    }
    private com.amazonaws.services.sns.model.SubscribeRequest createRequest(String protocol, String endpoint) {
        return new com.amazonaws.services.sns.model.SubscribeRequest()
                .withProtocol(protocol)
                .withEndpoint(endpoint);
    }

    private URI createUri(String endpoint, String path) {
       return UriComponentsBuilder.fromUriString(endpoint)
                .path(path)
                .build()
                .encode()
                .toUri();
    }

    private HttpHeaders createHeader() {
        HttpHeaders header = new HttpHeaders();
        header.set("Content-Type", "application/json");
        return header;
    }
}

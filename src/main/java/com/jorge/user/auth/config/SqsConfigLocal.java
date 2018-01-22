package com.jorge.user.auth.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev-local")
public class SqsConfigLocal extends AwsGeneralConfigLocal {

    private static final Logger LOG = LoggerFactory.getLogger(SqsConfigLocal.class);

    @Value("${amazon.aws.sqs.endpoint}")
    private String amazonSqsEndpoint;
    @Value("${amazon.aws.sqs.queue.name.user.created}")
    private String userCreatedQeueName;

    @Bean
    public AmazonSQSAsync amazonSqs() {
        LOG.debug("Creating the SQS Client...");
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(getSqsEndpointConfiguration())
                .withCredentials(getAmazonAWSCredentials())
                .build();
    }

    @Bean
    public String userCreatedQueueUrl() {
        return createQueue(userCreatedQeueName).getQueueUrl();
    }

    private CreateQueueResult createQueue(String queueName) {
        LOG.debug("Creating the Queue [" + queueName + "]");
        return amazonSqs().createQueue(new CreateQueueRequest(queueName));
    }

    private AwsClientBuilder.EndpointConfiguration getSqsEndpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(amazonSqsEndpoint, amazonRegion);
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSqs) {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSqs);
        return factory;
    }
}
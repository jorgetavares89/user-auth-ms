package com.jorge.user.auth.config;

import com.jorge.user.auth.model.result.SubscribeResult;
import com.jorge.user.auth.service.SnsSubscribeClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
public class SnsSubscriber implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(SnsSubscriber.class);

    private SnsSubscribeClientService snsSubscribeClientService;

    @Autowired
    public SnsSubscriber(SnsSubscribeClientService snsSubscribeClientService) {
        this.snsSubscribeClientService = snsSubscribeClientService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final SubscribeResult result = snsSubscribeClientService.subscribe();
        LOG.info("Subscription arn: "+result.getSubscriptionArn());
    }
}

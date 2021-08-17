package com.twitter.accionaChallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@ConditionalOnClass({ TwitterFactory.class, Twitter.class })
@PropertySource("classpath:twitter4j.properties")
public class AccessTwitter {

    @Value("${oauth.consumerKey}")
    private String consumerKey;
    @Value("${oauth.consumerSecret}")
    private String consumerSecret;
    @Value("${oauth.accessToken}")
    private String token;
    @Value("${oauth.accessTokenSecret}")
    private String tokenSecret;

    public TwitterStream credentials() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(token)
                .setOAuthAccessTokenSecret(tokenSecret);

        return new TwitterStreamFactory(configurationBuilder.build()).getInstance();
    }
}


package com.twitter.accionaChallenge.service;

import com.twitter.accionaChallenge.config.AccessTwitter;
import com.twitter.accionaChallenge.domain.Hashtag;
import com.twitter.accionaChallenge.domain.TweetData;
import com.twitter.accionaChallenge.repository.AccionaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import twitter4j.*;

import java.util.Objects;

@Service
@Component
public class DataFlowImpl implements ApplicationListener<ApplicationReadyEvent> {

    private final AccionaRepository accionaRepository;
    private static int N = 1500;
    private AccessTwitter accessTwitter;
    private AccionaService accionaService;

    @Autowired
    public DataFlowImpl(AccionaRepository accionaRepository, AccessTwitter accessTwitter, AccionaService accionaService){
        this.accionaRepository = accionaRepository;
        this.accessTwitter = accessTwitter;
        this.accionaService = accionaService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        TwitterStream twitterStream = accessTwitter.credentials();
        Flux<Status> flux = Flux.create(fluxSink -> {
            twitterStream.onStatus(fluxSink::next);
            twitterStream.onException(fluxSink::error);
            fluxSink.onCancel(twitterStream::shutdown);
            twitterStream.sample();
        });
        flux.filter(status -> status.getUser().getFollowersCount() > N && (Objects.equals(status.getLang(), "es") || Objects.equals(status.getLang(), "fr") || Objects.equals(status.getLang(), "it")))
                .flatMap(status -> accionaService.saveTweet(status))
                .subscribe();

        flux.filter(status -> status.getUser().getFollowersCount() > N && (Objects.equals(status.getLang(), "es") || Objects.equals(status.getLang(), "fr") || Objects.equals(status.getLang(), "it")) && status.getHashtagEntities().length > 0)
                .flatMap(status -> accionaService.saveHashtag(status))
                .subscribe();

    }
}

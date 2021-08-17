package com.twitter.accionaChallenge.service;

import com.twitter.accionaChallenge.domain.Hashtag;
import com.twitter.accionaChallenge.domain.TweetData;
import reactor.core.publisher.Mono;
import twitter4j.Status;
import java.util.List;
import java.util.Map;

public interface AccionaService {
    List<TweetData> getAllTweets();
    TweetData setValidationTweet(long id);
    List<TweetData> getTweetsValidated();
    List<Map.Entry<String, Integer>> getHashtags();
    Mono<TweetData> saveTweet (Status status);
    Mono<Hashtag> saveHashtag (Status status);
}
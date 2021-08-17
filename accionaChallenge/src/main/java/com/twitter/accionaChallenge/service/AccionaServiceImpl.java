package com.twitter.accionaChallenge.service;

import com.twitter.accionaChallenge.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.twitter.accionaChallenge.domain.Hashtag;
import com.twitter.accionaChallenge.domain.TweetData;
import com.twitter.accionaChallenge.repository.AccionaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import twitter4j.*;
import java.util.*;

@Service
@PropertySource("classpath:twitter4j.properties")
public class AccionaServiceImpl implements AccionaService {

    private final AccionaRepository accionaRepository;
    private final HashtagRepository hashtagRepository;

    @Value("${numberOfHashtags}")
    private int numberOfHashtags;

    @Autowired
    public AccionaServiceImpl(AccionaRepository accionaRepository, HashtagRepository hashtagRepository){
        this.accionaRepository = accionaRepository;
        this.hashtagRepository = hashtagRepository;
    }

    @Override
    public List<TweetData> getAllTweets() {
        List<TweetData> tweets = accionaRepository.findAll().collectList().block();

        return tweets;
    }

    @Override
    public TweetData setValidationTweet(long id) {
        TweetData tweetDataAux = accionaRepository.findById(id).block();
        if (tweetDataAux != null) {
            tweetDataAux.setValidation(true);
            accionaRepository.save(tweetDataAux).subscribe();

            return tweetDataAux;
        }

        return null;
    }

    @Override
    public List<TweetData> getTweetsValidated() {
        List<TweetData> tweets;
        tweets = accionaRepository.findByValidation(true).collectList().block();
        return tweets;
    }

    @Override
    public List<Map.Entry<String, Integer>> getHashtags() {
        List<Hashtag> hashtags = hashtagRepository.findAll().collectList().block();
        Map<String, Integer> map = new HashMap<>();
        int counter = 0;
        String word;

        assert hashtags != null;
        for (Hashtag hashtag:hashtags){
            for (HashtagEntity hashtagEntities: hashtag.getHashtagEntities()) {
                word = hashtagEntities.getText();
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(hashtagEntities.getText(), 1);
                    counter += 1;
                }
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        List<Map.Entry<String, Integer>> aux = new ArrayList<>();

        aux.add(0, Map.entry("NO HAY HASHTAG AUN", 0));
        if (counter < numberOfHashtags && counter > 0) {        //Control de condición para cuando la lista de hashtags es menor que el valor final de la clasificación
            aux.set(0, list.get(0));
            for (int i = 1; i < counter; i++) {
                aux.add(i, list.get(i));
            }
        } else if (counter >= numberOfHashtags){           //Control de condición para cuando tenemos suficientes hashtags para realizar la clasificación
            aux.set(0, list.get(0));
            for (int i = 1; i < numberOfHashtags; i++){
                aux.add(i, list.get(i));
            }

        }

        return aux;
    }

    @Override
    public Mono<TweetData> saveTweet(Status status) {
        TweetData tweetData = new TweetData(status.getId(), status.getUser().getName(), status.getText(), status.getUser().getLocation(), false);
        return accionaRepository.save(tweetData).log("SAVED");
    }

    @Override
    public Mono<Hashtag> saveHashtag(Status status) {
        Hashtag hashtag = new Hashtag(status.getId(), status.getHashtagEntities());
        return hashtagRepository.save(hashtag).log("SAVED");
    }
}


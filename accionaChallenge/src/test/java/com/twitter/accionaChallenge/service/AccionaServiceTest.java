package com.twitter.accionaChallenge.service;

import com.twitter.accionaChallenge.domain.Hashtag;
import com.twitter.accionaChallenge.domain.TweetData;
import com.twitter.accionaChallenge.repository.AccionaRepository;
import com.twitter.accionaChallenge.repository.HashtagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import twitter4j.HashtagEntity;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccionaServiceTest {

    @MockBean
    private AccionaRepository accionaRepository;

    @MockBean
    private HashtagRepository hashtagRepository;

    @Autowired
    private AccionaServiceImpl accionaService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accionaService = new AccionaServiceImpl(accionaRepository, hashtagRepository);
    }

    @Test
    public void getAllTweetsTest() throws Exception {
        TweetData[] tweetDataArray = {new TweetData(1, "Alex", "Hola", "Almeria", false)};
        Flux<TweetData> list = Flux.fromArray(tweetDataArray);

        BDDMockito.given((this.accionaRepository.findAll())).willReturn(list);
        List<TweetData> aux = accionaService.getAllTweets();

        assertEquals(list.collectList().block().toString(), aux.toString());
    }


    @Test
    public void setValidationTweetTest() throws Exception {
        TweetData tweetData = new TweetData(1, "Alex", "Hola", "Almeria", false);
        TweetData tweetDataAux = new TweetData(1, "Alex", "Hola", "Almeria", true);

        given(this.accionaRepository.findById(tweetData.getId())).willReturn(Mono.just(tweetData));
        given(this.accionaRepository.save(tweetData)).willReturn(Mono.just(tweetDataAux));
        TweetData tweetDataReturned = accionaService.setValidationTweet(tweetData.getId());

        assertTrue(tweetDataReturned.getValidation());
    }

    @Test
    public void getTweetsValidatedTest() throws Exception {
        TweetData[] tweetDataArray = {new TweetData(1, "Alex", "Hola", "Almeria", true)};
        Flux<TweetData> list = Flux.fromArray(tweetDataArray);

        BDDMockito.given(this.accionaRepository.findByValidation(true)).willReturn(list);
        List<TweetData> aux = accionaService.getTweetsValidated();

        assertEquals(list.collectList().block().toString(), aux.toString());
    }

    @Test
    public void getHashtagsTest() throws Exception{
        HashtagEntity hashtagEntities = new HashtagEntity() {
            @Override
            public String getText() {
                return "Acciona";
            }

            @Override
            public int getStart() {
                return 0;
            }

            @Override
            public int getEnd() {
                return 0;
            }
        };

        HashtagEntity[] hashtagEntitiesArray = new HashtagEntity[1];
        hashtagEntitiesArray[0] = hashtagEntities;
        Hashtag[] hashtag = {new Hashtag(1, hashtagEntitiesArray)};
        Flux<Hashtag> hashtags = Flux.fromArray(hashtag);

        BDDMockito.given(this.hashtagRepository.findAll()).willReturn(hashtags);
        List<Map.Entry<String, Integer>> hashtagReturned = accionaService.getHashtags();
        //System.out.println(hashtagReturned.get(0).getKey());

        assertEquals(hashtagEntitiesArray[0].getText(), hashtagReturned.get(0).getKey());
    }
}

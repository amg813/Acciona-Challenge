package com.twitter.accionaChallenge.repository;

import com.twitter.accionaChallenge.domain.TweetData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AccionaRepository extends ReactiveMongoRepository<TweetData, Long> {
    Flux<TweetData> findByValidation(boolean value);
}

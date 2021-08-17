package com.twitter.accionaChallenge.repository;

import com.twitter.accionaChallenge.domain.Hashtag;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends ReactiveMongoRepository<Hashtag, Long> {
}

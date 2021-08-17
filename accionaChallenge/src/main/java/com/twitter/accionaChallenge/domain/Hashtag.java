package com.twitter.accionaChallenge.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import twitter4j.HashtagEntity;
import twitter4j.Status;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "hashtagInformation")
public class Hashtag {
    private long idTweet;
    private HashtagEntity[] hashtagEntities;

    public Hashtag(long idTweet, HashtagEntity[] hashtagEntities) {
        this.idTweet = idTweet;
        this.hashtagEntities = hashtagEntities;
    }
}

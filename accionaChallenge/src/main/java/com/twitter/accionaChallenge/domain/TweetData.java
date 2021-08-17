package com.twitter.accionaChallenge.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "tweetInformation")
public class TweetData {
    @Id
    private long id;
    private String name;
    private String text;
    private String location;
    private boolean validation;

    public TweetData(long id, String name, String text, String location, boolean validation) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.location = location;
        this.validation = validation;
    }

    public boolean getValidation() {
        return validation;
    }
}

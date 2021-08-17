package com.twitter.accionaChallenge.controller;

import com.twitter.accionaChallenge.domain.TweetData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface AccionaController {
    @ApiOperation("Get a list with all tweets")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The request has been successful"),
            @ApiResponse(code = 201, message = "Tweets successfully obtained", response = TweetData.class)
    })
    ResponseEntity<List<TweetData>> getAllTweets();

    @ApiOperation("Validate a specified tweet")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The request has been successful"),
            @ApiResponse(code = 201, message = "Tweet successfully validated", response = TweetData.class)
    })
    ResponseEntity<TweetData> setValidationTweet(Long id);

    @ApiOperation("Get a list with all tweets validated")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The request has been successful"),
            @ApiResponse(code = 201, message = "Tweets successfully obtained", response = TweetData.class)
    })
    ResponseEntity<List<TweetData>> getTweetsValidated();

    @ApiOperation("Get a list with the N most used hashtags")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The request has been successful"),
            @ApiResponse(code = 201, message = "Hashtags successfully obtained", response = TweetData.class)
    })
    ResponseEntity<List<Map.Entry<String, Integer>>> getHashtags();
}

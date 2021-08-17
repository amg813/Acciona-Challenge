package com.twitter.accionaChallenge.controller;

import com.twitter.accionaChallenge.domain.TweetData;
import com.twitter.accionaChallenge.service.AccionaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tweets")
public class AccionaControllerImpl implements AccionaController {

    private final AccionaService accionaService;

    @Autowired
    public AccionaControllerImpl(AccionaService accionaService) {
        this.accionaService = accionaService;
    }

    @Override
    @GetMapping("/getTweets")
    public ResponseEntity<List<TweetData>> getAllTweets() {
        return ResponseEntity.ok(accionaService.getAllTweets());
    }

    @Override
    @PutMapping("/{id}/setValidation")
    public ResponseEntity<TweetData> setValidationTweet(@PathVariable("id") Long id) {
        return ResponseEntity.ok(accionaService.setValidationTweet(id));
    }

    @Override
    @GetMapping("/getTweetsValidated")
    public ResponseEntity<List<TweetData>> getTweetsValidated() {
        return ResponseEntity.ok(accionaService.getTweetsValidated());
    }

    @Override
    @GetMapping("/getHashtags")
    public ResponseEntity<List<Map.Entry<String, Integer>>> getHashtags() {
        return ResponseEntity.ok(accionaService.getHashtags());
    }
}


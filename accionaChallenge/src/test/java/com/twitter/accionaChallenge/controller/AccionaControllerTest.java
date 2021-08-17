package com.twitter.accionaChallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.accionaChallenge.domain.TweetData;
import com.twitter.accionaChallenge.service.AccionaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

import java.util.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(AccionaController.class)
public class AccionaControllerTest {

    TweetData tweetData;
    private String URL = "http://localhost:8080/twitter";

    @Autowired
    private MockMvc mockMvc;
    private AccionaController accionaController;

    @MockBean
    private AccionaService accionaService;


    @Test
    public void getAllTweetsTest() throws Exception {
        TweetData tweetData = new TweetData(1, "Alex", "Hola", "Almeria", false);
        List<TweetData> list = Arrays.asList(tweetData);

        given(accionaService.getAllTweets()).willReturn(list);

        mockMvc.perform(MockMvcRequestBuilders
            .get(URL + "/getTweets")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name", is(tweetData.getName())))
            .andExpect(jsonPath("$[0].text", is(tweetData.getText())))
            .andExpect(jsonPath("$[0].location", is(tweetData.getLocation())))
            .andExpect(jsonPath("$[0].validation", is(false)));
    }

    @Test
    public void setValidationTweetTest() throws Exception {
        TweetData tweetData = new TweetData(1, "Alex", "Hola", "Almeria", false);
        TweetData tweetDataAux = new TweetData(1, "Alex", "Hola", "Almeria", true);

        when(accionaService.setValidationTweet(tweetData.getId())).thenReturn(tweetDataAux);

        mockMvc.perform(put(URL + "/setValidation/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(tweetData.getName())))
                .andExpect(jsonPath("$.text", is(tweetData.getText())))
                .andExpect(jsonPath("$.location", is(tweetData.getLocation())))
                .andExpect(jsonPath("$.validation", is(true)));
    }

    @Test
    public void getTweetsValidatedTest() throws Exception {
        TweetData tweetData1 = new TweetData(1, "Alex", "Hola", "Almeria", true);
        TweetData tweetData2 = new TweetData(2, "Jose", "Adios", "Almeria", true);
        List<TweetData> list = Arrays.asList(tweetData1, tweetData2);

        when(accionaService.getTweetsValidated()).thenReturn(list);

        mockMvc.perform(get(URL + "/getTweetsValidated")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(tweetData1.getName())))
                .andExpect(jsonPath("$[0].text", is(tweetData1.getText())))
                .andExpect(jsonPath("$[0].location", is(tweetData1.getLocation())))
                .andExpect(jsonPath("$[0].validation", is(true)))
                .andExpect(jsonPath("$[1].name", is(tweetData2.getName())))
                .andExpect(jsonPath("$[1].text", is(tweetData2.getText())))
                .andExpect(jsonPath("$[1].location", is(tweetData2.getLocation())))
                .andExpect(jsonPath("$[1].validation", is(true)));
    }

    @Test
    public void getHashtagsTest() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("Acciona", 1);
        map.put("Twitter", 2);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        List<Map.Entry<String, Integer>> listAux = new ArrayList<>(map.entrySet());
        ObjectMapper mapper = new ObjectMapper();

        when(accionaService.getHashtags()).thenReturn(list);

        mockMvc.perform(get(URL + "/getHashtags")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(list.get(0), listAux.get(0));
        assertEquals(list.get(1), listAux.get(1));
    }
}

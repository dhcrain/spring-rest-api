package com.dhcrain.app.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "guggy")
public class Guggy {

    private final String POST_URL = "http://text2gif.guggy.com/v2/guggify";

    @Value("${guggyApiKey}")
    private String API_KEY;


    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json"})
    public String getGuggy() throws IOException {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("apiKey", API_KEY);
        Map<String, String> map = new HashMap<>();

        map.put("sentence", "I think");
        map.put("lang", "en");

        HttpEntity<?> requestEntity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(POST_URL, requestEntity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode gifUrl = root.get("animated").elements().next().get("gif").get("hires").get("secureUrl");

        return gifUrl.toString();
    }
}

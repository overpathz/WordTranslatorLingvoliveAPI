package org.overpathz.mono.rest.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.overpathz.mono.rest.entity.TranslateResponse;
import org.overpathz.mono.rest.util.TranslateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Scanner;

@Component("translate")
public class TranslateAPI {

    private String TOKEN;

    String TRANSLATE_SERVICE_URL = "https://developers.lingvolive.com/";

    private final RestTemplate restTemplate;

    @Autowired
    public TranslateAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.TOKEN = getAuthorizationToken();
    }


    public String translate(TranslateDetails translateDetails) {

        int srcLang = 1049;
        int dstLang = 1033;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите текс для перевода: ");
        String text = scanner.next();

        String MAIN_URL = getParametrizedUrlForTranslationAPI(text, srcLang, dstLang, false);

        HttpEntity<String> entity = getEntity("Authorization", TOKEN);

        ResponseEntity<String> responseEntity = null;

        try {
            restTemplate.exchange(MAIN_URL, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<>() {
                    });
        } catch (HttpClientErrorException exception) {
            System.out.println(exception.getMessage());
            return "No translations found for text";
        }

        String jsonString = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

        TranslateResponse translateResponse = null;
        try {
            translateResponse = mapper.readValue(jsonString, TranslateResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String resultTranslatedWord = translateResponse.getTranslation().getTranslation();

        System.out.println(resultTranslatedWord);

        if (resultTranslatedWord.equals("") && resultTranslatedWord == null) {
            return "I cant translate that";
        } else {
            return resultTranslatedWord;
        }
    }


    private String getParametrizedUrlForTranslationAPI(String text, int srcLang, int dstLang, boolean isCaseSensitive) {
        String API_URL = "api/v1/Minicard?text=" + text + "&srcLang=" + srcLang
                + "&dstLang=" + dstLang + "&isCaseSensitive=" + isCaseSensitive;
        return TRANSLATE_SERVICE_URL + API_URL;
    }

    private String getAuthorizationToken() {
        String API_KEY = "Basic ZWIxYWE4ZGMtMzRlMy00ZDllLTk3MTgtNzBmYzg1NzkzMjdjOjc2OGNkNzcwZDhjYTQyNDk5ZGYyYWIzYzY3ZDRhNGUy";
        String URL = TRANSLATE_SERVICE_URL + "api/v1.1/authenticate";

        HttpEntity<String> entity = getEntity("Authorization", API_KEY);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity,
                new ParameterizedTypeReference<>() {
                });

        return "Bearer " + responseEntity.getBody();
    }

    private HttpEntity<String> getEntity(String key, String value) {
        MultiValueMap<String, String> multiValueMap = new HttpHeaders();
        multiValueMap.add(key, value);

        HttpHeaders headers = new HttpHeaders(multiValueMap);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        return entity;
    }

}

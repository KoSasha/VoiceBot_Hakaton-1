package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebService {

    @Autowired
    RestTemplate restTemplate;

    public String getClientsAccounts(String token) {
        String response = restTemplate.exchange("https://internetbankmb.open.ru/webapi-2.1/persons/short-info/"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<String>() {
                }, token).getBody();
        return response;
    }

//    @SuppressWarnings("unused")
//    private String callUserService_Fallback(String group) {
//        return "Error! " + new Date();
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

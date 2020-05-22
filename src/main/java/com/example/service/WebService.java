package com.example.service;

import com.example.model.BalanceList;
import com.example.model.Content;
import com.example.model.PaymentDocument;
import com.example.model.StatementStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class WebService {

    @Autowired
    RestTemplate restTemplate;

    public final HttpEntity<String> addHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Authorization", "Bearer sso_1.0_eyAiY3R5IjogIkpXVCIsICJ0eXAiOiAiand0IiwgImFsZyI6ICJIUzI1NiIgfQ.eyAic3ViIjogInNzb19fX19fNTMwM2E2ZjMtNjdjZS00NjY1LWFkODktNTQ5Y2ZhNWU4ZmI4IiwgInZlciI6ICIxLjAiLCAiaXNzIjogImh0dHBzOi8vcnVtc2thcHQyNDEub3Blbi5ydS9zc28iLCAidG9rZW5OYW1lIjogImlkX3Rva2VuIiwgImlkX3Rva2VuIjogInNzb19fX19fZTlkYWUxNTMtYmRjZS00MTJlLThlMTctODIyOGFmNzJiMzVhIiwgImNsaWVudF9pZCI6ICJzbWVwb3J0YWxfbTJtIiwgImF1ZCI6IFsgInVybjpvaWQ6NWEzMGI0YTUtZGM3NS00ZGZlLWI5NDctNWNiN2UzYjEzNDEyIiBdLCAiYXRoIjogMTU5MDA4MjAyMTUxOSwgImF6cCI6ICJpbnRlZ3JhdGlvbnMiLCAiY2xhaW1zIjogeyAiYXV0aFR5cGUiOiAiYXBpdG9rZW5zIiwgInJvbGVzIjogWyAiUk9MRV9FSU8iIF0gfSwgInJlYWxtIjogIi9jdXN0b21lciIsICJzY29wZXMiOiBbICJPdHAiLCAiY291bnRlcnBhcnR50KFoZdGBayIsICJzYWxhcnkiLCAicGF5bWVudHNfc2lnbiIsICJQYXltZW50VmlldyIsICJzaG9ydEluZm8iLCAiU2FsYXJ5UmVnaXN0cnlTZW5kIiwgIlNhbGFyeVJlZ2lzdHJ5VmlldyIsICJtYWtlUGF5bWVudHMiLCAiUGF5bWVudFNpZ24iLCAiZ2V0U3RhdGVtZW50IiwgIlBheW1lbnRDcmVhdGUiIF0sICJleHAiOiAxNjIxNjE4MDIxNTE5LCAidG9rZW5UeXBlIjogIkFwaVRva2VuIiwgImlhdCI6IDE1OTAwODIwMjE1MTksICJhdXRoTGV2ZWwiOiBbICI1IiBdLCAianRpIjogInNzb19fX19fZTlkYWUxNTMtYmRjZS00MTJlLThlMTctODIyOGFmNzJiMzVhIiB9.xhj-hDaNpKUBeefaWwUOrCAWW0XyNbsKagfUFBWZ9os");
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        return entity;
    }

    public Content getClientsAccounts() throws IOException {
        final HttpEntity<String> entity = addHeaders();
        ResponseEntity<String> response = restTemplate.exchange("https://rumskapt241.open.ru/webapi-2.1/accounts", HttpMethod.GET, entity, String.class);
        Content content = getAccountContent(response);
        return content;
    }

    public StatementStatus requestStatementStatus(String accountId, String to) throws JsonProcessingException {
        final HttpEntity<String> entity = addHeaders();
        ResponseEntity<String> response = restTemplate.exchange("https://rumskapt241.open.ru/webapi-2.1/accounts/" + accountId + "/statement?format=JSON&from=2019-08-14&to=" + to, HttpMethod.POST, entity, String.class);
        StatementStatus statementStatus = getStatementStatus(response);
        return statementStatus;
    }

    public StatementStatus getStatementStatus(ResponseEntity<String> response) throws JsonProcessingException {
        String status = response.getBody().substring(8, response.getBody().length() - 1);
        StatementStatus statementStatus = new StatementStatus();
        statementStatus.fromJSON(status);
        return statementStatus;
    }

    // в этой функции кек
    public ArrayList<PaymentDocument> getPaymentDocumentList(String accountId, String statementId, BalanceList balance) throws JsonProcessingException {
        final HttpEntity<String> entity = addHeaders();
        Integer stId =  Integer.parseInt(statementId) + 1;
        ResponseEntity<String> response = restTemplate.exchange("https://rumskapt241.open.ru/webapi-2.1/accounts/" + accountId + "/statement/" + stId.toString() + "/print?print=true", HttpMethod.GET, entity, String.class);
        ArrayList<PaymentDocument> paymentDocuments = null;
        balance = getBalanceList(response);
        System.out.println(balance.getCreditAmountNatCurr());
        return paymentDocuments;
    }

    public BalanceList getBalanceList(ResponseEntity<String> response) throws JsonProcessingException {
        String[] arrayResponse = response.getBody().split("balanceList:");
        BalanceList balance = new BalanceList();
        balance.fromJSON(arrayResponse[1]);
        return balance;
    }

    public Content getAccountContent(ResponseEntity<String> response) throws JsonProcessingException {
        String[] arrayResponse = response.getBody().replace("[", "/").replace("]", "/").split("/");
        Content content = new Content();
        content.fromJSON(arrayResponse[1]);
        return content;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

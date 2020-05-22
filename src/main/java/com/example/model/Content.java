package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter@Getter
@AllArgsConstructor@NoArgsConstructor
public class Content implements JSON {
    private String accountId;

    private String accountStatus;

    private String amount;

    private String negativeBalance;

    private String currencyCode;

    private String title;

    @Override
    public void fromJSON(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Content content = mapper.readValue(json, Content.class);
        this.setAccountId(content.getAccountId());
        this.setAccountStatus(content.getAccountStatus());
        this.setAmount(content.getAmount());
        this.setCurrencyCode(content.getCurrencyCode());
        this.setNegativeBalance(content.getNegativeBalance());
        this.setTitle(content.getTitle());
    }
}

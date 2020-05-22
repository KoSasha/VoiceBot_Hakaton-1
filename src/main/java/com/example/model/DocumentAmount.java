package com.example.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Setter@Getter
@AllArgsConstructor@NoArgsConstructor
public class DocumentAmount implements JSON {
    private String amount;

    @Override
    public void fromJSON(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        DocumentAmount documentAmount = mapper.readValue(json, DocumentAmount.class);
        this.setAmount(documentAmount.getAmount());
    }
}

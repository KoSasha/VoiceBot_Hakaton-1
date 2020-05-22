package com.example.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Setter@Getter
@AllArgsConstructor@NoArgsConstructor
public class BeneficiaryInfo implements JSON {
    private String name;

    @Override
    public void fromJSON(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        BeneficiaryInfo beneficiaryInfo = mapper.readValue(json, BeneficiaryInfo.class);
        this.setName(beneficiaryInfo.getName());
    }
}

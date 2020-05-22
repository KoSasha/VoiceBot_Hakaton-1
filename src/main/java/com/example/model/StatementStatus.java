package com.example.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Setter@Getter
@AllArgsConstructor@NoArgsConstructor
public class StatementStatus implements JSON {
    private String fromDate;

    private String toDate;

    private String statementId;

    @Override
    public void fromJSON(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        StatementStatus statementStatus = mapper.readValue(json, StatementStatus.class);
        this.setFromDate(statementStatus.getFromDate());
        this.setToDate(statementStatus.getToDate());
        this.setStatementId(statementStatus.getStatementId());
    }
}

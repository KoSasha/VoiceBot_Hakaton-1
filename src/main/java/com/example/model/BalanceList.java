package com.example.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Setter@Getter
@AllArgsConstructor@NoArgsConstructor
public class BalanceList implements JSON {
    private String enterBalanceAmountNatCurr;

    private String outBalanceAmountNatCurr;

    private String debitAmountNatCurr;

    private String creditAmountNatCurr;

    @Override
    public void fromJSON(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        BalanceList balance = mapper.readValue(json, BalanceList.class);
        this.setEnterBalanceAmountNatCurr(balance.enterBalanceAmountNatCurr);
        this.setOutBalanceAmountNatCurr(balance.getOutBalanceAmountNatCurr());
        this.setDebitAmountNatCurr(balance.getDebitAmountNatCurr());
        this.setCreditAmountNatCurr(balance.creditAmountNatCurr);
    }
}

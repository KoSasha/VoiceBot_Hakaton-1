package com.example.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Setter@Getter
@AllArgsConstructor@NoArgsConstructor
public class PaymentDocument implements JSON {

    @JsonDeserialize(as = BeneficiaryInfo.class)
    private BeneficiaryInfo beneficiaryInfo;

    @JsonDeserialize(as = DocumentAmount.class)
    private DocumentAmount documentAmount;

    @Override
    public void fromJSON(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        PaymentDocument paymentDocument = mapper.readValue(json, PaymentDocument.class);
        this.setBeneficiaryInfo(paymentDocument.getBeneficiaryInfo());
        this.setDocumentAmount(paymentDocument.getDocumentAmount());
    }
}

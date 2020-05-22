package com.example.controller;

import com.example.model.BalanceList;
import com.example.model.Content;
import com.example.model.PaymentDocument;
import com.example.model.StatementStatus;
import com.example.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class WebController {

    @Autowired
    WebService service;

    @RequestMapping(value = "/amount", method = RequestMethod.GET)
    public String getClientsAccount() {
        try {
            Content content = service.getClientsAccounts();
            if (content != null) {
                if (content.getNegativeBalance() != null && !content.getNegativeBalance().equals(0)) {
                    return "{\"amount\":\"" + content.getNegativeBalance() + "\"}";
                } else {
                    return "{\"amount\":\"" + content.getAmount() + "\"}";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // в этой функции кек
    @RequestMapping(value = "/statement", method = RequestMethod.GET)
    public String getStatement(@RequestParam("to") String to) {
        try {
            Content content = service.getClientsAccounts();
            String accountId = content.getAccountId();
            StatementStatus statementStatus = service.requestStatementStatus(accountId, to);
            BalanceList balance = null;
            // вот здесь кек (в функции getPaymentDocumentList() не проходит запрос)
            ArrayList<PaymentDocument> paymentDocuments = service.getPaymentDocumentList(accountId
                    , statementStatus.getStatementId(), balance);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "10";
    }


}

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

    @RequestMapping(value = "/statement", method = RequestMethod.GET)
    public String getStatement(@RequestParam("to") String to) {
        try {
            Content content = service.getClientsAccounts();
            String accountId = content.getAccountId();
            StatementStatus statementStatus = service.requestStatementStatus(accountId, to);
            return "{\"accountId\":\"" + accountId + "\"statementId\":\"" + statementStatus.getStatementId() + "\"}";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(@RequestParam("accountId") String accountId, @RequestParam("statementId") String statementId) {
        try {
            BalanceList balance = service.getBalance(accountId, statementId);
            return balance.toJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/operations", method = RequestMethod.GET)
    public String getList(@RequestParam("countOperation") String countOperation) {
//        ArrayList<PaymentDocument> operations = service.getLastOperations();
//        String json = "[";
//        for (int i = 0; i <= Integer.parseInt(countOperation); i++) {
//            json += "{";
//            json += operations.get(i);
//            json += "},";
//        }
//        return json;
        return null;
    }


}

package com.example.controller;

import com.example.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @Autowired
    WebService service;

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String getClientsAccount(@PathVariable String token) {
        return service.getClientsAccounts(token);
    }

    @RequestMapping(value = "/amount", method = RequestMethod.GET)
    public String getCheck() {
        return "10";
    }

}

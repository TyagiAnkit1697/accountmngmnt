package com.sec.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactsController {
    @GetMapping("/contact")
    public String getContact(){
        return "Contacts details";
    }
}

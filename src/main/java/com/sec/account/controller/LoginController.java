package com.sec.account.controller;

import com.sec.account.model.Customer;
import com.sec.account.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    CustomerRepo repo;
    @PostMapping("/register")
    public ResponseEntity<String> newUser(@RequestBody Customer customer){
    Customer savedCustomer = null;
    ResponseEntity response = null;

    try {
        savedCustomer = repo.save(customer);
        response = (savedCustomer.getId()>0)?ResponseEntity.status(HttpStatus.CREATED)
                .body("User has been created successfully")
                :ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("someting went wrong");
    }catch (Exception e){
        response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An exception has occured due to "+ e.getMessage());
    }

        return response;
    }
}

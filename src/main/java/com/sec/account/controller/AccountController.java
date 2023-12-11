package com.sec.account.controller;

import com.sec.account.model.Accounts;
import com.sec.account.repo.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountsRepository accRepo;

    @GetMapping("/myAccount")
    public ResponseEntity<Accounts> getAccount(@RequestParam("id") int customerId){
        try {
            Accounts accounts = accRepo.findByCustomerId(customerId);
            return ResponseEntity.accepted().body(accounts);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }
}

package com.sec.account.repo;

import com.sec.account.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
     public List<Customer> findByEmail(String email);
}

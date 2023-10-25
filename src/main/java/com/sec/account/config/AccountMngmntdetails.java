package com.sec.account.config;

import com.sec.account.model.Customer;
import com.sec.account.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AccountMngmntdetails implements UserDetailsService {
    @Autowired
    CustomerRepo repo;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       String userName, password = null;
       List<GrantedAuthority> authorityList = null;
       List<Customer> customer = repo.findByEmail(username);
       if (customer.size() == 0){
           throw new  UsernameNotFoundException("user details not found for user "+username);
       }else {
           userName = customer.get(0).getEmail();
           password = customer.get(0).getPwd();
           authorityList = new ArrayList<>();
           authorityList.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
       }
       return new User(username, password, authorityList);
    }
}

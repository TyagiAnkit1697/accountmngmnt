package com.sec.account.config;

import com.sec.account.model.Customer;
import com.sec.account.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserPwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomerRepo repo;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String pwd = authentication.getCredentials().toString();
       List<Customer> info = repo.findByEmail(userName);
        if (info.size() > 0){
            if(encoder.matches(pwd, info.get(0).getPwd())){
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(info.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(userName, pwd, authorities);
            }
            else {
                throw new BadCredentialsException("invalid Password");
            }
        }
        else {
            throw new UsernameNotFoundException("No register user found with these details");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

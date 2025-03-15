package com.nt.config;

import com.nt.model.CustomUser;
import com.nt.model.UserInfo;
import com.nt.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserInfo user = userInfoRepo.findByUsername(username).orElseThrow(()->new RuntimeException());
        String encPwd = user.getPassword();
        boolean matches = encoder.matches(password, encPwd);
        if(matches){
            List<String> roleCodeList= Arrays.asList(user.getRole().getRoleCode());
            List<SimpleGrantedAuthority> authorites = roleCodeList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            CustomUser userDetails=new CustomUser(username,password,authorites,roleCodeList,username);
            return new UsernamePasswordAuthenticationToken(userDetails,password,authorites);
        }else{
         throw new RuntimeException("Authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}

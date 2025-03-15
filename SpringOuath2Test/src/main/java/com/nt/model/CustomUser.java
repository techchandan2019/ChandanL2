package com.nt.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class CustomUser extends User {
    private List<String> roles;
    private String name;
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities,List<String> roles,String name){
        super(username,password,authorities);
        this.roles=roles;
        this.name=name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

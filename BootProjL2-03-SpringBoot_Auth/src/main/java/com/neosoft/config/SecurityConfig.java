package com.neosoft.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("ckp").password("{noop}root").roles("MANAGER");
		auth.inMemoryAuthentication().withUser("raja").password("{noop}rani").roles("CLERK");
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/offer").hasAnyRole("CLERK","MANAGER")
		.antMatchers("/price").hasRole("MANAGER")
//		.and().httpBasic();
		.and().formLogin()
		.and().logout();
	}
	
}

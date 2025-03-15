package com.nt.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nt.model.CustomUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract the token part after "Bearer "
            String token = authorizationHeader.substring(7);

            if (token != null) {
                DecodedJWT decodedJWT= JWT.decode(token);
                Claim userName = decodedJWT.getClaim("user_name");
                String stringUsername=userName.asString();
                List<String> authorities = extractFromClaims(decodedJWT, "authorities");
                List<String> roles = extractFromClaims(decodedJWT, "roles");

                List<SimpleGrantedAuthority> grantedAuthority = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                CustomUser userDetails = new CustomUser(stringUsername,"",grantedAuthority,authorities,stringUsername);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, grantedAuthority);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);

    }
    private List<String> extractFromClaims(DecodedJWT decodedJWT, String key){

        List<String> roles=new ArrayList<>();
        Claim rolesClaim = decodedJWT.getClaim(key);
        if(!rolesClaim.isNull()){
            roles = rolesClaim.asList(String.class);
        }
        return roles;
    }
}

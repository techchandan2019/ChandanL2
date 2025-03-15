package com.nt.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nt.model.CustomUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomTokenStore extends JwtTokenStore {

    public CustomTokenStore(JwtAccessTokenConverter jwtAccessTokenConverter){
        super(jwtAccessTokenConverter);
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        OAuth2Authentication authentication = super.readAuthenticationForRefreshToken(token);

        if(authentication != null && authentication.getUserAuthentication()!=null){
            DecodedJWT decodedJWT= JWT.decode(((DefaultOAuth2RefreshToken)token).getValue());
            Claim userName = decodedJWT.getClaim("user_name");
            String stringUsername=userName.asString();
            List<String> authorities = extractFromClaims(decodedJWT, "authorities");
            List<String> roles = extractFromClaims(decodedJWT, "roles");

            List<SimpleGrantedAuthority> grantedAuthority = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            CustomUser userDetails = new CustomUser(stringUsername,"",grantedAuthority,authorities,stringUsername);
            authentication=new OAuth2Authentication(authentication.getOAuth2Request(),
                                                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()));
        }
        return authentication;
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

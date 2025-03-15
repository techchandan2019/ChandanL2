package com.nt.config;


import com.nt.model.CustomUser;
import com.nt.model.Oauth2ConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private Oauth2ConfigurationProperties oauth2ConfigurationProperties;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Autowired
    private ClientDetailsService clientDetailsService;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory() // Use your database to fetch client details
                .withClient("admin")
//                .secret("{noop}admin123") // Plaintext for demo, use {bcrypt} for hashed passwords
                .secret(encoder.encode("admin123")) // Plaintext for demo, use {bcrypt} for hashed passwords
                .accessTokenValiditySeconds(30000)
                .refreshTokenValiditySeconds(60000)
                .authorizedGrantTypes("password","refresh_token")
                .scopes("read", "write")
                .resourceIds();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenServices(tokenServices());
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setTokenEnhancer(tokenEnhancerChain());
        return tokenServices;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
    }

    @Bean
    public TokenEnhancerChain tokenEnhancerChain(){
        final TokenEnhancerChain tokenEnhancerChain=new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new AmbaTokenEnhancer(),jwtAccessTokenConverter()));
        return tokenEnhancerChain;
    }

    private static class AmbaTokenEnhancer implements TokenEnhancer{

        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
            CustomUser customUser=(CustomUser) oAuth2Authentication.getPrincipal();

            Map<String,Object> map=new HashMap<>();
            map.put("roles",customUser.getRoles());
            map.put("name",customUser.getName());
            ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(map);
            return oAuth2AccessToken;
        }
    }

    @Bean
    public TokenStore tokenStore() {
        // Define the token store; here we're using an in-memory token store
        return new CustomTokenStore(jwtAccessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter=new JwtAccessTokenConverter();
        converter.setSigningKey("admin");
        return converter;
    }
}

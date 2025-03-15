package com.nt.controller;

import com.nt.model.Oauth2ConfigurationProperties;
import com.nt.model.Role;
import com.nt.model.UserInfo;
import com.nt.repository.RoleInfoRepo;
import com.nt.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.management.relation.RoleInfo;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private RoleInfoRepo roleInfoRepo;

    @Autowired
    private BCryptPasswordEncoder encode;

    @Autowired
    private Oauth2ConfigurationProperties oauth2ConfigurationProperties;


    @PostMapping("/getToken")
    public ResponseEntity<Map<String,Object>> getToken(@RequestParam String username, @RequestParam String password) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "password");
            map.add("username", username);
            map.add("password", password);

            String credentials = "admin" + ":" + "admin123";
            String base64Cred = Base64.getEncoder().encodeToString(credentials.getBytes());
            headers.set("Authorization", "Basic " + base64Cred);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

            ResponseEntity<Map> response = restTemplate.exchange(oauth2ConfigurationProperties.getTokenUri(), HttpMethod.POST, entity, Map.class);

            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        }catch (Exception e){
            Map<String,Object> map=new HashMap<>();
            map.put("error",e.getMessage());
            return new ResponseEntity<>(map,HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String username,@RequestParam String password,@RequestParam Integer roleId) {

        try{
            UserInfo userInfo=new UserInfo();
            userInfo.setUsername(username);
            userInfo.setPassword(encode.encode(password));

            Role role = roleInfoRepo.findById(roleId).orElseThrow(() -> new RuntimeException("RoleId not Found"));
            userInfo.setRole(role);

            userInfoRepo.save(userInfo);
            return new ResponseEntity<>("Successfully register the User",HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to register the user,due to "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/registerRole")
    public ResponseEntity<String> registerRole(@RequestParam String roleCode) {

        try{
          Role role=new Role();
          role.setRoleCode(roleCode);
          role.setRoleName(roleCode);
          roleInfoRepo.save(role);
          return new ResponseEntity<>("Successfully saved the Role",HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to saved the role,due to "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }


}


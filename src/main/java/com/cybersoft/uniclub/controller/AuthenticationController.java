package com.cybersoft.uniclub.controller;


import com.cybersoft.uniclub.payload.response.BaseResponse;
import com.cybersoft.uniclub.services.AuthenticationServices;
import com.cybersoft.uniclub.utils.JwtHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServices authenticationServices;

    @Autowired
    private JwtHelper jwtHelper;

    /*
    * {
    *    "code":200,
    *    "message":"Success",
    *    "data": true
    *
    * }
    *
    *
    * */



    @PostMapping("/signin")
  public ResponseEntity<?> signin(@RequestParam String email,
                                  @RequestParam String password) {

//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//  String data= jwtHelper.decodeToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aMO0bmcgdGluIGPhuqduIGzGsHUgdHJvbmcgdG9rZW46IHVzZXJuYWVtLCBlbWFpbCJ9.ar07DHLgXTriMOc1eWlxDa50wl3LPunP6DAtlDQ0LEg");
//  System.out.println("data: "+ data);
        String token = authenticationServices.checkLogin(email, password);
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setMessage("Success");
        response.setData(token);

      return ResponseEntity.ok(response);
  }
}





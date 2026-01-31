package com.cybersoft.uniclub.controller;

import com.cybersoft.uniclub.payload.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @PostMapping
    public ResponseEntity<?>insertProduct() {
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setMessage("Insert product success");
        response.setData(null);
        return ResponseEntity.ok(response);

    }

}

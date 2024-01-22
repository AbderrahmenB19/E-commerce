package com.jwtyt.ecommerce.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping

public class AuthTest {
    @GetMapping("/forUser")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> H(){
        return new ResponseEntity<String>("dd", HttpStatus.OK);
    }
}

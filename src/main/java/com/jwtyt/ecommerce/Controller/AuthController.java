package com.jwtyt.ecommerce.Controller;

import com.jwtyt.ecommerce.Dto.AuthResponseDto;
import com.jwtyt.ecommerce.Dto.LoginDto;
import com.jwtyt.ecommerce.Dto.RegisterDto;
import com.jwtyt.ecommerce.Models.Role;
import com.jwtyt.ecommerce.Models.UserEntity;
import com.jwtyt.ecommerce.Repository.RolerRepo;
import com.jwtyt.ecommerce.Repository.UserRepo;
import com.jwtyt.ecommerce.Security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class AuthController {
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final RolerRepo rolerRepo;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody RegisterDto registerDto){

        if(userRepo.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("userIsTAken", HttpStatus.BAD_REQUEST);
        }
        UserEntity user =new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(Collections.singletonList(rolerRepo.findByName("ADMIN").get()));
        userRepo.save(user);
        return new ResponseEntity<>("goood", HttpStatus.OK);

    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if(userRepo.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("userIsTAken", HttpStatus.BAD_REQUEST);
        }
        UserEntity user=new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = rolerRepo.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));


        userRepo.save(user);
        return new ResponseEntity<>("user Registered",HttpStatus.OK);


    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> Login(@RequestBody LoginDto loginDto){
        Authentication authentication= authenticationManager.
                authenticate(
                        new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword())
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.Generate(authentication);
        
        return new ResponseEntity<>(new AuthResponseDto(token,userRepo.findByUsername(loginDto.getUsername()).get().getRoles()),HttpStatus.OK);
    }



}

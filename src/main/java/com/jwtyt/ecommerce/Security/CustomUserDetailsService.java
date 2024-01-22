package com.jwtyt.ecommerce.Security;

import com.jwtyt.ecommerce.Models.Role;
import com.jwtyt.ecommerce.Models.UserEntity;
import com.jwtyt.ecommerce.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user =userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user Not found"));
        return new User(user.getUsername(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }
    public Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(e-> new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList());
    }
}

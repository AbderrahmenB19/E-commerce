package com.jwtyt.ecommerce.Repository;

import com.jwtyt.ecommerce.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolerRepo extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(String name);
}

package com.jwtyt.ecommerce.Repository;

import com.jwtyt.ecommerce.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

    void deleteById(int id);
}

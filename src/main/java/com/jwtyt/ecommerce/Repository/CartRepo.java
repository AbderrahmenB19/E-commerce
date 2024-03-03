package com.jwtyt.ecommerce.Repository;

import com.jwtyt.ecommerce.Models.Carts;
import com.jwtyt.ecommerce.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CartRepo extends JpaRepository<Carts,Integer> {
    public List<Carts> findByUserEntity(UserEntity userEntity);
}

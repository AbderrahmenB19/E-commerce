package com.jwtyt.ecommerce.Repository;

import com.jwtyt.ecommerce.Models.OrderDetail;
import com.jwtyt.ecommerce.Models.Product;
import com.jwtyt.ecommerce.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo  extends JpaRepository<OrderDetail,Integer> {
    Optional<Object> findByProductAndUser(Product product, UserEntity user);
}

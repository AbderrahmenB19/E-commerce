package com.jwtyt.ecommerce.Service;

import com.jwtyt.ecommerce.Dto.OrderInput;
import com.jwtyt.ecommerce.Dto.OrderProductQuantity;
import com.jwtyt.ecommerce.Models.Carts;
import com.jwtyt.ecommerce.Models.OrderDetail;
import com.jwtyt.ecommerce.Models.Product;
import com.jwtyt.ecommerce.Models.UserEntity;
import com.jwtyt.ecommerce.Repository.CartRepo;
import com.jwtyt.ecommerce.Repository.OrderRepo;
import com.jwtyt.ecommerce.Repository.ProductRepo;
import com.jwtyt.ecommerce.Repository.UserRepo;
import com.jwtyt.ecommerce.Security.JwtFilter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service

public class OrderDetailService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final CartRepo cartRepo;
    private static final String ORDER_PLACED = "placed";


    @Transactional
    public void placeOrder(OrderInput orderInput, boolean isCartCheckout) {
        List<OrderProductQuantity> orderProductQuantities = orderInput.getOrderProductQuantities();
        for (OrderProductQuantity o : orderProductQuantities) {
            Product product = productRepo.findById(o.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
            String currentUsername = JwtFilter.CURRENT_USER;
            UserEntity user = userRepo.findByUsername(currentUsername).orElseThrow(() -> new RuntimeException("User not found"));

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContact(),
                    ORDER_PLACED,
                    product.getProductActualPrice() * o.getProductQuantity(),
                    product,
                    user
            );
            if( isCartCheckout){
                List<Carts> carts= cartRepo.findByUserEntity(user);
                carts.forEach(x->cartRepo.deleteById(x.getCartId()));

            }
            orderRepo.save(orderDetail);
        }
    }
}
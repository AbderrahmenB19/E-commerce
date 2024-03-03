package com.jwtyt.ecommerce.Controller;

import com.jwtyt.ecommerce.Dto.OrderInput;
import com.jwtyt.ecommerce.Service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @PostMapping("/placeOrder/{isCartCheckout}")
    public ResponseEntity<String> placeOrder(@PathVariable(name = "isCartCheckout") boolean isCartCheckout, @RequestBody OrderInput orderInput){
        orderDetailService.placeOrder(orderInput,isCartCheckout);
        return new ResponseEntity<>("greaaaaaaaaaaat", HttpStatus.OK);

    }

}

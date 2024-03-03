package com.jwtyt.ecommerce.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderInput {
    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private String alternateContact;
    private String transactionId;
    private List<OrderProductQuantity> orderProductQuantities;

}

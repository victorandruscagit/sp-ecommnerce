package com.andrusca.ecommerce.dto;

import com.andrusca.ecommerce.entity.Address;
import com.andrusca.ecommerce.entity.Customer;
import com.andrusca.ecommerce.entity.Order;
import com.andrusca.ecommerce.entity.OrderItem;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address  shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}

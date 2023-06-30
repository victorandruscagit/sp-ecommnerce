package com.andrusca.ecommerce.service;

import com.andrusca.ecommerce.dao.CustomerRepository;
import com.andrusca.ecommerce.dto.Purchase;
import com.andrusca.ecommerce.dto.PurchaseResponse;
import com.andrusca.ecommerce.entity.Customer;
import com.andrusca.ecommerce.entity.Order;
import com.andrusca.ecommerce.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements  CheckoutService{

    @Autowired
    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        /** retrieve the order info from DTO */
        Order order = purchase.getOrder();

        /** generate tracking number */
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        /** populate order with order Items */
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        /** populate order with billing and shipping  address */
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        /**poupulate customer with order */
        Customer customer = purchase.getCustomer();
        customer.add(order);

        /**save to the database */
        customerRepository.save(customer);

        /**return a response */
        return  new PurchaseResponse(orderTrackingNumber);

    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}

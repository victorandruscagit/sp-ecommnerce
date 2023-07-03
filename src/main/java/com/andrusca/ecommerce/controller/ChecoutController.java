package com.andrusca.ecommerce.controller;

import com.andrusca.ecommerce.dto.Purchase;
import com.andrusca.ecommerce.dto.PurchaseResponse;
import com.andrusca.ecommerce.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.PagedResultsControl;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class ChecoutController {

    private CheckoutService checkoutService;

    public ChecoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        return  purchaseResponse;

    }







}

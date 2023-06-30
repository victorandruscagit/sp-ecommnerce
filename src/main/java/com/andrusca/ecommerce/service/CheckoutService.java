package com.andrusca.ecommerce.service;

import com.andrusca.ecommerce.dto.Purchase;
import com.andrusca.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}

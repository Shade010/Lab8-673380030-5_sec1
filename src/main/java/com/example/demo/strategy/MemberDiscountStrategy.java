package com.example.demo.strategy;

import org.springframework.stereotype.Component;

@Component
public class MemberDiscountStrategy implements DiscountStrategy {
    private static final double DISCOUNT_RATE = 0.10;

    @Override
    public Double applyDiscount(Double originalPrice) {
        return originalPrice - (originalPrice * DISCOUNT_RATE);
    }
}
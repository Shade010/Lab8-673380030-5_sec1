package com.example.demo.strategy;

import org.springframework.stereotype.Component;

@Component
public class DiscountContext {

    private final NoDiscountStrategy noDiscountStrategy;
    private final MemberDiscountStrategy memberDiscountStrategy;
    private final SeasonalSaleStrategy seasonalSaleStrategy;

    public DiscountContext(NoDiscountStrategy noDiscountStrategy,
                            MemberDiscountStrategy memberDiscountStrategy,
                            SeasonalSaleStrategy seasonalSaleStrategy) {
        this.noDiscountStrategy = noDiscountStrategy;
        this.memberDiscountStrategy = memberDiscountStrategy;
        this.seasonalSaleStrategy = seasonalSaleStrategy;
    }

    public DiscountStrategy getStrategy(String discountType) {
        if (discountType == null) return noDiscountStrategy;
        switch (discountType) {
            case "MEMBER":
                return memberDiscountStrategy;
            case "SEASONAL":
                return seasonalSaleStrategy;
            default:
                return noDiscountStrategy;
        }
    }

    public Double calculateFinalPrice(Double originalPrice, String discountType) {
        DiscountStrategy strategy = getStrategy(discountType);
        return strategy.applyDiscount(originalPrice);
    }
}
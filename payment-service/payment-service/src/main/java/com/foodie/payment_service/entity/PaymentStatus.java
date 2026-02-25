package com.foodie.payment_service.entity;

public enum PaymentStatus {
    CREATED,
    REQUIRES_PAYMENT_METHOD,
    PROCESSING,
    SUCCESS,
    FAILED,
    CANCELLED,
    REFUNDED
}

package com.example.demo.mapper;

import com.example.demo.dto.PaymentDto2;
import com.example.demo.model.Payment;
import com.example.demo.types.Currency;

public class PaymentMapper {

    public static PaymentDto2 toDto(Payment payment) {
        PaymentDto2 paymentDto = new PaymentDto2();
        paymentDto.setIdPayments(payment.getIdPayments());
        paymentDto.setPaymentStatus(payment.getPaymentStatus());
        paymentDto.setPaymentDate(payment.getPaymentDate());
        paymentDto.setStripePaymentId(payment.getStripePaymentId());
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setDescription(payment.getDescription());
        paymentDto.setCurrency(payment.getCurrency());
        return paymentDto;
    }

    public static Payment toModel(PaymentDto2 paymentDto) {
        Payment payment = new Payment();
        payment.setIdPayments(paymentDto.getIdPayments());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        payment.setStripePaymentId(paymentDto.getStripePaymentId());
        payment.setAmount(paymentDto.getAmount());
        payment.setDescription(paymentDto.getDescription());
        if (paymentDto.getCurrency() != null) {
            payment.setCurrency(Currency.valueOf(paymentDto.getCurrency().name()));
        }
        return payment;
    }

}

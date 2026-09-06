package com.RazorpayProject.Razorpay.payment.entity;

import com.RazorpayProject.Razorpay.common.entity.Money;
import com.RazorpayProject.Razorpay.common.exception.enums.PaymentMethod;
import com.RazorpayProject.Razorpay.common.exception.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy=jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="order_id",nullable=false)//Many payment to one order
    private OrderRecord order;

    @Column(nullable = false)
    private UUID merchantId;//no unique contarint as a merchant can have many [ayents, once if the merchant has done the payment and it is unique fort he same merchant we cannot do another payment and that is not expected.

    @Embedded
    private Money amount;

    @Column(nullable=false, length=100)
    private String idempotencyKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=30)
    private PaymentStatus status;

    @Column(nullable = false)
    private PaymentMethod method;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name="method_details", columnDefinition="jsonb")
    private Map<String, Object> methodDetails;
    //each payment way will have its own details so this will store those


    @Column(length=100)
    private String bankReference;  //UTR information that we can get from the payment

    @Column(length=100)
    private String errorCode;

    @Column(length=255)
    private String errorDescription;

    private LocalDateTime authorizedAt;

    private LocalDateTime capturedAt;

    private LocalDateTime failedAt;

    private LocalDateTime refundedAt;

    private LocalDateTime settledAt;
}

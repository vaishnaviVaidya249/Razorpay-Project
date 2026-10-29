package com.RazorpayProject.Razorpay.payment.entity;

import com.RazorpayProject.Razorpay.common.entity.Money;
import com.RazorpayProject.Razorpay.common.exception.enums.RefundStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="refund")
public class Refund {

    @Id
    @GeneratedValue(strategy=jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="payment_id", nullable=false)
    private Payment payment;
    //partial and full refund or refund may fail and we have to rasie another refund so these are the scenarios saying there may be many refunds for a payment.

    @Column(nullable=false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private RefundStatus status =RefundStatus.PENDING; //refund will have its own lifecycle so we will create a enum.

    @Column(length=100)
    private String bankReference;

    @Column(length=100)
    private String errorCode;

    @Column(length=500)
    private String errorDescription;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> notes; //the merchant can attach a note of why they are issuing this refund.

    private LocalDateTime processedAt;

    private LocalDateTime createdAt;
}

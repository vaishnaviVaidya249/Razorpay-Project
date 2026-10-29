package com.RazorpayProject.Razorpay.operations;

import com.RazorpayProject.Razorpay.common.exception.enums.WebhookEventStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="webhook_event")
public class WebhookEvent {
    //for logging purposes we store it in db even though present in redis

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private UUID merchantId;

    @Column(nullable=false, length=100)
    private String eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> payload;//that we put inside the event saying something happened

    @Column(nullable=false)
    private String targetUrl;

    @Column(nullable=false)
    private String signature;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false)
    private WebhookEventStatus status;

    @Column(nullable=false)
    private Integer attempts;

    private LocalDateTime nextRetryAt;

    private LocalDateTime lastAttemptAt;

    private Integer lastResponseCode;

    @Column(length=100)
    private Integer lastResponseBody;

    private LocalDateTime deliveredAt;
}

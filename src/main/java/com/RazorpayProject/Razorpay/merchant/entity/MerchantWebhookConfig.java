package com.RazorpayProject.Razorpay.merchant.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="merchant_webhook_config")
public class MerchantWebhookConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="merchant_id", nullable=false)
    private Merchant merchant;

    @Column(nullable=false, length=500)
    private String targetUrl;

    @Column(length=500)
    private String webhookSecretHash;

    @Column(length=255)
    private String eventTypes;
    //comma separated event types to subscribe to

    @Column(nullable=false)
    private Boolean enabled=true;
}

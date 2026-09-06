package com.RazorpayProject.Razorpay.merchant.entity;

import com.RazorpayProject.Razorpay.common.exception.enums.Environment;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="api_key")
public class ApiKey {  //api key will store the key in their backend to communicate with razorpay
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="merchant_id",nullable=false)
    private Merchant merchant;

    @Column(nullable=false,length=50,unique=true)
    private String keyId;

    @Column(nullable=false,length=200)
    private String keySecretHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=10)
    private Environment environment;

    @Column(nullable=false)
    private boolean enabled=true;


    private LocalDateTime lastUsedAt;
    private LocalDateTime rotatedAt;
    private LocalDateTime gracePeriodExpiresAt;
}

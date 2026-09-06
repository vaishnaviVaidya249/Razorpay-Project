package com.RazorpayProject.Razorpay.merchant.entity;

import com.RazorpayProject.Razorpay.common.exception.enums.UserRole;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY) //will give only appuser or if it is eager it will give the data about merchant as well with appuser
    @JoinColumn(name="merchant_id")
    private Merchant merchant; //Many App user can have only one merchant

    @Column(nullable=false,unique=true)
    private String email;

    @Column(nullable=false)
    private String passwordHash;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
}

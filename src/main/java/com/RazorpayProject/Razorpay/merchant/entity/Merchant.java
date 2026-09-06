package com.RazorpayProject.Razorpay.merchant.entity;

import com.RazorpayProject.Razorpay.common.exception.enums.BusinessType;
import com.RazorpayProject.Razorpay.common.exception.enums.MerchantStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="merchant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id; //gives us universally unique id

    @Column(nullable=false,length=200)
    private String name;

    @Column(unique=true)
    private String email;

    @Column(length=20)
    private String contactNumber;

    @Column(length=50)
    @Enumerated(EnumType.STRING)
    private BusinessType BusinessType;

    @Column(length=100)
    private String businessName;

    @Column(length=200)
    private String websiteUrl;

    @Column(length=200, nullable = false)
    @Enumerated(EnumType.STRING)
    private MerchantStatus status=MerchantStatus.PENDING_KYC;

    @Column(length=20)
    private String gstId;

    @Column(length=20)
    private String panId;


    @Column(length=200)
    private String settlementBankAccount;

    @Column(length=20)
    private String settlementBankIfsc;

    @Column(length=200)
    private String settlementBankAccountHolderName;
}

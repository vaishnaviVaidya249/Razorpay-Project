package com.RazorpayProject.Razorpay.vault.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="vault_card")
public class VaultCard {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(nullable=false, length=4)
    private String lastFour;

    @Column(nullable=false, length=6)
    private String bin;
//bin and last four digits are stored for user experince because if the user has
// already once entered their card detail we will show them the next time they want
// to do the payment through this card.without entering again.

    @Column(nullable=false)
    private byte[] encryptedPan;
    //storing in bytes array because the pan is encrypted with algorithm which spits out an array of bytes.
    //and also the array of byte takes less space

    @Column(nullable=false)
    private byte[] encryptedDek;
    //script used to encrypt the pan is called the dek, generated using random algorithm

    @Column(nullable=false)
    private String brand; //rupay, mastercard, Visa card brands

    @Column(nullable=false)
    private String expiryMonth;

    @Column(nullable=false)
    private String expiryYear;

    @Column(nullable=false)
    private String cardHolderName;

    private LocalDateTime deletedAt;
}

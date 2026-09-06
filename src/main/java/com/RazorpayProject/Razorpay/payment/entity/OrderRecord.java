package com.RazorpayProject.Razorpay.payment.entity;

import com.RazorpayProject.Razorpay.common.entity.Money;
import com.RazorpayProject.Razorpay.common.exception.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="order_record")
public class OrderRecord {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    //no FK - cross service boundary  means This order belongs to merchant ABC123,
    // but the Merchant object/data is managed by another service, so I only store its ID here.
    //And "no FK" does not mean there is no relationship conceptually.
    // There is still a logical relationship between an order and a merchant;
    // there is just no database foreign-key constraint or JPA entity relationship.
    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private OrderStatus orderstatus=OrderStatus.CREATED;

    @Column(nullable=false)
    private Integer attempts=0;

    @JdbcTypeCode((SqlTypes.JSON))  //used to covert the json stored in the db to map
    @Column(columnDefinition="jsonb")  //used to mention the datatype
    private Map<String, Object> notes;

    @Column(nullable=false)
    private LocalDateTime expiredAt;



}

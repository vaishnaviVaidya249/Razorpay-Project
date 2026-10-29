package com.RazorpayProject.Razorpay.operations.entity;

import com.RazorpayProject.Razorpay.common.entity.Money;
import com.RazorpayProject.Razorpay.common.exception.enums.SettlementStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="settlement")
public class Settlement {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private UUID merchantId;

    @Embedded //used where we have to use same embedded column name again and again
    @AttributeOverrides({
            //So in the Money class we have column name amountUnit but we want to override with new column property
            @AttributeOverride(name="amountUnits", column=@Column(name="gross_amount_units", nullable=false)),
            @AttributeOverride(name="currency", column=@Column(name="gross_amount_currency", nullable=false))
    })
    private Money grossAmount;//total settlement amount that merchant received

    @Embedded //used where we have to use same embedded column name again and again
    @AttributeOverrides({
            //So in the Money class we have column name amountUnit but we want to override with new column property
            @AttributeOverride(name="amountUnits", column=@Column(name="refund_amount_units", nullable=false)),
            @AttributeOverride(name="currency", column=@Column(name="refund_amount_currency", nullable=false))
    })
    private Money refundAmount;//amount that merchant refunded that day

    @Embedded //used where we have to use same embedded column name again and again
    @AttributeOverrides({
            //So in the Money class we have column name amountUnit but we want to override with new column property
            @AttributeOverride(name="amountUnits", column=@Column(name="gst_amount_units", nullable=false)),
            @AttributeOverride(name="currency", column=@Column(name="gst_amount_currency", nullable=false))
    })
    private Money gstAmount;//gst amount that we charge usually 18 percent

    @Embedded //used where we have to use same embedded column name again and again
    @AttributeOverrides({
            //So in the Money class we have column name amountUnit but we want to override with new column property
            @AttributeOverride(name="amountUnits", column=@Column(name="fee_amount_units", nullable=false)),
            @AttributeOverride(name="currency", column=@Column(name="fee_amount_currency", nullable=false))
    })
    private Money feeAmount;//on the fee we charge the gst charge

    @Embedded //used where we have to use same embedded column name again and again
    @AttributeOverrides({
            //So in the Money class we have column name amountUnit but we want to override with new column property
            @AttributeOverride(name="amountUnits", column=@Column(name="net_amount_units", nullable=false)),
            @AttributeOverride(name="currency", column=@Column(name="net_amount_currency", nullable=false))
    })
    private Money netAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private SettlementStatus status;

    @Column(nullable=false, length=50)
    private String bankReference;

    private LocalDateTime processedAt;
}

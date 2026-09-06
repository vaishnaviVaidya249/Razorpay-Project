package com.RazorpayProject.Razorpay.common.entity;

import jakarta.persistence.Embeddable;

@Embeddable    //used to create a class which can be reused in diff classes, "reusable table"
public class Money {

    private int amountUnits;
    private String currency;

    private Money(int amountUnits, String currency){
        this.amountUnits=amountUnits;
        this.currency=currency;
    }

    protected Money(){

    }

    public static Money of(int amountUnits, String currency){

        return new Money(amountUnits, currency);
    }

    public static Money inr(int amountUnits){
        return new Money(amountUnits,"INR");
    }

    public Money add(Money other){
        if(!this.currency.equals(other.currency)){
            throw new IllegalArgumentException("Cannot add money with different currencies");
        }
        return new Money(this.amountUnits+other.amountUnits, this.currency);

    }

    public Money subtract(Money other){
        if(!this.currency.equals(other.currency)){
            throw new IllegalArgumentException("Cannot subtract money with different currencies");
        }
        return new Money(this.amountUnits-other.amountUnits, this.currency);

    }
}

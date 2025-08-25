package com.lldproject.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private Date paymentDate;
    private String referenceNumber;
    private double amount;
    @Enumerated
    private PaymentStatus paymentStatus;
    @Enumerated
    private PaymentMode paymentMode;
    @ManyToOne
    private Booking booking;
}

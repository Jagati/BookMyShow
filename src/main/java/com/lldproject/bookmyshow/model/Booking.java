package com.lldproject.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    private Date bookingDate;
    private int numSeats;
    @ManyToOne
    private User bookedBy;
    @ManyToOne
    private Show show;
    @ManyToMany
    private List<ShowSeat> bookedSeats;
    private int totalAmount;
    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;
    @Enumerated
    private BookingStatus bookingStatus;
}

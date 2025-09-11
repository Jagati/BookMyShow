package com.lldproject.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String seatNumber;
    private int seatRow;
    private int seatCol;

//    @ManyToOne(fetch = FetchType.EAGER)
//    private Screen screen;
//    @ManyToOne
//    private SeatType seatType;
}

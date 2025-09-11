package com.lldproject.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    @ManyToOne(fetch = FetchType.EAGER)
    private Show show;
    @ManyToOne(fetch = FetchType.EAGER)
    private Seat seat;
    @Enumerated
    private ShowSeatStatus status;
    private Date blockedAt;
    @ManyToOne
    @Enumerated
    private ShowSeatType showSeatType;
}

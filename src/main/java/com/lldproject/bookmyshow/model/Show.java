package com.lldproject.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name="shows")
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    private Date showDate;
    private Time startTime;
    @ManyToOne
    private Screen screen;
    @OneToMany
    List<ShowSeat> showSeats;
    @OneToMany
    List<ShowSeatType> showSeatTypes;




}

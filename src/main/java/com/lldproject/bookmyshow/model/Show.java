package com.lldproject.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name="shows")
public class Show extends BaseModel{
    @ManyToOne(fetch = FetchType.EAGER)
    private Movie movie;
    private Date showDate;
    private Date startTime;
    @ManyToOne(fetch = FetchType.EAGER)
    private Screen screen;
    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats;
    @OneToMany(mappedBy = "show")
    private List<ShowSeatType> showSeatTypes;
    private Language language;
    private Features feature;




}

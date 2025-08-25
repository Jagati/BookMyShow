package com.lldproject.bookmyshow.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Screen extends BaseModel {
    private String screenNumber;
    @OneToMany
    private List<Seat> seats;
    @Enumerated
    @ElementCollection
    private List<Features> screenFeatures;
    @ManyToOne
    private Theatre theatre;
}

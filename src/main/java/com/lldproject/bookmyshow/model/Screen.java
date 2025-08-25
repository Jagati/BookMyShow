package com.lldproject.bookmyshow.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.util.List;

public class Screen extends BaseModel {
    private String screenName;
    private List<Seat> seats;
    @Enumerated
    @ElementCollection
    private List<Features> screenFeatures;
}

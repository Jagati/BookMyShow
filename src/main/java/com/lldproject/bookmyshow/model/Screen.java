package com.lldproject.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private String screenNumber;
    @OneToMany
    @JoinColumn(name="screen_id")
    private List<Seat> seats;
    @Enumerated
    @ElementCollection
    private List<Features> screenFeatures;
    @ManyToOne
    private Theatre theatre;
}

package com.lldproject.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity(name="users")
public class User extends BaseModel{
    private String name;
    private String email;//User:Bookings 1:M
    @OneToMany(mappedBy = "bookedBy")
    private List<Booking> bookings;
}

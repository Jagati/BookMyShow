package com.lldproject.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserRating extends BaseModel{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id", nullable=false)
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    private int rating; // 1 to 5 stars
}

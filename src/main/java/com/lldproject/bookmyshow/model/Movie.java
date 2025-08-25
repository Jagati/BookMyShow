package com.lldproject.bookmyshow.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String title;
    private String director;
    private String year;
    private String genre;
    @Enumerated
    private Rating rating;
    @Enumerated
    @ElementCollection
    private List<Features> availableFeatures;
    @Enumerated
    @ElementCollection
    private List<Language> languages;

}

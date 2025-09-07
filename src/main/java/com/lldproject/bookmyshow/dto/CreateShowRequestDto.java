package com.lldproject.bookmyshow.dto;

import com.lldproject.bookmyshow.model.Features;
import com.lldproject.bookmyshow.model.Language;
import com.lldproject.bookmyshow.model.ShowSeatPricing;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateShowRequestDto {
    private long created_by;
    private long screen_id;
    private long movie_id;
    private Date start_time;
    private Date end_time;
    private Language language;
    private Features supported_feature;
    private List<ShowSeatPricing> seatPricing;

}

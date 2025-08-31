package com.lldproject.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDto {
    private Long bookingId;
    private ResponseStatus responseStatus;
    private String message;
}

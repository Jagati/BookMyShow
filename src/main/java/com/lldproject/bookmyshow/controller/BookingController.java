package com.lldproject.bookmyshow.controller;

import com.lldproject.bookmyshow.dto.BookTicketRequestDto;
import com.lldproject.bookmyshow.dto.BookTicketResponseDto;
import com.lldproject.bookmyshow.dto.ResponseStatus;
import com.lldproject.bookmyshow.model.Booking;
import com.lldproject.bookmyshow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    public BookTicketResponseDto BookTicket(BookTicketRequestDto bookTicketRequestDto){
        BookTicketResponseDto bookTicketResponseDto = new BookTicketResponseDto();
        try{
            Booking booking = bookingService.bookTicket(bookTicketRequestDto.getShowId(), bookTicketRequestDto.getUserId(), bookTicketRequestDto.getShowSeatIds());
            bookTicketResponseDto.setBookingId(booking.getId());
            bookTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            bookTicketResponseDto.setMessage("Booking confirmed. Please make the payment.");
        }
        catch(Exception e){
            bookTicketResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            bookTicketResponseDto.setMessage("Booking failed. "+e.getMessage());
        }
        return bookTicketResponseDto;
    }
}

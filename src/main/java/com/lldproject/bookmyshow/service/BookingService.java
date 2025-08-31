package com.lldproject.bookmyshow.service;

import com.lldproject.bookmyshow.model.*;
import com.lldproject.bookmyshow.repository.BookingRepository;
import com.lldproject.bookmyshow.repository.ShowRepository;
import com.lldproject.bookmyshow.repository.ShowSeatRepository;
import com.lldproject.bookmyshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private ShowRepository showRepository;
    private BookingRepository bookingRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;

    @Autowired
    public BookingService(ShowRepository showRepository, BookingRepository bookingRepository, ShowSeatRepository showSeatRepository, UserRepository userRepository) {
        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public Booking bookTicket(Long showId, Long userId, List<Long> showSeatIds){
        //Get the user from db
        Optional<User> userOp=userRepository.findById(userId);
        if(userOp.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User user=userOp.get();
        //Get the show from db
        Optional<Show> showOp = showRepository.findById(showId);
        if(showOp.isEmpty()){
            throw new RuntimeException("Show not found");
        }
        Show show=showOp.get();
        //Get the show seats from db
        List<ShowSeat> showSeats = showSeatRepository.findAllByIdAndStatus(showSeatIds, ShowSeatStatus.AVAILABLE);
        //Check if all seats are available
        if(showSeats.size()<showSeatIds.size()){
            throw new RuntimeException("Show Seats not available");
        }
        //If yes, block the seats
        for(ShowSeat showSeat:showSeats){
            showSeat.setStatus(ShowSeatStatus.BLOCKED);
        }
        showSeatRepository.saveAll(showSeats);
        //Create booking
        Booking booking=new Booking();
        booking.setShow(show);
        booking.setBookedBy(user);
        booking.setBookingDate(new Date());
        booking.setBookedSeats(showSeats);
        booking.setBookingStatus(BookingStatus.PENDING);
        //Get the seat type
        //Implement logic to get the total price
        booking.setTotalAmount(100);
        booking.setNumSeats(showSeats.size());
        return bookingRepository.save(booking);
    }
}

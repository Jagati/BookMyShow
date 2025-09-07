package com.lldproject.bookmyshow.service;

import com.lldproject.bookmyshow.exceptions.ShowNotFoundException;
import com.lldproject.bookmyshow.exceptions.ShowSeatsNotFoundException;
import com.lldproject.bookmyshow.exceptions.UserNotFoundException;
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
    public Booking bookTicket(Long showId, Long userId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException, ShowSeatsNotFoundException{
        String threadName = Thread.currentThread().getName();
        System.out.println("Running thread: " + threadName + ". Starting ticket booking for user: "+userId);
        //Get the user from db
        Optional<User> userOp=userRepository.findById(userId);
        if(userOp.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user=userOp.get();
        //Get the show from db
        Optional<Show> showOp = showRepository.findById(showId);
        if(showOp.isEmpty()){
            throw new ShowNotFoundException("Show not found");
        }
        Show show=showOp.get();
        //Get the show seats from db
        List<ShowSeat> showSeats = showSeatRepository.findAllByIdAndStatus(showSeatIds, ShowSeatStatus.AVAILABLE);
        //Check if all seats are available
        if(showSeats.size()<showSeatIds.size()){
            System.out.println("[" + threadName + "] " + "Seat availability check failed. Found " + showSeats.size() + " available seats out of "+ showSeatIds.size() + "requested.");
            throw new ShowSeatsNotFoundException("Show Seats not available");
        }
        System.out.println("[" + threadName + "] " + "Seat availability check successful. Found "+ showSeats.size() + " available seats out of "+ showSeatIds.size() + "seats.");
        //If yes, block the seats
        System.out.println("[" + threadName + "] Updating seat status to BLOCKED for " + showSeats.size() + " seats");
        for(ShowSeat showSeat:showSeats){

            showSeat.setStatus(ShowSeatStatus.BLOCKED);
        }
        showSeatRepository.saveAll(showSeats);
        System.out.println("["+threadName+"] " + "Seats blocked successfully");
        //Create booking
        System.out.println("["+threadName+"] " + "Creating a booking");
        Booking booking=new Booking();
        booking.setShow(show);
        booking.setBookedBy(user);
        booking.setBookingDate(new Date());
        booking.setBookedSeats(showSeats);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setNumSeats(showSeats.size());
        //Get the seat type and calculate the total cost of booking
        ShowSeat bookedSeat = booking.getBookedSeats().get(0); //Getting one seat from the booked seats
        int seatCost = bookedSeat.getShowSeatType().getPrice();
        booking.setTotalAmount(seatCost*booking.getNumSeats());
        Booking savedBooking = bookingRepository.save(booking);
        System.out.println("[" + threadName + "] Booking created successfully. Booking ID: " + savedBooking.getId());
        return savedBooking;
    }
}

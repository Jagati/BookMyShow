package com.lldproject.bookmyshow.service;

import com.lldproject.bookmyshow.model.ShowSeatPricing;
import com.lldproject.bookmyshow.exceptions.*;
import com.lldproject.bookmyshow.model.*;
import com.lldproject.bookmyshow.repository.*;
import com.lldproject.bookmyshow.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    private UserRepository userRepository;
    private ScreenRepository screenRepository;
    private MovieRepository movieRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository  showSeatRepository;

    public ShowService(UserRepository userRepository, ScreenRepository screenRepository, MovieRepository movieRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository) {
        this.userRepository = userRepository;
        this.screenRepository = screenRepository;
        this.movieRepository = movieRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }

    public long createShow(long user_id, long movie_id, long screen_id, Date start_time, Date end_time, Language language, Features supported_feature, List<ShowSeatPricing> seatPricing) throws UserNotFoundException, UnAuthorizedAccessException, ScreenNotFoundException, MovieNotFoundException, FeatureNotSupportedException, InvalidDateException, LanguageNotSupportedException {
        //Get User
        Optional<User> userOp= userRepository.findById(screen_id);
        if (userOp.isEmpty()) {
            throw new UserNotFoundException("User id not found");
        }
        User user = userOp.get();
        if(user.getUserRole()!= UserRole.ADMIN) {
            throw new UnAuthorizedAccessException("Only admin can create show");
        }
        //Get screen
        Optional<Screen> screenOp= screenRepository.findById(screen_id);
        if (screenOp.isEmpty()) {
            throw new ScreenNotFoundException("Screen not found.");
        }
        Screen screen = screenOp.get();
        //Get movie
        Optional<Movie> movieOp= movieRepository.findById(movie_id);
        if (movieOp.isEmpty()) {
            throw new MovieNotFoundException("Movie not found.");
        }
        Movie movie = movieOp.get();
        if(!screen.getScreenFeatures().contains(supported_feature)) {
            throw new FeatureNotSupportedException("Screen does not support the requested movie feature.");
        }
        if(!DateUtils.isValidShowtimings(start_time,end_time)) {
            throw new InvalidDateException("Invalid show timings");
        }
        if(!movie.getLanguages().contains(language)){
            throw new LanguageNotSupportedException("Movie is not available in the desired language");
        }
        Show show =new Show();
        show.setScreen(screen);
        show.setMovie(movie);
        show.setStartTime(start_time);
        show.setLanguage(language);
        show.setFeature(supported_feature);
        List<ShowSeatType> showSeatTypes = new ArrayList<>();
        for(ShowSeatPricing seatPrice: seatPricing) {
            ShowSeatType showSeatType = new ShowSeatType();
            showSeatType.setName(seatPrice.getShowSeatName());
            showSeatType.setPrice(seatPrice.getPrice());
            showSeatTypes.add(showSeatType);
        }
        show.setShowSeatTypes(showSeatTypes);
        Show savedShow= showRepository.save(show);

        //Get seats in the screen and create show seats
        List<Seat> seats = screen.getSeats();
        List<ShowSeat> showSeats = new ArrayList<>();
        for(Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeat(seat);
            showSeat.setShow(savedShow);
            showSeat.setStatus(ShowSeatStatus.AVAILABLE);
            showSeats.add(showSeat);
        }
        showSeatRepository.saveAll(showSeats);
        show.setShowSeats(showSeats);
        return savedShow.getId();
    }
}

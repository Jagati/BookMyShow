package com.lldproject.bookmyshow.service;

import com.lldproject.bookmyshow.exceptions.MovieNotFoundException;
import com.lldproject.bookmyshow.exceptions.UserNotFoundException;
import com.lldproject.bookmyshow.model.Movie;
import com.lldproject.bookmyshow.model.User;
import com.lldproject.bookmyshow.model.UserRating;
import com.lldproject.bookmyshow.repository.MovieRepository;
import com.lldproject.bookmyshow.repository.UserRatingRepository;
import com.lldproject.bookmyshow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRatingService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final UserRatingRepository userRatingRepository;

    public UserRatingService(UserRepository userRepository, MovieRepository movieRepository, UserRatingRepository userRatingRepository){
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.userRatingRepository = userRatingRepository;
    }

    public UserRating rateMovie(long user_id, long movie_id, int rating) throws UserNotFoundException, MovieNotFoundException {
        Optional<User> userOp = userRepository.findById(user_id);
        if(userOp.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOp.get();
        Optional<Movie> movieOp = movieRepository.findById(movie_id);
        if(movieOp.isEmpty()){
            throw new MovieNotFoundException("Movie not found");
        }
        Movie movie = movieOp.get();
        Optional<UserRating> userRatingOp = userRatingRepository.findByMovieAndUser(movie, user);
        UserRating userRating;
        if(userRatingOp.isPresent()){
            userRating = userRatingOp.get();
            userRating.setRating(rating);
        }
        else{
            userRating = new UserRating();
            userRating.setUser(user);
            userRating.setMovie(movie);
            userRating.setRating(rating);
        }
        return userRatingRepository.save(userRating);
    }

    public double getAverageRating(long movie_id) throws MovieNotFoundException{
        Optional<Movie> movieOp = movieRepository.findById(movie_id);
        if(movieOp.isEmpty()){
            throw new MovieNotFoundException("Movie not found");
        }
        Movie movie = movieOp.get();
        List<UserRating> userRatings = userRatingRepository.findByMovie(movie);
        return userRatings.stream()
                          .mapToInt(UserRating::getRating)
                          .average()
                          .orElse(0.0);
    }
}

package com.lldproject.bookmyshow.repository;

import com.lldproject.bookmyshow.model.Movie;
import com.lldproject.bookmyshow.model.Rating;
import com.lldproject.bookmyshow.model.User;
import com.lldproject.bookmyshow.model.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRatingRepository extends JpaRepository<UserRating, Long> {
    Optional<UserRating> findByMovieAndUser(Movie movie, User user);
    List<UserRating> findByMovie(Movie movie);
}

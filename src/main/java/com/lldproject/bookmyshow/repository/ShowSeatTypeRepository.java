package com.lldproject.bookmyshow.repository;

import com.lldproject.bookmyshow.model.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
}

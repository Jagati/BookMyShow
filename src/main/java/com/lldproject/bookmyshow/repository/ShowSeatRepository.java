package com.lldproject.bookmyshow.repository;

import com.lldproject.bookmyshow.model.ShowSeat;
import com.lldproject.bookmyshow.model.ShowSeatStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ss FROM ShowSeat ss WHERE ss.id IN :ids AND ss.status = :status")
    List<ShowSeat> findAllByIdAndStatus(@Param("ids")List<Long>ids, @Param("status") ShowSeatStatus status);
}

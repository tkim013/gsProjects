package com.genspark.backend.Repository;

import com.genspark.backend.Entity.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Logger logger = LoggerFactory.getLogger(ReservationRepository.class);

}

package com.oc.sitejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oc.sitejava.entity.Reservation;

public interface ReserveRepository extends JpaRepository<Reservation, Integer> {

}

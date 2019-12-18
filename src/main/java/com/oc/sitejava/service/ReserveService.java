package com.oc.sitejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.sitejava.entity.Reservation;
import com.oc.sitejava.repository.ReserveRepository;

@Service
public class ReserveService {
	
	@Autowired
	private ReserveRepository resRepository;
	
	public List<Reservation> listAll(){
		return resRepository.findAll();
	}
	
	public void save(Reservation res) {
		resRepository.save(res);
	}
	
	public Reservation get(int id_reservation) {
		return resRepository.findById(id_reservation).get();
	}
	
	public void delete(int id_reservation) {
		resRepository.deleteById(id_reservation);
	}
	
}

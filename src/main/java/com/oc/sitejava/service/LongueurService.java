package com.oc.sitejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.sitejava.entity.Longueur;
import com.oc.sitejava.repository.LongueurRepository;

@Service
public class LongueurService {
	
	@Autowired
	private LongueurRepository longueurRepository;

	public void insertLongueur(Integer hauteur, Integer id_voie) {
		longueurRepository.insertLongueur(hauteur, id_voie);
	}
	public List<Longueur> listAll(){
		return longueurRepository.findAll();
	}
	
	public void save(Longueur longueur) {
		longueurRepository.save(longueur);
	}
	
	public Longueur get(int id_longueur) {
		return longueurRepository.findById(id_longueur).get();
	}
	
	public void delete(int id_longueur) {
		longueurRepository.deleteById(id_longueur);
	}
}

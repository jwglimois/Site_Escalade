package com.oc.sitejava.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.sitejava.entity.Voie;
import com.oc.sitejava.repository.VoieRepository;

@Service
public class VoieService {
	@Autowired
	private VoieRepository voieRepository;
	
	public Integer fetchNbVoie(Integer id_site) {
		return voieRepository.fetchNbVoie(id_site);
	}
	
	public List<String> fetchCotations(Integer id_site) {
		return voieRepository.fetchCotations(id_site);
	}
	
	public Integer getTotalHauteur(Integer id_voie) {
		return voieRepository.getTotalHauteur(id_voie);
	}
	
	public Integer getNbLongueur(Integer id_voie) {
		return voieRepository.getNbLongueur(id_voie);
	}
	
	public void insertVoie(String cotation, String nom_voie, Integer id_secteur) {
		voieRepository.insertVoie(cotation, nom_voie, id_secteur);
	}
	
	public List<Voie> listAll(){
		return voieRepository.findAll();
	}
	
	public void save(Voie voie) {
		voieRepository.save(voie);
	}
	
	public Voie get(int id_voie) {
		return voieRepository.findById(id_voie).get();
	}
	
	public void delete(int id_voie) {
		voieRepository.deleteById(id_voie);
	}
}

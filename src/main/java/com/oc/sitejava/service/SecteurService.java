package com.oc.sitejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.sitejava.dto.SecteurDto;
import com.oc.sitejava.entity.Secteur;
import com.oc.sitejava.repository.SecteurRepository;

@Service
public class SecteurService {
	
	@Autowired
	private SecteurRepository secteurRepository;
	
	public Integer fetchNbSecteur(Integer id_site){
		return secteurRepository.fetchNbSecteur(id_site);
	}
	
	public List<Secteur> listAll(){
		return secteurRepository.findAll();
	}
	
	public void save(Secteur secteur) {
		secteurRepository.save(secteur);
	}
	
	public Secteur get(int id_secteur) {
		return secteurRepository.findById(id_secteur).get();
	}
	
	public void delete(int id_secteur) {
		secteurRepository.deleteById(id_secteur);
	}
}

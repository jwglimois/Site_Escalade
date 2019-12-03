package com.oc.sitejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oc.sitejava.entity.Voie;

public interface VoieRepository extends JpaRepository<Voie, Integer>{
	@Query(value = "SELECT COUNT(*) FROM voie v "
				+ " INNER JOIN secteur sr ON v.id_secteur = sr.id_secteur "
				+ " INNER JOIN site s ON s.id_site = sr.id_site "
				+ " WHERE sr.id_site= ?1", 
				  nativeQuery = true)
	Integer fetchNbVoie(Integer id_site);
	
	@Query(value = "SELECT v.cotation FROM voie v "
			+ " INNER JOIN secteur sr ON v.id_secteur = sr.id_secteur "
			+ " INNER JOIN site s ON s.id_site = sr.id_site "
			+ " WHERE sr.id_site= ?1"
			+ " ORDER BY v.cotation ASC", 
			  nativeQuery = true)
	List<String> fetchCotations(Integer id_site);
	

}
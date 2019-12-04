package com.oc.sitejava.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oc.sitejava.entity.Secteur;

public interface SecteurRepository extends JpaRepository<Secteur, Integer > {
	
	@Query(value = "SELECT COUNT(*) FROM secteur sr WHERE sr.id_site= ?1", 
			  nativeQuery = true)
	Integer fetchNbSecteur(Integer id_site);
	
	@Modifying
	@Query(value = "INSERT INTO secteur (nom_secteur) VALUES (:nom_secteur)",
	  		nativeQuery = true)
	void insertSecteur(@Param("nom_secteur") String nom_secteur);

}

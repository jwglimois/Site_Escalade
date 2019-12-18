package com.oc.sitejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.oc.sitejava.entity.Longueur;

public interface LongueurRepository extends JpaRepository <Longueur, Integer>{
	
	@Transactional
	@Modifying
	@Query(value = "insert into longueur (hauteur, id_voie) values (?1 , ?2)", 
	  nativeQuery = true)
	void insertLongueur(Integer hauteur, Integer id_voie);
}

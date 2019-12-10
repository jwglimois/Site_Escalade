package com.oc.sitejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oc.sitejava.entity.Utilisateur;

@Repository
public interface UserRepository extends JpaRepository < Utilisateur, Integer > {
	
	Utilisateur findByEmail(String email);
	
	@Modifying
    @Query(value = "insert into utilisateur (nom, prenom, email, password, id_role) VALUES (:nom, :prenom, :email, :password, :id_role)", nativeQuery = true)
	@Transactional
	void saveByUserDTO(@Param("nom") String nom,
    					@Param("prenom") String prenom,
    					@Param("email") String email,
    					@Param("password") String password, 
    					@Param("id_role") int id_role);
}

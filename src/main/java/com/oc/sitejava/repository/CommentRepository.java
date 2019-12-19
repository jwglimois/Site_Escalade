package com.oc.sitejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.oc.sitejava.entity.Commentaire;
import com.oc.sitejava.entity.Site;

public interface CommentRepository extends JpaRepository <Commentaire, Integer>{
	
	@Query("select new com.oc.sitejava.entity.Commentaire (c.id_commentaire, c.date_creation, c.message , c.site, c.utilisateur) "
			+ " from Commentaire c where c.site = ?1")
	List<Commentaire> getlistCommentBySite(Site site);
	
	@Transactional
	@Modifying
	@Query(value = "update commentaire c set c.message = ? where c.id_commentaire = ?", 
	  nativeQuery = true)
	void updateCommentaire(String message, Integer id_commentaire);
	
	@Transactional
	@Modifying
	@Query(value="delete from commentaire where id_commentaire= ?1", 
			nativeQuery=true)
	void deleteCommentaire(int id_commentaire);
}

package com.oc.sitejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.oc.sitejava.entity.Topo;
import com.oc.sitejava.entity.Utilisateur;

public interface TopoRepository extends JpaRepository <Topo, Integer>{
	
	@Query("select new com.oc.sitejava.entity.Topo (t.id_topo, t.nom_topo, t.description , t.date_parution, t.statut, t.site, t.proprietaire) "
			+ " from Topo t where t.proprietaire = ?1")
	List<Topo> getlistTopoByUser(Utilisateur utilisateur);
	
	@Transactional
	@Modifying
	@Query(value = "update topo t set t.statut = ? where t.id_topo = ?", 
	  nativeQuery = true)
	void updateTopoStatut(String statut, Integer id_topo);
}

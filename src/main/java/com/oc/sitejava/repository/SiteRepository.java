package com.oc.sitejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oc.sitejava.dto.SiteDto;
import com.oc.sitejava.entity.Site;

public interface SiteRepository extends JpaRepository<Site, Integer >{
	@Query("SELECT DISTINCT new com.oc.sitejava.dto.SiteDto (s.id_site ,s.nomSite, s.region) "
			+ "FROM Site s INNER JOIN s.listSecteurs sr")
	List<SiteDto> fetchSites();
	
	@Modifying
	@Query(value = "INSERT INTO site (nom_site, region) VALUES (:nom_site, :region)",
	  		nativeQuery = true)
	void insertSite(@Param("nom_site") String nom_site, @Param("region") String region);
	
}

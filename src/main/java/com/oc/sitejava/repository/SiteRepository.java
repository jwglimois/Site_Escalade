package com.oc.sitejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oc.sitejava.dto.SiteDto;
import com.oc.sitejava.entity.Site;

public interface SiteRepository extends JpaRepository<Site, Integer >{
	@Query("SELECT DISTINCT new com.oc.sitejava.dto.SiteDto (s.id_site ,s.nomSite, s.region) "
			+ "FROM Site s INNER JOIN s.listSecteurs sr")
	List<SiteDto> fetchSites();
	
}

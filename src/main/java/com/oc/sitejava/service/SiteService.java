package com.oc.sitejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.sitejava.dto.SiteDto;
import com.oc.sitejava.entity.Site;
import com.oc.sitejava.repository.SecteurRepository;
import com.oc.sitejava.repository.SiteRepository;
import com.oc.sitejava.repository.VoieRepository;

@Service
public class SiteService {
	
	@Autowired
	private SiteRepository siteRepository;
	
	void insertSite(String nom_site, String region) {
		siteRepository.insertSite(nom_site, region);
	}
	
	public List<SiteDto> fetchSites(){
		return siteRepository.fetchSites();
	}
	
	
	public List<Site> listAll(){
		return siteRepository.findAll();
	}

	public void save(Site siteEntity) {
		siteRepository.save(siteEntity);
	}
	
	public Site get(int id_site) {
		return siteRepository.findById(id_site).get();
	}
	
	public void delete(int id_site) {
		siteRepository.deleteById(id_site);
	}
}

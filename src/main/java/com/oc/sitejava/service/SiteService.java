package com.oc.sitejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.sitejava.dto.SiteDto;
import com.oc.sitejava.entity.Site;
import com.oc.sitejava.repository.SiteRepository;

@Service
public class SiteService {
	
	@Autowired
	private SiteRepository siteRepository;
	
	public Integer fetchLastIDSite(){
		return siteRepository.fetchLastIDSite();
	}
	
	public List<SiteDto> fetchSites(){
		return siteRepository.fetchSites();
	}
	
	public Integer getIdSiteBySearch(String region, String cotation) {
		return siteRepository.getIdSiteBySearch(region, cotation);
	}
	
	public void addTag(String tag_active, Integer id_site) {
		siteRepository.addTag(tag_active, id_site);
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

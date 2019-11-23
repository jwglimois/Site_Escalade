package com.oc.sitejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.sitejava.entity.Site;
import com.oc.sitejava.repository.SiteRepository;

@Service
public class SiteService {
	
	@Autowired
	private SiteRepository siteRepository;
	
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

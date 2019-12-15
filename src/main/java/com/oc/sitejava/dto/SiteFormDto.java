package com.oc.sitejava.dto;

import java.util.List;

import com.oc.sitejava.entity.Longueur;
import com.oc.sitejava.entity.Secteur;
import com.oc.sitejava.entity.Site;
import com.oc.sitejava.entity.Voie;

public class SiteFormDto {
	

	private Site site;
	private List<Secteur> secteurList;
	private List<Voie> voieList;
	private List<Longueur> longueurList;
	
	public SiteFormDto() {
		super();
	}


	public SiteFormDto(Site site) {
		super();
		this.site = site;
	}

	

	public SiteFormDto(Site site, List<Secteur> secteurList) {
		super();
		this.site = site;
		this.secteurList = secteurList;
	}


	public List<Secteur> getSecteurList() {
		return secteurList;
	}


	public void setSecteurList(List<Secteur> secteurList) {
		this.secteurList = secteurList;
	}


	public List<Voie> getVoieList() {
		return voieList;
	}


	public void setVoieList(List<Voie> voieList) {
		this.voieList = voieList;
	}


	public List<Longueur> getLongueurList() {
		return longueurList;
	}


	public void setLongueurList(List<Longueur> longueurList) {
		this.longueurList = longueurList;
	}


	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	
	@Override
	public String toString() {
		return "SiteFormDto [---------site=" + site + ", secteurList=" + secteurList + ", voieList=" + voieList
				+ ", longueurList=" + longueurList + "-----------------]";
	}

}

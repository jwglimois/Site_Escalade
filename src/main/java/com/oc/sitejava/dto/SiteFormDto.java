package com.oc.sitejava.dto;

import com.oc.sitejava.entity.Longueur;
import com.oc.sitejava.entity.Secteur;
import com.oc.sitejava.entity.Site;
import com.oc.sitejava.entity.Voie;

public class SiteFormDto {
	private Site site;
	private Secteur secteur;
	private Voie voie;
	private Longueur longueur;
	
	public SiteFormDto() {
		super();
	}

	public SiteFormDto(Site site, Secteur secteur, Voie voie, Longueur longueur) {
		super();
		this.site = site;
		this.secteur = secteur;
		this.voie = voie;
		this.longueur = longueur;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public Voie getVoie() {
		return voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}

	public Longueur getLongueur() {
		return longueur;
	}

	public void setLongueur(Longueur longueur) {
		this.longueur = longueur;
	}
	
	

}

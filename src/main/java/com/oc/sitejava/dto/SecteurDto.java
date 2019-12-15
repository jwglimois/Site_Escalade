package com.oc.sitejava.dto;

import java.util.List;

import com.oc.sitejava.entity.Secteur;
import com.oc.sitejava.entity.Site;

public class SecteurDto {
	private Secteur secteur1;
	private Secteur secteur2;
	private Secteur secteur3;
	
	private Site site;


	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public SecteurDto() {
		super();
	}

	public SecteurDto(Secteur secteur1, Secteur secteur2, Secteur secteur3, Site site) {
		super();
		this.secteur1 = secteur1;
		this.secteur2 = secteur2;
		this.secteur3 = secteur3;
		this.site = site;
	}

	public Secteur getSecteur1() {
		return secteur1;
	}

	public void setSecteur1(Secteur secteur1) {
		this.secteur1 = secteur1;
	}

	public Secteur getSecteur2() {
		return secteur2;
	}

	public void setSecteur2(Secteur secteur2) {
		this.secteur2 = secteur2;
	}

	public Secteur getSecteur3() {
		return secteur3;
	}

	public void setSecteur3(Secteur secteur3) {
		this.secteur3 = secteur3;
	}
	
	
}

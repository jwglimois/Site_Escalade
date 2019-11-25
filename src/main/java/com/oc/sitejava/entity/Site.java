package com.oc.sitejava.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Site {
	
	@Id
	private int id_site;
	private String nomSite;
	private String region;
	private int nbSecteur;
	private int nbVoie;
	private String cotation;
	
	protected Site() {		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId_site() {
		return id_site;
	}

	public void setId_site(int id_site) {
		this.id_site = id_site;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getNbSecteur() {
		return nbSecteur;
	}

	public void setNbSecteur(int nbSecteur) {
		this.nbSecteur = nbSecteur;
	}

	public int getNbVoie() {
		return nbVoie;
	}

	public void setNbVoie(int nbVoie) {
		this.nbVoie = nbVoie;
	}

	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}
	
	

}

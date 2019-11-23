package com.oc.sitejava.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Site {
	
	@Id
	private int id_site;
	private String lieu;
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

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
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

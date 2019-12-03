package com.oc.sitejava.dto;

public class SiteDto {
	private int siteId;
	private String siteNom;
	private String siteRegion;
	private int nbSecteur;
	private int nbVoie;
	private String rangeCotation;
	
	
	public SiteDto(int siteId, String siteNom, String siteRegion) {
		super();
		this.siteId = siteId;
		this.siteNom = siteNom;
		this.siteRegion = siteRegion;
	
	}
	
	

	public SiteDto(int siteId, String siteNom, String siteRegion, int nbSecteur, int nbVoie, String rangeCotation) {
		super();
		this.siteId = siteId;
		this.siteNom = siteNom;
		this.siteRegion = siteRegion;
		this.nbSecteur = nbSecteur;
		this.nbVoie = nbVoie;
		this.rangeCotation = rangeCotation;
	}


	

	public String getRangeCotation() {
		return rangeCotation;
	}



	public void setRangeCotation(String rangeCotation) {
		this.rangeCotation = rangeCotation;
	}



	public int getNbVoie() {
		return nbVoie;
	}



	public void setNbVoie(int nbVoie) {
		this.nbVoie = nbVoie;
	}



	public int getNbSecteur() {
		return nbSecteur;
	}



	public void setNbSecteur(int nbSecteur) {
		this.nbSecteur = nbSecteur;
	}



	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public String getSiteNom() {
		return siteNom;
	}
	public void setSiteNom(String siteNom) {
		this.siteNom = siteNom;
	}
	public String getSiteRegion() {
		return siteRegion;
	}
	public void setSiteRegion(String siteRegion) {
		this.siteRegion = siteRegion;
	}
	
	

}

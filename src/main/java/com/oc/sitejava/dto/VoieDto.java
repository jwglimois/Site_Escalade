package com.oc.sitejava.dto;

import com.oc.sitejava.entity.Secteur;
import com.oc.sitejava.entity.Voie;

public class VoieDto {
	private Voie voie1;
	private Voie voie2;
	private Voie voie3;
	private Voie voie4;
	private Voie voie5;
	private Voie voie6;
	private Secteur secteur1;
	private Secteur secteur2;
	private Secteur secteur3;
	
	//For fichesite.html
	private String nomVoie;
	private int hauteurVoie;
	private int nbLongueur;
	private String coteVoie;
	

	public VoieDto() {
		super();
	}


	public VoieDto(String nomVoie, int hauteurVoie, int nbLongueur, String coteVoie) {
		super();
		this.nomVoie = nomVoie;
		this.hauteurVoie = hauteurVoie;
		this.nbLongueur = nbLongueur;
		this.coteVoie = coteVoie;
	}


	public VoieDto(Voie voie1, Voie voie2, Voie voie3, Voie voie4, Voie voie5, Voie voie6, Secteur secteur1,
			Secteur secteur2, Secteur secteur3) {
		super();
		this.voie1 = voie1;
		this.voie2 = voie2;
		this.voie3 = voie3;
		this.voie4 = voie4;
		this.voie5 = voie5;
		this.voie6 = voie6;
		this.secteur1 = secteur1;
		this.secteur2 = secteur2;
		this.secteur3 = secteur3;
	}






	public Voie getVoie1() {
		return voie1;
	}




	public void setVoie1(Voie voie1) {
		this.voie1 = voie1;
	}




	public Voie getVoie2() {
		return voie2;
	}




	public void setVoie2(Voie voie2) {
		this.voie2 = voie2;
	}




	public Voie getVoie3() {
		return voie3;
	}




	public void setVoie3(Voie voie3) {
		this.voie3 = voie3;
	}




	public Voie getVoie4() {
		return voie4;
	}




	public void setVoie4(Voie voie4) {
		this.voie4 = voie4;
	}




	public Voie getVoie5() {
		return voie5;
	}




	public void setVoie5(Voie voie5) {
		this.voie5 = voie5;
	}




	public Voie getVoie6() {
		return voie6;
	}




	public void setVoie6(Voie voie6) {
		this.voie6 = voie6;
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

	

	public String getNomVoie() {
		return nomVoie;
	}


	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}


	public int getHauteurVoie() {
		return hauteurVoie;
	}


	public void setHauteurVoie(int hauteurVoie) {
		this.hauteurVoie = hauteurVoie;
	}


	public int getNbLongueur() {
		return nbLongueur;
	}


	public void setNbLongueur(int nbLongueur) {
		this.nbLongueur = nbLongueur;
	}


	public String getCoteVoie() {
		return coteVoie;
	}


	public void setCoteVoie(String coteVoie) {
		this.coteVoie = coteVoie;
	}


	
	
	
}

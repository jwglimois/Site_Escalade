package com.oc.sitejava.dto;


public class VoieDto {
	private int id_voie;
	private String nom_voie;
	private String cotation;

	

	public VoieDto(int id_voie, String nom_voie, String cotation) {
		super();
		this.id_voie = id_voie;
		this.nom_voie = nom_voie;
		this.cotation = cotation;
	}

	public VoieDto() {
		super();
	}

	public int getId_voie() {
		return id_voie;
	}

	public void setId_voie(int id_voie) {
		this.id_voie = id_voie;
	}

	public String getNom_voie() {
		return nom_voie;
	}

	public void setNom_voie(String nom_voie) {
		this.nom_voie = nom_voie;
	}

	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}
	
	
	
	
	
}

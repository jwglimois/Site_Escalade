package com.oc.sitejava.dto;

public class LongueurDto {
	private int id_longueur;
	private String hauteur;
	
	public LongueurDto() {
		super();
	}
	public LongueurDto(int id_longueur, String hauteur) {
		super();
		this.id_longueur = id_longueur;
		this.hauteur = hauteur;
	}
	public int getId_longueur() {
		return id_longueur;
	}
	public void setId_longueur(int id_longueur) {
		this.id_longueur = id_longueur;
	}
	public String getHauteur() {
		return hauteur;
	}
	public void setHauteur(String hauteur) {
		this.hauteur = hauteur;
	}
	
	
}

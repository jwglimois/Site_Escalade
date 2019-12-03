package com.oc.sitejava.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "longueur")
public class Longueur {
	
	@Id
	@Column(name = "id_longueur")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_longueur;
	
	@Column(name="hauteur")
	private int hauteur;
	
	@Access(AccessType.FIELD)
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_voie")
	private Voie voie;
	
	protected Longueur() {
	}

	
	public int getId_longueur() {
		return id_longueur;
	}

	public void setId_longueur(int id_longueur) {
		this.id_longueur = id_longueur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	
}

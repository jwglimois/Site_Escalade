package com.oc.sitejava.entity;

import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "voie")
public class Voie {
	
	@Id
	@Column(name = "id_voie")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_voie;
	
	@Column(name="nom_voie")
	private String nomVoie;

	@Column(name="cotation")
	private String cotation;
	
	@Access(AccessType.FIELD)
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_secteur")
	private Secteur secteur;
	
	@Access(AccessType.FIELD)
	@OneToMany(mappedBy="voie",cascade = CascadeType.ALL)
	private List<Longueur> listLongueur;
	

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	protected Voie() {	
	}
	
	
	public int getId_voie() {
		return id_voie;
	}

	public void setId_voie(int id_voie) {
		this.id_voie = id_voie;
	}

	public String getNomVoie() {
		return nomVoie;
	}

	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}


	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}
	
	
}

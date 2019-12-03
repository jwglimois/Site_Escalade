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
@Table(name = "secteur")
public class Secteur {

	@Id
	@Column(name = "id_secteur")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_secteur;
	
	@Column (name="nom_secteur")
	private String nomSecteur;
	

	@Access(AccessType.FIELD)
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_site")
	private Site site;
	
	@Access(AccessType.FIELD)
	@OneToMany(mappedBy="secteur",cascade = CascadeType.ALL)
	private List<Voie> listVoie;
	
	protected Secteur() {		
	}

	
	public List<Voie> getListVoie() {
		return listVoie;
	}

	public void setListVoie(List<Voie> listVoie) {
		this.listVoie = listVoie;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}


	public int getId_secteur() {
		return id_secteur;
	}

	public void setId_secteur(int id_secteur) {
		this.id_secteur = id_secteur;
	}

	public String getNomSecteur() {
		return nomSecteur;
	}

	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}

	
}

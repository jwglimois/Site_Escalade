package com.oc.sitejava.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="site")
public class Site implements Serializable {
	
	@Id
	@Column(name = "id_site")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_site;
	
	@Column(name="nom_site")
	private String nomSite;
	
	@Column(name="region")
	private String region;
	
	@Access(AccessType.FIELD)
	@OneToMany (mappedBy = "site", cascade = CascadeType.ALL)
	private List<Secteur> listSecteurs;
	
	public List<Secteur> getlistSecteurs() {
		return listSecteurs;
	}

	public void setlistSecteurs(List<Secteur> listSecteurs) {
		this.listSecteurs = listSecteurs;
	}

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



}

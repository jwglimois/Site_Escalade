package com.oc.sitejava.entity;

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
public class Site  {
	
	@Id
	@Column(name = "id_site")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_site;
	
	@Column(name="nom_site")
	private String nom_site;
	
	@Column(name="region")
	private String region;
	
	@Access(AccessType.FIELD)
	@OneToMany (mappedBy = "site", cascade = CascadeType.ALL)
	private List<Secteur> listSecteurs;
	
	@Access(AccessType.FIELD)
	@OneToMany (mappedBy = "site", cascade = CascadeType.ALL)
	private List<Commentaire> listCom;
	
	@Access(AccessType.FIELD)
	@OneToMany (mappedBy = "site", cascade = CascadeType.ALL)
	private List<Topo> listTopo;
	
	public List<Secteur> getlistSecteurs() {
		return listSecteurs;
	}

	public void setlistSecteurs(List<Secteur> listSecteurs) {
		this.listSecteurs = listSecteurs;
	}

	public Site() {		
	}


	public int getId_site() {
		return id_site;
	}

	public void setId_site(int id_site) {
		this.id_site = id_site;
	}

	public String getNom_site() {
		return nom_site;
	}

	public void setNom_site(String nom_site) {
		this.nom_site = nom_site;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Site(int id_site, String nom_site, String region, List<Secteur> listSecteurs) {
		super();
		this.id_site = id_site;
		this.nom_site = nom_site;
		this.region = region;
		this.listSecteurs = listSecteurs;
	}

	@Override
	public String toString() {
		return "Site [---------id_site=" + id_site + ", nom_site=" + nom_site + ", region=" + region + ", listSecteurs="
				+ listSecteurs + "--------------]";
	}

	public List<Secteur> getListSecteurs() {
		return listSecteurs;
	}

	public void setListSecteurs(List<Secteur> listSecteurs) {
		this.listSecteurs = listSecteurs;
	}

	public List<Commentaire> getListCom() {
		return listCom;
	}

	public void setListCom(List<Commentaire> listCom) {
		this.listCom = listCom;
	}

	public List<Topo> getListTopo() {
		return listTopo;
	}

	public void setListTopo(List<Topo> listTopo) {
		this.listTopo = listTopo;
	}

	


}

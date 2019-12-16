package com.oc.sitejava.entity;

import java.util.Date;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="topo")
public class Topo {
	
	@Id
	@Column(name = "id_topo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_topo;
	
	@Column (name="nom_topo")
	private String nom_topo;
	
	@Column (name="description")
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column (name="date_parution")
	private Date date_parution;
	
	@Column (name="statut")
	private String statut;
	
	@Access(AccessType.FIELD)
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_site")
	private Site site;
	
	@Access(AccessType.FIELD)
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinColumn(name="id_user")
	private Utilisateur proprietaire;

	@Access(AccessType.FIELD)
	@OneToMany (mappedBy = "topo", cascade = CascadeType.ALL)
	private List<Reservation> listReservation;
	
	public Topo() {
	}



	public Topo(int id_topo, String nom_topo, String description, Date date_parution, String statut, Site site,
			Utilisateur proprietaire) {
		super();
		this.id_topo = id_topo;
		this.nom_topo = nom_topo;
		this.description = description;
		this.date_parution = date_parution;
		this.statut = statut;
		this.site = site;
		this.proprietaire = proprietaire;
	}



	public int getId_topo() {
		return id_topo;
	}

	public void setId_topo(int id_topo) {
		this.id_topo = id_topo;
	}

	public String getNom_topo() {
		return nom_topo;
	}

	public void setNom_topo(String nom_topo) {
		this.nom_topo = nom_topo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_parution() {
		return date_parution;
	}

	public void setDate_parution(Date date_parution) {
		this.date_parution = date_parution;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}



	public Utilisateur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Utilisateur proprietaire) {
		this.proprietaire = proprietaire;
	}

	public List<Reservation> getListReservation() {
		return listReservation;
	}

	public void setListReservation(List<Reservation> listReservation) {
		this.listReservation = listReservation;
	}

	@Override
	public String toString() {
		return "Topo [id_topo=" + id_topo + ", nom_topo=" + nom_topo + ", description=" + description
				+ ", date_parution=" + date_parution + ", statut=" + statut + ", site=" + site + ", proprietaire="
				+ proprietaire + ", listReservation=" + listReservation + "]";
	}


	
	
	
}

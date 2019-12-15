package com.oc.sitejava.entity;

import java.util.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="commentaire")
public class Commentaire {
	
	@Id
	@Column(name = "id_commentaire")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_commentaire;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="date_creation")
	private Date date_creation;
	
	@Column(name="message")
	private String message;
	
	@Access(AccessType.FIELD)
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_site")
	private Site site;

	@Access(AccessType.FIELD)
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinColumn(name="id_user")
	private Utilisateur utilisateur;
	

	public Commentaire() {
		super();
	}
	

	public Commentaire(int id_commentaire, Date date_creation, String message, Site site, Utilisateur utilisateur) {
		super();
		this.id_commentaire = id_commentaire;
		this.date_creation = date_creation;
		this.message = message;
		this.site = site;
		this.utilisateur = utilisateur;
	}



	public Commentaire(Date date_creation, String message, Site site, Utilisateur utilisateur) {
		super();
		this.date_creation = date_creation;
		this.message = message;
		this.site = site;
		this.utilisateur = utilisateur;
	}


	public int getId_commentaire() {
		return id_commentaire;
	}

	public void setId_commentaire(int id_commentaire) {
		this.id_commentaire = id_commentaire;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	

}

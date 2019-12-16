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
@Table(name="reservation")
public class Reservation {
	
	@Id
	@Column(name = "id_reservation")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_reservation;
	
	@Access(AccessType.FIELD)
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_topo")
	private Topo topo;
	
	@Access(AccessType.FIELD)
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_user")
	private Utilisateur emprunteur;

	public Reservation() {
	}

	public Reservation(int id_reservation, Topo topo, Utilisateur emprunteur) {
		super();
		this.id_reservation = id_reservation;
		this.topo = topo;
		this.emprunteur = emprunteur;
	}

	public int getId_reservation() {
		return id_reservation;
	}

	public void setId_reservation(int id_reservation) {
		this.id_reservation = id_reservation;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
	}
	
	

}

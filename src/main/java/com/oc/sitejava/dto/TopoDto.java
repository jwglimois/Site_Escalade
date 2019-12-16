package com.oc.sitejava.dto;

import java.util.Date;
import java.util.List;

import com.oc.sitejava.entity.Topo;

public class TopoDto {

	private int id_site;
	private int id_proprietaire;
	private String nom_topo;
	private String description;
	private Date date_parution;
	private String statut;
	private List<Topo> listTopo;
	

	public TopoDto() {
		super();
	}


	public TopoDto(int id_site, int id_proprietaire, String nom_topo, String description, Date date_parution,
			String statut) {
		super();
		this.id_site = id_site;
		this.id_proprietaire = id_proprietaire;
		this.nom_topo = nom_topo;
		this.description = description;
		this.date_parution = date_parution;
		this.statut = statut;
	}
	
	
	public TopoDto(List<Topo> listTopo) {
		super();
		this.listTopo = listTopo;
	}


	public int getId_site() {
		return id_site;
	}
	public void setId_site(int id_site) {
		this.id_site = id_site;
	}
	public int getId_proprietaire() {
		return id_proprietaire;
	}
	public void setId_proprietaire(int id_proprietaire) {
		this.id_proprietaire = id_proprietaire;
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


	public List<Topo> getListTopo() {
		return listTopo;
	}


	public void setListTopo(List<Topo> listTopo) {
		this.listTopo = listTopo;
	}

	
}

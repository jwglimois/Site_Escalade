package com.oc.sitejava.dto;

import java.util.List;

import com.oc.sitejava.entity.Site;

public class RechercheDto {
	private List<Site> listSite;
	private List<String> listRegion;
	private List<String> listCotation;
	private String Region;
	private String Cote;

	public RechercheDto() {
		super();
	}




	public RechercheDto( List<String> listRegion, List<String> listCotation) {
		this.listRegion = listRegion;
		this.listCotation = listCotation;
	}




	public RechercheDto(List<String> listRegion, List<String> listCotation, String region, String cote) {
		super();
		this.listRegion = listRegion;
		this.listCotation = listCotation;
		Region = region;
		Cote = cote;
	}




	public List<Site> getListSite() {
		return listSite;
	}

	public void setListSite(List<Site> listSite) {
		this.listSite = listSite;
	}




	public List<String> getListRegion() {
		return listRegion;
	}




	public void setListRegion(List<String> listRegion) {
		this.listRegion = listRegion;
	}




	public List<String> getListCotation() {
		return listCotation;
	}




	public void setListCotation(List<String> listCotation) {
		this.listCotation = listCotation;
	}




	public String getRegion() {
		return Region;
	}




	public void setRegion(String region) {
		Region = region;
	}




	public String getCote() {
		return Cote;
	}




	public void setCote(String cote) {
		Cote = cote;
	}






}

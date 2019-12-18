package com.oc.sitejava.dto;

public class ReserveDto {
	private int idRes;
	private int idTopo;
	private String nomTopo;
	private String emailBorrower;
	private String statut;
	private String prenomOwner;
	private String nomOwner;
	private String emailOwner;
	
	public ReserveDto() {
	}



	public ReserveDto(int idRes, int idTopo, String nomTopo, String emailBorrower, String statut) {
		super();
		this.idRes = idRes;
		this.idTopo = idTopo;
		this.nomTopo = nomTopo;
		this.emailBorrower = emailBorrower;
		this.statut = statut;
	}


	public ReserveDto(int idRes, int idTopo, String nomTopo, String statut, String prenomOwner, String nomOwner,
			String emailOwner) {
		super();
		this.idRes = idRes;
		this.idTopo = idTopo;
		this.nomTopo = nomTopo;
		this.statut = statut;
		this.prenomOwner = prenomOwner;
		this.nomOwner = nomOwner;
		this.emailOwner = emailOwner;
	}



	public String getEmailBorrower() {
		return emailBorrower;
	}



	public void setEmailBorrower(String emailBorrower) {
		this.emailBorrower = emailBorrower;
	}



	public String getPrenomOwner() {
		return prenomOwner;
	}



	public void setPrenomOwner(String prenomOwner) {
		this.prenomOwner = prenomOwner;
	}



	public String getEmailOwner() {
		return emailOwner;
	}



	public void setEmailOwner(String emailOwner) {
		this.emailOwner = emailOwner;
	}



	public int getIdRes() {
		return idRes;
	}

	public void setIdRes(int idRes) {
		this.idRes = idRes;
	}

	public String getNomTopo() {
		return nomTopo;
	}

	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}




	public int getIdTopo() {
		return idTopo;
	}



	public void setIdTopo(int idTopo) {
		this.idTopo = idTopo;
	}



	public String getStatut() {
		return statut;
	}



	public void setStatut(String statut) {
		this.statut = statut;
	}



	public String getNomOwner() {
		return nomOwner;
	}



	public void setNomOwner(String nomOwner) {
		this.nomOwner = nomOwner;
	}








	
}

package com.oc.sitejava.dto;


import com.oc.sitejava.entity.Role;
import com.oc.sitejava.entity.Utilisateur;

public class UserInscriptionDto {

	private Utilisateur utilisateur;
	private Role role;
	
	public UserInscriptionDto() {
		super();
	}
	
	
	public UserInscriptionDto(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
	}


	public UserInscriptionDto(Utilisateur utilisateur, Role role) {
		super();
		this.utilisateur = utilisateur;
		this.role = role;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
}

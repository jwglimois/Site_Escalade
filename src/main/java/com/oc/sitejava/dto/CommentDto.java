package com.oc.sitejava.dto;

import java.util.Date;

import com.oc.sitejava.entity.Commentaire;

public class CommentDto {
	private int id_role;
	private String prenomUser;
	private Date date_creation;
	private String message;
	private int id_site;
	private int id_user;
	private Commentaire comment;
	
	
	public CommentDto() {
		super();
	}
	
	
	public CommentDto(int id_role, String prenomUser, Date date_creation, String message, int id_site) {
		super();
		this.id_role = id_role;
		this.prenomUser = prenomUser;
		this.date_creation = date_creation;
		this.message = message;
		this.id_site = id_site;
	}





	public CommentDto(int id_user, Commentaire comment, String message) {
		super();
		this.id_user = id_user;
		this.comment = comment;
		this.message = message;
	}


	public int getId_role() {
		return id_role;
	}
	public void setId_role(int id_role) {
		this.id_role = id_role;
	}
	public String getPrenomUser() {
		return prenomUser;
	}
	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
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


	public int getId_site() {
		return id_site;
	}


	public void setId_site(int id_site) {
		this.id_site = id_site;
	}


	public int getId_user() {
		return id_user;
	}


	public void setId_user(int id_user) {
		this.id_user = id_user;
	}


	public Commentaire getComment() {
		return comment;
	}


	public void setComment(Commentaire comment) {
		this.comment = comment;
	}


	

}

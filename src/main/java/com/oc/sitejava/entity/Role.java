package com.oc.sitejava.entity;


import java.util.ArrayList;
import java.util.List;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id_role;
	private String type;
	
	@OneToMany(mappedBy="role")
	private List<Utilisateur> users = new ArrayList<>();
	
	public Role() {
		super();
	}

	public Role(String type) {
		super();
		this.type = type;
	}
	

	public Role(int id_role, String type) {
		super();
		this.id_role = id_role;
		this.type = type;
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Role [id_role=" + id_role + ", type=" + type + "]";
	}

	public List<Utilisateur> getUsers() {
		return users;
	}

	public void setUsers(List<Utilisateur> users) {
		this.users = users;
	}
	
	

}

package com.oc.sitejava.entity;



import java.util.List;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Utilisateur {
	
	@Id
	@Column(name="id_user")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id_user;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	private int id_role;
	
	private String password;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Role role = new Role();
	
	@Access(AccessType.FIELD)
	@OneToMany (mappedBy = "utilisateur", cascade = CascadeType.MERGE)
	private List<Commentaire> listCom;
	
	public Utilisateur() {super();}

	public Utilisateur(int id_user, String nom, String prenom, String email, String password) {
		super();
		this.id_user = id_user;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}
	
	

	public Utilisateur(int id_user, String nom, String prenom, String email, String password, int id_role) {
		super();
		this.id_user = id_user;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.id_role = id_role;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Utilisateur [id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", id_role=" + id_role + ", password=" + password + ", role=" + role + "]";
	}

	public List<Commentaire> getListCom() {
		return listCom;
	}

	public void setListCom(List<Commentaire> listCom) {
		this.listCom = listCom;
	}

	
	

	
	

}

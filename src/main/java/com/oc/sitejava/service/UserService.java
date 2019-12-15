package com.oc.sitejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.sitejava.entity.Utilisateur;
import com.oc.sitejava.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void saveByUserDTO(String nom, String prenom, String email, String password, int id_role) {
		userRepository.saveByUserDTO(nom, prenom, email, password, id_role);
	}
	
	public Utilisateur findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Utilisateur getUserByFirstName(String prenomUser) {
		return userRepository.getUserByFirstName(prenomUser);
	}
	
	public List<Utilisateur> listAll(){
		return userRepository.findAll();
	}
	
	public void save(Utilisateur user) {
		userRepository.save(user);
	}
		
	public Utilisateur get(int id_user) {
		return userRepository.findById(id_user).get();
	}
	
	public void delete(int id_user) {
		userRepository.deleteById(id_user);
	}
}

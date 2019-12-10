package com.oc.sitejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.sitejava.entity.Role;
import com.oc.sitejava.repository.RoleRepository;

@Service
public class RoleService {
	

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> listAll(){
		return roleRepository.findAll();
	}
	
	public void save(Role role) {
		roleRepository.save(role);
	}
		
	public Role get(int id_role) {
		return roleRepository.findById(id_role).get();
	}
	
	public void delete(int id_role) {
		roleRepository.deleteById(id_role);
	}
}

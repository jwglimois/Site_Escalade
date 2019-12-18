package com.oc.sitejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.sitejava.entity.Topo;
import com.oc.sitejava.entity.Utilisateur;
import com.oc.sitejava.repository.TopoRepository;

@Service
public class TopoService {

	@Autowired
	private TopoRepository topoRepository;
	
	public void updateTopoStatut(String statut, Integer id_topo) {
		topoRepository.updateTopoStatut(statut, id_topo);
	}
	
	public List<Topo> getlistTopoByUser(Utilisateur utilisateur){
		return topoRepository.getlistTopoByUser(utilisateur);
	}
	
	public List<Topo> listAll(){
		return topoRepository.findAll();
	}
	
	public void save(Topo topo) {
		topoRepository.save(topo);
	}
	
	public Topo get(int id_topo) {
		return topoRepository.findById(id_topo).get();
	}
	
	public void delete(int id_topo) {
		topoRepository.deleteById(id_topo);
	}
}

package com.oc.sitejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.sitejava.entity.Commentaire;
import com.oc.sitejava.entity.Site;
import com.oc.sitejava.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository comRepository;
	
	public void updateCommentaire(String message, Integer id_commentaire) {
		comRepository.updateCommentaire(message, id_commentaire);
	}
	
	public List<Commentaire> getlistCommentBySite(Site site){
		return comRepository.getlistCommentBySite(site);
	}
	
	public List<Commentaire> listAll(){
		return comRepository.findAll();
	}
	
	public void save(Commentaire com) {
		comRepository.save(com);
	}
	
	public Commentaire get(int id_commentaire) {
		return comRepository.findById(id_commentaire).get();
	}
	
	public void delete(int id_commentaire) {
		comRepository.deleteById(id_commentaire);
	}
}

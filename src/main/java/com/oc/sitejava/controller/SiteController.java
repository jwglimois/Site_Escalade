package com.oc.sitejava.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oc.sitejava.dto.SiteDto;
import com.oc.sitejava.entity.Site;
import com.oc.sitejava.service.SecteurService;
import com.oc.sitejava.service.SiteService;
import com.oc.sitejava.service.VoieService;


@Controller
public class SiteController {
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private SecteurService secteurService;
	
	@Autowired
	private VoieService voieService;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping(value={"/listsites"}, method = RequestMethod.GET)
	public ModelAndView viewHomePage() {
		
		ModelAndView modelAndView = new ModelAndView();
		
		List<SiteDto> listSites =  siteService.fetchSites();
		
		for(SiteDto ligne : listSites) {
			ligne.setNbSecteur(secteurService.fetchNbSecteur(ligne.getSiteId()));
			ligne.setNbVoie(voieService.fetchNbVoie(ligne.getSiteId()));		

			List<String> listCotations = voieService.fetchCotations(ligne.getSiteId());
			String rangeCote = listCotations.get(0) + "-" + listCotations.get(listCotations.size()-1);
			ligne.setRangeCotation(rangeCote);	
		}
		
		modelAndView.addObject("listSites", listSites);
		
		return modelAndView;
		
	}
	
	@RequestMapping("/createsite")
	public String showNewSiteForm(Model model) {
		Site site = new Site();
		model.addAttribute("site", site);
		
		return "createsite";
	}

}

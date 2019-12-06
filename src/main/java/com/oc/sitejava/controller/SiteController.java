package com.oc.sitejava.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oc.sitejava.dto.SiteDto;
import com.oc.sitejava.dto.SiteFormDto;
import com.oc.sitejava.entity.Longueur;
import com.oc.sitejava.entity.Secteur;
import com.oc.sitejava.entity.Site;
import com.oc.sitejava.entity.Voie;
import com.oc.sitejava.service.LongueurService;
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
	
	@Autowired
	private LongueurService longueurService;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping(value={"/listsites"}, method = RequestMethod.GET)
	public ModelAndView viewListSites(){
		
		ModelAndView modelAndView = new ModelAndView();
		
		List<SiteDto> listSites = new ArrayList<SiteDto>();
				
		for(SiteDto ligne : siteService.fetchSites()) {
			listSites.add(ligne);
		}
		
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
	
	@RequestMapping(value = "/createsite", method = RequestMethod.GET)
	public String showNewSiteForm(Model model) {
		Site site = new Site();
		Secteur secteur = new Secteur();
		Voie voie = new Voie();
		Longueur longueur = new Longueur();
		SiteFormDto formData = new SiteFormDto(site, secteur, voie, longueur);
		model.addAttribute("site", site);
		model.addAttribute("secteur", secteur);
		model.addAttribute("voie", voie);
		model.addAttribute("longueur", longueur);
		model.addAttribute("formData", formData);
		
		return "createsite";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String insertFormData(@ModelAttribute(value="site") SiteFormDto formData) {
		
		Site site = formData.getSite();
		siteService.save(site);
		site.setId_site(siteService.fetchLastIDSite());
		
		Secteur secteur = formData.getSecteur();
		secteur.setSite(site);
		secteurService.save(secteur);
		
		Voie voie = formData.getVoie();
		voie.setSecteur(secteur);
		voieService.save(voie);
		
		Longueur longueur = formData.getLongueur();
		longueur.setVoie(voie);
		longueurService.save(longueur);
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }
	
	@GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
	
	

}

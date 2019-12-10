package com.oc.sitejava.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.oc.sitejava.dto.SiteDto;
import com.oc.sitejava.dto.SiteFormDto;
import com.oc.sitejava.dto.UserInscriptionDto;
import com.oc.sitejava.entity.Longueur;
import com.oc.sitejava.entity.Role;
import com.oc.sitejava.entity.Secteur;
import com.oc.sitejava.entity.Site;
import com.oc.sitejava.entity.Utilisateur;
import com.oc.sitejava.entity.Voie;
import com.oc.sitejava.service.LongueurService;
import com.oc.sitejava.service.RoleService;
import com.oc.sitejava.service.SecteurService;
import com.oc.sitejava.service.SiteService;
import com.oc.sitejava.service.UserService;
import com.oc.sitejava.service.VoieService;


@Controller
@SessionAttributes("infoSession")
public class SessionController {
	

	
/*--------------Generation de session sur Login--------------*/
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/index")
	public String index(Model model, HttpSession session ) {
		
		@SuppressWarnings("unchecked")
		List<String> infoSession = (List<String>)session.getAttribute("infoSession");
		model.addAttribute("infoSession", infoSession);
		return "index";
	}
	
	//Créer un objet session sur la page login
	@RequestMapping("/login")
	public String process(Model model, HttpSession session) {
		
		@SuppressWarnings("unchecked")
		List<String> infoSession = (List<String>)session.getAttribute("infoSession");
		
		if(infoSession == null) {
			infoSession = new ArrayList<>();
		}
		model.addAttribute("infoSession" , infoSession);
		
		for(String sessionID : infoSession) {
			System.out.println("Session entrée --method GET : " + sessionID );
		}
		
		return "login";
	}
	

	
	@RequestMapping(value={"/login"}, method = RequestMethod.POST)
    public String stockLoginToSession(@RequestParam("username") String username, HttpServletRequest request) {

		@SuppressWarnings("unchecked")
		List<String> infoSession = (List<String>) request.getSession().getAttribute("infoSession");
		
		if (infoSession == null) {
			infoSession = new ArrayList<>();
			request.getSession().setAttribute("infoSession", infoSession);
		}
		
		//Add the Identifiant (email)
		infoSession.add(username);
		infoSession.add("test");
		request.getSession().setAttribute("MY_SESSION_INFO", infoSession);
		System.out.println("Username entrée --method Post : " + username );	
		return "redirect:/login";
    }

	
	@PostMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login?logout";
	}

	@Autowired
	private SiteService siteService;
	
	@Autowired
	private SecteurService secteurService;
	
	@Autowired
	private VoieService voieService;
	
	@Autowired
	private LongueurService longueurService;
	
/*--------------Control du site--------------*/
	
	@RequestMapping(value={"/listsites"}, method = RequestMethod.GET)
	public ModelAndView viewListSites(HttpSession session){
		
		@SuppressWarnings("unchecked")
		List<String> infoSession = (List<String>)session.getAttribute("infoSession");
		
		ModelAndView modelAndView = new ModelAndView();
		
		List<SiteDto> listSites = siteService.fetchSites();
		
		for(SiteDto ligne : listSites) {
			ligne.setNbSecteur(secteurService.fetchNbSecteur(ligne.getSiteId()));
			ligne.setNbVoie(voieService.fetchNbVoie(ligne.getSiteId()));		

			List<String> listCotations = voieService.fetchCotations(ligne.getSiteId());
			String rangeCote = listCotations.get(0) + "-" + listCotations.get(listCotations.size()-1);
			ligne.setRangeCotation(rangeCote);	
		}
		
		modelAndView.addObject("listSites", listSites);
		modelAndView.addObject("infoSession", infoSession);
		
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
	public String insertSiteFormData(@ModelAttribute(value="formData") SiteFormDto formData) {
		
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
		
		
		return "redirect:/index";
	}
	
/* --------------Inscription -------------*/	

	@Autowired
    private UserService userService;
	
	@Autowired
    private RoleService roleService;

	@RequestMapping(value = "/inscription", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
		
		Utilisateur utilisateur = new Utilisateur();
		Role role = new Role();
		UserInscriptionDto userDto = new UserInscriptionDto(utilisateur, role);
	
		model.addAttribute("role", role);
		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("userDto", userDto);
		
		
        return "inscription";
    }
	
	@RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public String registerUserAccount(@ModelAttribute("userDto") @Valid UserInscriptionDto userDto, 
                                      BindingResult result){

		List<Role> listRole = roleService.listAll();
		Utilisateur user = userDto.getUtilisateur();
		
        Utilisateur existingUser = userService.findByEmail(user.getEmail());
        if (existingUser  != null){
        	return "redirect:/inscription?error";
        }
        if (result.hasErrors()){    	       	
            return "redirect:/inscription?error";     
        }
        
        
        for(Role r: listRole) {
        	if( r.getType().equals(userDto.getRole().getType()) ){
        		user.setId_role(r.getId_role());
        	}
        }
        System.out.println(user.toString());
    
        userService.saveByUserDTO(user.getNom(), user.getPrenom(), user.getEmail(), user.getPassword(), user.getId_role());
        return "redirect:/inscription?success";
    }

	
}



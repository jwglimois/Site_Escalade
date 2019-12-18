package com.oc.sitejava.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.oc.sitejava.dto.CommentDto;
import com.oc.sitejava.dto.LongueurDto;
import com.oc.sitejava.dto.RechercheDto;
import com.oc.sitejava.dto.ReserveDto;
import com.oc.sitejava.dto.SecteurDto;
import com.oc.sitejava.dto.SiteDto;
import com.oc.sitejava.dto.SiteFormDto;
import com.oc.sitejava.dto.TopoDto;
import com.oc.sitejava.dto.UserInscriptionDto;
import com.oc.sitejava.dto.VoieDto;
import com.oc.sitejava.entity.Commentaire;
import com.oc.sitejava.entity.Longueur;
import com.oc.sitejava.entity.Reservation;
import com.oc.sitejava.entity.Role;
import com.oc.sitejava.entity.Secteur;
import com.oc.sitejava.entity.Site;
import com.oc.sitejava.entity.Topo;
import com.oc.sitejava.entity.Utilisateur;
import com.oc.sitejava.entity.Voie;
import com.oc.sitejava.service.CommentService;
import com.oc.sitejava.service.LongueurService;
import com.oc.sitejava.service.ReserveService;
import com.oc.sitejava.service.RoleService;
import com.oc.sitejava.service.SecteurService;
import com.oc.sitejava.service.SiteService;
import com.oc.sitejava.service.TopoService;
import com.oc.sitejava.service.UserService;
import com.oc.sitejava.service.VoieService;


@Controller
@SessionAttributes({"infoSession", "idUserSession", "allSitesSession"})
public class SessionController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private SecteurService secteurService;
	
	@Autowired
	private VoieService voieService;
	
	@Autowired
	private LongueurService longueurService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private RoleService roleService;
	
	@Autowired
	private CommentService comService;

	@Autowired
	private TopoService topoService;
	
	@Autowired
	private ReserveService reserveService;

	
/*--------------Generation de session sur Login--------------*/
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/index")
	public String index(Model model ) {
		
		return "index";
	}
	

	@GetMapping("/login")
	public String login(Model model ) {
		
		return "login";
	}
	
	
	@RequestMapping(value={"/connexion"}, method = RequestMethod.POST)
    public String stockLoginToSession(@RequestParam("username") String username, HttpServletRequest request) {

		//Vérifiy Si login exist
		Utilisateur user = userService.findByEmail(username);
		if(user != null) {
			
			@SuppressWarnings("unchecked")
			List<String> infoSession = (List<String>) request.getSession().getAttribute("infoSession");
		
			//Vider la liste de anciens sessions
			if(infoSession != null) {
				infoSession.clear();
			}
			
			if (infoSession == null) {
				infoSession = new ArrayList<>();
				request.getSession().setAttribute("infoSession", infoSession);
			}
			
			
			//Add Id_role
			Utilisateur currentUser = userService.findByEmail(username);
			
			String id_role = String.valueOf(currentUser.getId_role()) ;
			String prenomUser = currentUser.getPrenom() ;
			infoSession.add(id_role);
			infoSession.add(prenomUser);
	
			//Ajouter le 2er object Session : idUser
			@SuppressWarnings("unchecked")
			List<String> idUserSession = (List<String>) request.getSession().getAttribute("idUserSession");
			//Vider la liste de anciens sessions
			if(idUserSession != null) {
				idUserSession.clear();
			}
			if (idUserSession== null) {
				idUserSession= new ArrayList<>();
				request.getSession().setAttribute("idUserSession", idUserSession);
			}
			String id_user = Long.toString(currentUser.getId_user());
			idUserSession.add(id_user);
			
			return "redirect:/index?success";
		}else {
			return "redirect:/login?error";
		}

    }

/* function destroy dans le menu.html*/	
	@PostMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
			
			HttpSession session=request.getSession(false);
			if (session != null && session.getAttribute("infoSession") != null) {
				//Il faut vider la liste Session avant que l'objet Session crée un autre qui la stockera
				@SuppressWarnings("unchecked")
				List<String> infoSession = (List<String>) request.getSession().getAttribute("infoSession");
				infoSession.clear();
				
				request.getSession().invalidate();
				return "redirect:/index?logout";
			} else {
				return "redirect:/index";
			}
		
	}


	

	
/* --------------Inscription -------------*/	



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
    
        userService.saveByUserDTO(user.getNom(), user.getPrenom(), user.getEmail(), DigestUtils.md5Hex(user.getPassword()), user.getId_role());
        return "redirect:/inscription?success";
    }

	
/*--------------Control du site--------------*/

	
	@RequestMapping(value={"/listsites"}, method = RequestMethod.GET)
	public String viewListSites(Model model){


		List<SiteDto> listSites = siteService.fetchSites();
		
		for(SiteDto ligne : listSites) {
			ligne.setNbSecteur(secteurService.fetchNbSecteur(ligne.getSiteId()));
			ligne.setNbVoie(voieService.fetchNbVoie(ligne.getSiteId()));		

			List<String> listCotations = voieService.fetchCotations(ligne.getSiteId());
			String rangeCote = listCotations.get(0) + "-" + listCotations.get(listCotations.size()-1);
			ligne.setRangeCotation(rangeCote);	
		}
		
		model.addAttribute("listSites", listSites);
	
		return "listsites";
		
	}
	
	//Fichesite.html --Use Thymeleaf URL Path
	@GetMapping("/{id:\\d+}")
	public String showSingleSite(
			@PathVariable("id") int id, 
			Model model,
			HttpSession session) {
		
		Site site = siteService.get(id);
		
		List<Secteur> listSecteur = site.getlistSecteurs();

		model.addAttribute("site", site);
		model.addAttribute("listSecteur", listSecteur);
		
		/* Site image à implémenter après
		String siteImage = "/img/s_" + site.getId_site()+ ".jpg";
		model.addAttribute("siteImage", siteImage);
		*/
		CommentDto comDto = new CommentDto();
		comDto.setId_site(site.getId_site());
		model.addAttribute("comDto", comDto);
		
		List<Commentaire> listCom = comService.getlistCommentBySite(site);
		model.addAttribute("listCom", listCom);
		
		List<VoieDto> listVoieDto1 = new ArrayList<VoieDto>();
		for(Voie v: listSecteur.get(0).getListVoie() ) {
			String nomVoie = v.getNomVoie();
			int totalHauteur = voieService.getTotalHauteur(v.getId_voie());
			int nbLongueur = voieService.getNbLongueur(v.getId_voie());
			String cote = v.getCotation();
			listVoieDto1.add(new VoieDto(nomVoie, totalHauteur, nbLongueur, cote ));
		}
		model.addAttribute("listVoieDto1", listVoieDto1);
		
		
		if(listSecteur.size()==2) {	
			List<VoieDto> listVoieDto2 = new ArrayList<VoieDto>();
			for(Voie v: listSecteur.get(1).getListVoie() ) {
				String nomVoie = v.getNomVoie();
				int totalHauteur = voieService.getTotalHauteur(v.getId_voie());
				int nbLongueur = voieService.getNbLongueur(v.getId_voie());
				String cote = v.getCotation();
				listVoieDto2.add(new VoieDto(nomVoie, totalHauteur, nbLongueur, cote ));
			}
			model.addAttribute("listVoieDto2", listVoieDto2);
		}
		
		if(listSecteur.size()==3) {
			List<VoieDto> listVoieDto2 = new ArrayList<VoieDto>();
			for(Voie v: listSecteur.get(1).getListVoie() ) {
				String nomVoie = v.getNomVoie();
				int totalHauteur = voieService.getTotalHauteur(v.getId_voie());
				int nbLongueur = voieService.getNbLongueur(v.getId_voie());
				String cote = v.getCotation();
				listVoieDto2.add(new VoieDto(nomVoie, totalHauteur, nbLongueur, cote ));
			}
			model.addAttribute("listVoieDto2", listVoieDto2);
			
			List<VoieDto> listVoieDto3 = new ArrayList<VoieDto>();
			for(Voie v: listSecteur.get(2).getListVoie() ) {
				String nomVoie = v.getNomVoie();
				int totalHauteur = voieService.getTotalHauteur(v.getId_voie());
				int nbLongueur = voieService.getNbLongueur(v.getId_voie());
				String cote = v.getCotation();
				listVoieDto3.add(new VoieDto(nomVoie, totalHauteur, nbLongueur, cote ));
			}
			model.addAttribute("listVoieDto3", listVoieDto3);
		}
		
		//show Topo
		List<Topo> listTopo = site.getListTopo();
		model.addAttribute("listTopo", listTopo);
		
		List<Utilisateur> listOwer = new ArrayList<>();
		for(Topo t: listTopo) {
			listOwer.add(t.getProprietaire());
		}
	
		
		return "fichesite";
	}

/*--------------Taguer le site----------------*/

	@RequestMapping(value = "/taguerSite/{idSite}", method = RequestMethod.POST)
	public String taguerSite(
			@PathVariable(name="idSite") int idSite,
			HttpSession session) {
		

		
		return null;
	}
	
	
/*--------------Commentaires------------------*/
	
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public String insertComment(
			@ModelAttribute(value="comDto") CommentDto comDto,
			@RequestParam(name="idSite") Integer idSite,
			HttpSession session) {
	
		
		@SuppressWarnings("unchecked")
		List<String> listSession = (List<String>) session.getAttribute("infoSession");
		Utilisateur user = userService.getUserByFirstName(listSession.get(1));
		System.out.println("---------print utilisateur :" + user.toString() );
		Site site = siteService.get(idSite);
		
		Date currentDate = new Date(System.currentTimeMillis());
		Commentaire com = new Commentaire(currentDate, comDto.getMessage(), site, user);

		comService.save(com);

		
		return "redirect:/" + idSite;
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditCommentForm(
			@PathVariable(name="id") int id,
			@ModelAttribute(value="comDto") CommentDto comDto) {
		ModelAndView mav = new ModelAndView("edit_comment");
		
		Commentaire comment = comService.get(id);
		
		comDto = new CommentDto(comment);

		mav.addObject("comDto", comDto);

		
		return mav;
	}
	
	@RequestMapping(value = "/modifyComment/{idCom}", method = RequestMethod.POST)
	public String modifyComment(
			@ModelAttribute(value="comDto") CommentDto comDto,
			@PathVariable("idCom") Integer idCom, 
			@RequestParam("newMessage") String newMessage,
			Model model) {
		
		Commentaire comment = comService.get(idCom);
		int idSite = comment.getSite().getId_site();
		comDto = new CommentDto(comment);
		model.addAttribute("comDto", comDto);

		
		comService.updateCommentaire(newMessage, idCom);
		
		return "redirect:/"+idSite ;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteComment(@PathVariable(name="id") int id) {
		Site site = comService.get(id).getSite();
		int idSite = site.getId_site();
		comService.deleteCommentaire(id);
		return "redirect:/"+idSite ;
	}

/*--------------Site------------------*/
	
	@RequestMapping(value = "/createsite", method = RequestMethod.GET)
	public String showSiteForm(Model model) {
		Site site = new Site();
		SiteFormDto formData = new SiteFormDto(site);
		model.addAttribute("site", site);
		model.addAttribute("formData", formData);
		return "createsite";
	}	
	
	@RequestMapping(value = "/saveSite", method = RequestMethod.POST)
	public String insertSiteFormData(@ModelAttribute(value="formData") SiteFormDto formData) {
		
		Site site = formData.getSite();
		siteService.save(site);
		
		return "redirect:/createsite/secteur";
	}
	
	@RequestMapping(value = "/createsite/secteur", method = RequestMethod.GET)
	public String showSecteurForm(Model model) {	
		Site site = siteService.get(siteService.fetchLastIDSite());
		Secteur secteur1 = new Secteur();
		Secteur secteur2 = new Secteur();
		Secteur secteur3 = new Secteur();
		
		SecteurDto secteurDto = new SecteurDto(secteur1, secteur2, secteur3, site);
		
		model.addAttribute("secteur1", secteur1);
		model.addAttribute("secteur2", secteur2);
		model.addAttribute("secteur3", secteur3);
		model.addAttribute("site", site);
		model.addAttribute("secteurDto", secteurDto);

		return "createsite/secteur";
	}

	@RequestMapping(value = "/saveSecteur", method = RequestMethod.POST)
	public String insertSecteurFormData(@ModelAttribute(value="secteurDto") SecteurDto secteurDto) {
		
		Site site = siteService.get(siteService.fetchLastIDSite());
		Secteur secteur1 = secteurDto.getSecteur1();
		secteur1.setSite(site);
		
		Secteur secteur2 = secteurDto.getSecteur2();
		secteur2.setSite(site);
		
		Secteur secteur3 = secteurDto.getSecteur3();
		secteur3.setSite(site);
		
		if(secteur1.getNomSecteur()!= null && !secteur1.getNomSecteur().equals("")) {
			secteurService.save(secteur1);
		}
		
		if(secteur2.getNomSecteur()!= null && !secteur2.getNomSecteur().equals("")) {
			secteurService.save(secteur2);
		}
		
		if(secteur3.getNomSecteur()!= null && !secteur3.getNomSecteur().equals("")) {
			secteurService.save(secteur3);
		}
		
		return "redirect:/createsite/voie";
	}
	
	@RequestMapping(value = "/createsite/voie", method = RequestMethod.GET)
	public String showVoieForm(Model model) {
		Site site = siteService.get(siteService.fetchLastIDSite());
		List<Secteur> listSecteur = site.getlistSecteurs();
		Secteur secteur1 = listSecteur.get(0);

		Secteur secteur2 = new Secteur();
		if(listSecteur.size()==2) {
			secteur2 = listSecteur.get(1);}
		
		Secteur secteur3 = new Secteur();
		if(listSecteur.size()==3) {
			secteur3 = listSecteur.get(2);}
		
		Voie voie1 = new Voie();
		Voie voie2 = new Voie();
		Voie voie3 = new Voie();
		Voie voie4 = new Voie();
		Voie voie5 = new Voie();
		Voie voie6 = new Voie();
		VoieDto voieDto = new VoieDto(voie1, voie2, voie3, voie4, voie5, voie6, secteur1, secteur2, secteur3);
		
		model.addAttribute("secteur1", secteur1);
		model.addAttribute("secteur2", secteur2);
		model.addAttribute("secteur3", secteur3);
		model.addAttribute("voie1", voie1);
		model.addAttribute("voie2", voie2);
		model.addAttribute("voie3", voie3);
		model.addAttribute("voie4", voie4);
		model.addAttribute("voie5", voie5);
		model.addAttribute("voie6", voie6);
		model.addAttribute("voieDto", voieDto);
		
		return "createsite/voie";
	}	
	
	@RequestMapping(value = "/saveVoie", method = RequestMethod.POST)
	public String insertVoieFormData(@ModelAttribute(value="voieDto") VoieDto voieDto) {
		
		Site site = siteService.get(siteService.fetchLastIDSite());
		List<Secteur> listSecteur = site.getlistSecteurs();
		Secteur secteur1 = listSecteur.get(0);
		
		Voie voie1 = voieDto.getVoie1();
		if(voie1.getNomVoie()!= null && !voie1.getNomVoie().equals("")) {
			voie1.setSecteur(secteur1);
			voieService.insertVoie(voie1.getCotation(), voie1.getNomVoie(), voie1.getSecteur().getId_secteur());
		}

		Voie voie2 = voieDto.getVoie2();
		if(voie2.getNomVoie()!= null && !voie2.getNomVoie().equals("")) {
			voie2.setSecteur(secteur1);
			voieService.insertVoie(voie2.getCotation(), voie2.getNomVoie(), voie2.getSecteur().getId_secteur());
		}			
		
		Voie voie3 = voieDto.getVoie3();
		Voie voie4 = voieDto.getVoie4();
		
		if(listSecteur.size()==2) {
			Secteur secteur2 = listSecteur.get(1);
			if(voie3.getNomVoie()!= null && !voie3.getNomVoie().equals("")) {
				voie3.setSecteur(secteur2);
				voieService.insertVoie(voie3.getCotation(), voie3.getNomVoie(), voie3.getSecteur().getId_secteur());
			}
			if(voie4.getNomVoie()!= null && !voie4.getNomVoie().equals("")) {
				voie4.setSecteur(secteur2);
				voieService.insertVoie(voie4.getCotation(), voie4.getNomVoie(), voie4.getSecteur().getId_secteur());
			}	
		}
		
		Voie voie5 = voieDto.getVoie5();
		Voie voie6 = voieDto.getVoie6();
		
		if(listSecteur.size()==3) {
			Secteur secteur2 = listSecteur.get(2);
			if(voie3.getNomVoie()!= null && !voie3.getNomVoie().equals("")) {
				voie3.setSecteur(secteur2);
				voieService.insertVoie(voie3.getCotation(), voie3.getNomVoie(), voie3.getSecteur().getId_secteur());
			}
			if(voie4.getNomVoie()!= null && !voie4.getNomVoie().equals("")) {
				voie4.setSecteur(secteur2);
				voieService.insertVoie(voie4.getCotation(), voie4.getNomVoie(), voie4.getSecteur().getId_secteur());
			}
			
			Secteur secteur3 = voieDto.getSecteur3();
			if(voie5.getNomVoie()!= null && !voie5.getNomVoie().equals("")) {
				voie5.setSecteur(secteur3);
				voieService.insertVoie(voie5.getCotation(), voie5.getNomVoie(), voie5.getSecteur().getId_secteur());
			}
			if(voie6.getNomVoie()!= null && !voie6.getNomVoie().equals("")) {
				voie6.setSecteur(secteur3);
				voieService.insertVoie(voie6.getCotation(), voie6.getNomVoie(), voie6.getSecteur().getId_secteur());
			}
		}
		
		return "redirect:/createsite/longueur";
	}
	
	@RequestMapping(value = "/createsite/longueur", method = RequestMethod.GET)
	public String showLongueurForm(Model model) {	
		Site site = siteService.get(siteService.fetchLastIDSite());
		List<Secteur> listSecteur = site.getlistSecteurs();
		
		//S'il n'y que Secteur 1
		List<Voie> listVoieS1 = new ArrayList<>();
		for(Voie v: listSecteur.get(0).getListVoie()) {
			listVoieS1.add(v);
		}
		Voie v1 = listVoieS1.get(0);
		
		Voie v2 = new Voie();
		if(listVoieS1.size()==2) {
			v2 = listVoieS1.get(1);
		}

		//S'il y a aussi secteur 2
		Voie v3 = new Voie();
		Voie v4 = new Voie();		
		if(listSecteur.size()==2) {
			List<Voie> listVoieS2 = new ArrayList<>();
			for(Voie v: listSecteur.get(1).getListVoie()) {
				listVoieS2.add(v);
			}
			v3 = listVoieS2.get(0);
			if(listVoieS2.size()==2) {
				v4 = listVoieS2.get(1);
			}
		}

		//S'il y a secteur 2 + secteur 3
		Voie v5 = new Voie();
		Voie v6 = new Voie();
		if(listSecteur.size()==3) {
			//Voie3 & voie4 du secteur2
			List<Voie> listVoieS2 = new ArrayList<>();
			for(Voie v: listSecteur.get(1).getListVoie()) {
				listVoieS2.add(v);
			}
			v3 = listVoieS2.get(0);
			if(listVoieS2.size()==2) {
				v4 = listVoieS2.get(1);
			}
			
			//Voie5 & voie6 du secteur3
			List<Voie> listVoieS3 = new ArrayList<>();
			for(Voie v: listSecteur.get(2).getListVoie()) {
				listVoieS3.add(v);
			}
			v5 = listVoieS3.get(0);
			if(listVoieS3.size()==2) {
				v6 = listVoieS3.get(1);
			}
		}
		
		LongueurDto longueurDto = new LongueurDto(v1, v2, v3, v4, v5, v6);
		
		model.addAttribute("voie1", v1);
		model.addAttribute("voie2", v2);
		model.addAttribute("voie3", v3);
		model.addAttribute("voie4", v4);
		model.addAttribute("voie5", v5);
		model.addAttribute("voie6", v6);
		model.addAttribute("longueurDto", longueurDto);
		return "createsite/longueur";
	}
	
	@RequestMapping(value = "/saveLongueur", method = RequestMethod.POST)
	public String insertLongueurFormData(@ModelAttribute(value="longueurDto") LongueurDto longueurDto) {
		Site site = siteService.get(siteService.fetchLastIDSite());
		//Un site a 3 secteurs au max.
		List<Secteur> listSecteur = site.getlistSecteurs();
		
		//Voie 1 du Secteur 1
		List<Voie> listVoieS1 = new ArrayList<>();
		for(Voie v: listSecteur.get(0).getListVoie()) {
			listVoieS1.add(v);
		}
		
		
		Voie voie1 = listVoieS1.get(0);
		
		Voie voie2 = new Voie();
		Voie voie3 = new Voie();
		Voie voie4 = new Voie();
		Voie voie5 = new Voie();
		Voie voie6 = new Voie();
		
		//Remove les longueur ayant hautueur =0
		List<Longueur> listLongueurV1Temp = new ArrayList<>();
		for(Longueur lg : longueurDto.getVoie1().getListLongueur() ) {
			if(lg.getHauteur()!=0) {
				listLongueurV1Temp.add(lg);
			}
		}
		
		voie1.setListLongueur(listLongueurV1Temp);
		
		//Cette liste a l'objet "Voie" et le tableau "ListLongueur"
		List<Longueur> listLongueurV1 = new ArrayList<>(voie1.getListLongueur());
		
		for(int i=0; i<listLongueurV1.size();i++) {
			listLongueurV1.get(i).setVoie(voie1);
			
		}
		
		
		for(int i=0; i<listLongueurV1.size();i++) {
			longueurService.save(listLongueurV1.get(i));
		
		}
		
		//Voie 2 du Secteur 1 
		if(listVoieS1.size()==2) {
			voie2 = listVoieS1.get(1);
			
			//Remove les longueur ayant hautueur =0
			List<Longueur> listLongueurV2Temp = new ArrayList<>();
			for(Longueur lg : longueurDto.getVoie2().getListLongueur() ) {
				if(lg.getHauteur()!=0) {
					listLongueurV2Temp.add(lg);
				}
			}
			
			voie2.setListLongueur(listLongueurV2Temp);
			List<Longueur> listLongueurV2 = voie2.getListLongueur();
			
			for(int i=0; i<listLongueurV2.size();i++) {
				listLongueurV2.get(i).setVoie(voie2);
			}
			
			for(Longueur l : listLongueurV2) {
				longueurService.save(l);
			}
		}
	
		System.out.println("---------------Size listSecteur :"  + listSecteur.size());
		//Voie 3 du Secteur 2
		if(listSecteur.size() ==2) {
			List<Voie> listVoieS2 = new ArrayList<>();
			for(Voie v: listSecteur.get(1).getListVoie()) {
				listVoieS2.add(v);
			}
			voie3 = listVoieS2.get(0);
			
			//Remove les longueurs ayant hautueur =0
			List<Longueur> listLongueurV3Temp = new ArrayList<>();
			for(Longueur lg : longueurDto.getVoie3().getListLongueur() ) {
				if(lg.getHauteur()!=0) {
					listLongueurV3Temp.add(lg);
				}
			}
			voie3.setListLongueur(listLongueurV3Temp);
			List<Longueur> listLongueurV3 = voie3.getListLongueur();
			
			for(int i=0; i<listLongueurV3.size();i++) {
				listLongueurV3.get(i).setVoie(voie3);
			}
			
			for(Longueur l : listLongueurV3) {
				longueurService.save(l);
			}
			
			//Voie 4 du Secteur 2
			if(listVoieS2.size() ==2) {
				voie4 = listVoieS2.get(1);
				
				//Remove les longueurs ayant hautueur =0
				List<Longueur> listLongueurV4Temp = new ArrayList<>();
				for(Longueur lg : longueurDto.getVoie4().getListLongueur() ) {
					if(lg.getHauteur()!=0) {
						listLongueurV4Temp.add(lg);
					}
				}
				voie4.setListLongueur(listLongueurV4Temp);
				List<Longueur> listLongueurV4 = voie4.getListLongueur();
				
				for(int i=0; i<listLongueurV4.size();i++) {
					listLongueurV4.get(i).setVoie(voie4);
				}
				
				for(Longueur l : listLongueurV4) {
					longueurService.save(l);
				}
			}
		}
		//Si le secteur 3 existe, on aura les secteurs 2 et 3
		if(listSecteur.size()==3) {
			
			//Voie 3 du Secteur 2
			List<Voie> listVoieS2 = new ArrayList<>();
			for(Voie v: listSecteur.get(1).getListVoie()) {
				listVoieS2.add(v);
			}
			voie3 = listVoieS2.get(0);
			//Remove les longueurs ayant hautueur =0
			List<Longueur> listLongueurV3Temp = new ArrayList<>();
			for(Longueur lg : longueurDto.getVoie3().getListLongueur() ) {
				if(lg.getHauteur()!=0) {
					listLongueurV3Temp.add(lg);
				}
			}
			voie3.setListLongueur(listLongueurV3Temp);
			
			List<Longueur> listLongueurV3 = voie3.getListLongueur();
			
			for(int i=0; i<listLongueurV3.size();i++) {
				listLongueurV3.get(i).setVoie(voie3);
			}
			
			for(Longueur l : listLongueurV3) {
				longueurService.save(l);
			}
			
			//Voie 4 du Secteur 2
			if(listVoieS2.size() ==2) {
				voie4 = listVoieS2.get(1);
				
				//Remove les longueurs ayant hautueur =0
				List<Longueur> listLongueurV4Temp = new ArrayList<>();
				for(Longueur lg : longueurDto.getVoie4().getListLongueur() ) {
					if(lg.getHauteur()!=0) {
						listLongueurV4Temp.add(lg);
					}
				}
				voie4.setListLongueur(listLongueurV4Temp);
				List<Longueur> listLongueurV4 = voie4.getListLongueur();
				
				for(int i=0; i<listLongueurV4.size();i++) {
					listLongueurV4.get(i).setVoie(voie4);
				}
				
				for(Longueur l : listLongueurV4) {
					longueurService.save(l);
				}
			}
			
			//Voie 5 du Secteur 3
			List<Voie> listVoieS3 = new ArrayList<>();
			for(Voie v: listSecteur.get(2).getListVoie()) {
				listVoieS3.add(v);
			}
			voie5 = listVoieS3.get(0);
			
			//Remove les longueurs ayant hautueur =0
			List<Longueur> listLongueurV5Temp = new ArrayList<>();
			for(Longueur lg : longueurDto.getVoie5().getListLongueur() ) {
				if(lg.getHauteur()!=0) {
					listLongueurV5Temp.add(lg);
				}
			}
			voie5.setListLongueur(listLongueurV5Temp);
			
			List<Longueur> listLongueurV5 = voie5.getListLongueur();
			
			for(int i=0; i<listLongueurV5.size();i++) {
				listLongueurV5.get(i).setVoie(voie5);
			}
			
			for(Longueur l : listLongueurV5) {
				longueurService.save(l);
			}
			
			//Voie 6 du Secteur 3
			if(listVoieS3.size() ==2) {
				voie6 = listVoieS3.get(1);
				
				//Remove les longueurs ayant hautueur =0
				List<Longueur> listLongueurV6Temp = new ArrayList<>();
				for(Longueur lg : longueurDto.getVoie6().getListLongueur() ) {
					if(lg.getHauteur()!=0) {
						listLongueurV6Temp.add(lg);
					}
				}
				voie6.setListLongueur(listLongueurV6Temp);
				voie6.setListLongueur(longueurDto.getVoie6().getListLongueur());
				List<Longueur> listLongueurV6 = voie6.getListLongueur();
				
				for(int i=0; i<listLongueurV6.size();i++) {
					listLongueurV6.get(i).setVoie(voie6);
				}
				
				for(Longueur l : listLongueurV6) {
					longueurService.save(l);
				}
				
			}
		}		
		
		return "redirect:/index?successSite";
	}
	

	
/*--------------Topo------------------*/
	
	@GetMapping("/compte")
	public String showCompte(@ModelAttribute(value="topoDto") TopoDto topoDto, Model model, HttpSession session ) {
		
		@SuppressWarnings("unchecked")
		List<String> listSession = (List<String>) session.getAttribute("infoSession");
		Utilisateur user = userService.getUserByFirstName(listSession.get(1));
		
		List<Topo> listTopo = topoService.getlistTopoByUser(user);
		topoDto = new TopoDto(listTopo);
		model.addAttribute("topoDto", topoDto);
		model.addAttribute("listTopo", listTopo);
		
		//Gestion Réservation - Reception demandes
		List<Topo> listTopoRes = new ArrayList<>();
		
		for(int i=0; i<listTopo.size();i++) {
			if(listTopo.get(i).getStatut().equals("attente") && listTopo.get(i).getProprietaire().getId_user()== user.getId_user() ) {
				listTopoRes.add(listTopo.get(i));
			}
		}
		System.out.println("Size of listTopoRes--------" + listTopoRes.size());
		for(Topo t: listTopoRes) {
			System.out.println("MalistTopoRes est --------" + t.getId_topo());
		}
		
		if(listTopoRes.size()>0) {
			List<Reservation> listResAll = reserveService.listAll();
			List<Reservation> listRes = new ArrayList<>();
			
			for(int i=0; i<listTopoRes.size();i++) {
				for(int j=0; j<listResAll.size(); j++) {
					if(listTopoRes.get(i).getId_topo() == listResAll.get(j).getTopo().getId_topo()) {
						listRes.add(listResAll.get(j));
					}
				}
			}
			
			for(Reservation r: listRes) {
				System.out.println("MalistRes est --------" + r.getId_reservation());
			}		
			
			List<ReserveDto> listResDtoRec = new ArrayList<>();
			for(Reservation r : listRes ) {
				listResDtoRec.add(new ReserveDto( r.getId_reservation(), r.getTopo().getId_topo(), r.getTopo().getNom_topo(),
						r.getEmprunteur().getEmail(), r.getTopo().getStatut() ));
			}
			model.addAttribute("listResDtoRec", listResDtoRec);
			
			for(ReserveDto rDto3: listResDtoRec) {
				System.out.println("MalistResDtoRec est --------" + rDto3.getIdRes());
			}	
			
		}
		
		//Reservation - Mes demandes envoyées
		List<Reservation> listResAll2 = reserveService.listAll();
		List<Reservation> listRes2 = new ArrayList<>();
		for(int i=0; i<listResAll2 .size();i++) {
			if(listResAll2.get(i).getEmprunteur().getId_user()==(int)user.getId_user()) {
				listRes2.add(listResAll2.get(i));
			}
		}

		
		if(listRes2.size()>0) {
			List<ReserveDto> listResDtoAsk = new ArrayList<>();
			for(Reservation r: listRes2) {
				listResDtoAsk.add(new ReserveDto(r.getId_reservation(), r.getTopo().getId_topo(),
						r.getTopo().getNom_topo(), r.getTopo().getStatut(),r.getTopo().getProprietaire().getPrenom(),
						r.getTopo().getProprietaire().getNom(), r.getTopo().getProprietaire().getEmail()));
			}
			
			
			model.addAttribute("listResDtoAsk", listResDtoAsk);
			
		}
		
		return "compte";
	}	
	
	@RequestMapping(value = "/saveTopo", method = RequestMethod.POST)
    public String saveTopo(
    		@ModelAttribute(value="topoDto") TopoDto topoDto, 
    		@RequestParam("id_site") Integer id_site, 
    		@RequestParam(name = "dateP") String dateP,
    		Model model , 
    		HttpSession session) throws ParseException {
		@SuppressWarnings("unchecked")
		List<String> listSession = (List<String>) session.getAttribute("infoSession");
		Utilisateur user = userService.getUserByFirstName(listSession.get(1));
		
		
		Topo topo = new Topo();
		topo.setNom_topo(topoDto.getNom_topo());
		
		SimpleDateFormat formatteur = new SimpleDateFormat("yyyy-MM-dd");
		
		topo.setDate_parution(formatteur.parse(dateP));
		topo.setDescription(topoDto.getDescription());
		topo.setStatut("dispo");
		topo.setProprietaire(user);
		topo.setSite(siteService.get(id_site));
		
		topoService.save(topo);
		
		return "redirect:/compte";
	}
	
/*---------Gestion de réservation-----------*/	
	
	@RequestMapping("/reserver/{idOwner}")
	public String reserverTopo(
			@PathVariable(name="idOwner") int idOwner,
			@RequestParam(name="idTopo") int idTopo,
			@RequestParam(name="idBorrower") String idBorrower,
			Model model) {
		Utilisateur owner =userService.get(idOwner);
		
		System.out.println("------owner = " + owner.toString());
		System.out.println("------idTopo = " + idTopo);
		System.out.println("------idBorrower = " + idBorrower);
		
		topoService.updateTopoStatut("attente", idTopo);
		
		Topo topoAsk = topoService.get(idTopo);
		Utilisateur borrower = userService.get(Integer.parseInt(idBorrower));
		Reservation res = new Reservation(topoAsk, borrower);
		reserveService.save(res);
		

		return "redirect:/compte";
	}

	@RequestMapping(value = "/accorderReservation", method = RequestMethod.POST)
    public String accorderReservation(
    		@RequestParam(value = "idResOk", required = false) int[] idResOk,
    		@RequestParam(value = "idResNo", required = false) int[] idResNo
    		) {
		
		boolean isResOKExist = false;
		boolean isResNoExist = false;
		
		if(idResOk!=null) {
			isResOKExist = true;
			//Trouver la list de Reservation ayant accord
			List<Reservation> listResAll= reserveService.listAll();
			List<Reservation> listResOk = new ArrayList<>();
			for(int i=0; i<listResAll.size(); i++) {
				for(int j=0; j<idResOk.length; j++) {
					if(listResAll.get(i).getId_reservation() == idResOk[j]) {
						listResOk.add(listResAll.get(i));
					}
				}
			}
			//Trouver la liste de Topo ayant accord
			List<Topo> listTopoAll = topoService.listAll();
			List<Topo> listTopoOk = new ArrayList<>();
			for(int i=0; i<listResOk.size(); i++) {
				for(int j=0; j<listTopoAll.size();j++) {
					if(listResOk.get(i).getTopo().getId_topo()==listTopoAll.get(j).getId_topo()) {
						listTopoOk.add(listResOk.get(i).getTopo());
					}
				}
			}
			
			for(Topo t: listTopoOk) {
				topoService.updateTopoStatut("prete", t.getId_topo());
			}
		}
		
		if(idResNo != null) {
			isResNoExist = true;
			//Trouver la list de Reservation ayant refus
			List<Reservation> listResAll= reserveService.listAll();
			List<Reservation> listResNo = new ArrayList<>();
			for(int i=0; i<listResAll.size(); i++) {
				for(int j=0; j<idResNo.length; j++) {
					if(listResAll.get(i).getId_reservation() == idResNo[j]) {
						listResNo.add(listResAll.get(i));
					}
				}
			}
			
			//Trouver la liste de Topo ayant accord
			List<Topo> listTopoAll = topoService.listAll();
			List<Topo> listTopoNo = new ArrayList<>();
			for(int i=0; i<listResNo.size(); i++) {
				for(int j=0; j<listTopoAll.size();j++) {
					if(listResNo.get(i).getTopo().getId_topo()==listTopoAll.get(j).getId_topo()) {
						listTopoNo.add(listResNo.get(i).getTopo());
					}
				}
			}
			
			for(Topo t: listTopoNo) {
				topoService.updateTopoStatut("dispo", t.getId_topo());
			}
		}
		
		if(isResOKExist == false && isResNoExist == false ) {
			return "redirect:/compte?warning";
		}
		
		return "redirect:/compte?confirm";
		
		
	}
	
	
/* ------Recherche par Multicritères ----------- */	
	
	@GetMapping("/recherche")
	public String showRecherche(@ModelAttribute(value="rechercheDto") RechercheDto recDto, Model model ) {
	List<Site> listSite = siteService.listAll();
	List<String> listRegion = new ArrayList<>();
	for(int i=0; i<listSite.size(); i++) {
		listRegion.add(listSite.get(i).getRegion());
	}
	
	List<Voie> listVoie = voieService.listAll();
	List<String> listCote = new ArrayList<>();
	for(int i=0; i<listVoie.size(); i++) {
		listCote.add(listVoie.get(i).getCotation());
	}
	
	recDto = new RechercheDto(listRegion, listCote);
	model.addAttribute("recDto" , recDto);
	model.addAttribute("listRegion" , listRegion);
	model.addAttribute("listCote" , listCote);
	
	return "recherche";
	}
	
	@RequestMapping(value = "/sendQuery", method = RequestMethod.POST)
    public String sendQuery(
    		@ModelAttribute(value="rechercheDto") RechercheDto recDto,
    		Model model) {
	
	System.out.println("---------recDto = " + recDto);	
	
	String region = recDto.getRegion();
	System.out.println("---------region = " + region);	
	
	String cotation = recDto.getCote();
	System.out.println("---------cotation = " + cotation);	
	
	int idSite = siteService.getIdSiteBySearch(region, cotation);

    return "redirect:/"+idSite;
	}
	

	
}



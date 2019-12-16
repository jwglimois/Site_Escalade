package com.oc.sitejava.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.oc.sitejava.dto.SecteurDto;
import com.oc.sitejava.dto.SiteDto;
import com.oc.sitejava.dto.SiteFormDto;
import com.oc.sitejava.dto.TopoDto;
import com.oc.sitejava.dto.UserInscriptionDto;
import com.oc.sitejava.dto.VoieDto;
import com.oc.sitejava.entity.Commentaire;
import com.oc.sitejava.entity.Longueur;
import com.oc.sitejava.entity.Role;
import com.oc.sitejava.entity.Secteur;
import com.oc.sitejava.entity.Site;
import com.oc.sitejava.entity.Topo;
import com.oc.sitejava.entity.Utilisateur;
import com.oc.sitejava.entity.Voie;
import com.oc.sitejava.service.CommentService;
import com.oc.sitejava.service.LongueurService;
import com.oc.sitejava.service.RoleService;
import com.oc.sitejava.service.SecteurService;
import com.oc.sitejava.service.SiteService;
import com.oc.sitejava.service.TopoService;
import com.oc.sitejava.service.UserService;
import com.oc.sitejava.service.VoieService;


@Controller
@SessionAttributes("infoSession")
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
	
			request.getSession().setAttribute("MY_SESSION_INFO", infoSession);
			System.out.println("Username entrée --method Post : " + username );	
			
			return "redirect:/login?success";
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
	
/*--------------Commentaires------------------*/
	
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public String insertComment(
			@ModelAttribute(value="comDto") CommentDto comDto,
			@RequestParam(name="idSite") Integer idSite,
			HttpSession session) {
		
		
		System.out.println("---------print id_site :" + idSite );
		
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
			voieService.save(voie1);
		}

		Voie voie2 = voieDto.getVoie2();
		if(voie2.getNomVoie()!= null && !voie2.getNomVoie().equals("")) {
			voie2.setSecteur(secteur1);
			voieService.save(voie2);
		}			
		
		Voie voie3 = voieDto.getVoie3();
		Voie voie4 = voieDto.getVoie4();
		
		if(listSecteur.size()==2) {
			Secteur secteur2 = listSecteur.get(1);
			if(voie3.getNomVoie()!= null && !voie3.getNomVoie().equals("")) {
				voie3.setSecteur(secteur2);
				voieService.save(voie3);
			}
			if(voie4.getNomVoie()!= null && !voie4.getNomVoie().equals("")) {
				voie4.setSecteur(secteur2);
				voieService.save(voie4);
			}	
		}
		
		Voie voie5 = voieDto.getVoie5();
		Voie voie6 = voieDto.getVoie6();
		
		if(listSecteur.size()==3) {
			Secteur secteur2 = listSecteur.get(2);
			if(voie3.getNomVoie()!= null && !voie3.getNomVoie().equals("")) {
				voie3.setSecteur(secteur2);
				voieService.save(voie3);
			}
			if(voie4.getNomVoie()!= null && !voie4.getNomVoie().equals("")) {
				voie4.setSecteur(secteur2);
				voieService.save(voie4);
			}
			
			Secteur secteur3 = voieDto.getSecteur3();
			if(voie5.getNomVoie()!= null && !voie5.getNomVoie().equals("")) {
				voie5.setSecteur(secteur3);
				voieService.save(voie5);
			}
			if(voie6.getNomVoie()!= null && !voie6.getNomVoie().equals("")) {
				voie6.setSecteur(secteur3);
				voieService.save(voie6);
			}
		}
		
		return "redirect:/createsite/longueur";
	}
	
	@RequestMapping(value = "/createsite/longueur", method = RequestMethod.GET)
	public String showLongueurForm(Model model) {	
		Site site = siteService.get(siteService.fetchLastIDSite());
		List<Secteur> listSecteur = site.getlistSecteurs();
		
		//S'il n'y que Secteur 1
		List<Voie> listVoie1 = new ArrayList<>();
		for(Voie v: listSecteur.get(0).getListVoie()) {
			listVoie1.add(v);
		}
		Voie v1 = listVoie1.get(0);
		
		Voie v2 = new Voie();
		if(listVoie1.size()==2) {
			v2 = listVoie1.get(1);
		}

		//S'il y a aussi secteur 2
		Voie v3 = new Voie();
		Voie v4 = new Voie();		
		if(listSecteur.size()==2) {
			List<Voie> listVoie2 = new ArrayList<>();
			for(Voie v: listSecteur.get(1).getListVoie()) {
				listVoie2.add(v);
			}
			v3 = listVoie2.get(0);
			if(listVoie2.size()==2) {
				v4 = listVoie2.get(1);
			}
		}

		//S'il y a secteur 2 + secteur 3
		Voie v5 = new Voie();
		Voie v6 = new Voie();
		if(listSecteur.size()==3) {
			//Voie3 & voie4 du secteur2
			List<Voie> listVoie2 = new ArrayList<>();
			for(Voie v: listSecteur.get(1).getListVoie()) {
				listVoie2.add(v);
			}
			v3 = listVoie2.get(0);
			if(listVoie2.size()==2) {
				v4 = listVoie2.get(1);
			}
			
			//Voie5 & voie6 du secteur3
			List<Voie> listVoie3 = new ArrayList<>();
			for(Voie v: listSecteur.get(2).getListVoie()) {
				listVoie3.add(v);
			}
			v5 = listVoie3.get(0);
			if(listVoie3.size()==2) {
				v6 = listVoie3.get(1);
			}
		}
		
		String th1 = new String();
		String th2 = new String();
		String th3 = new String();
		String th4 = new String();
		String th5 = new String();
		String th6 = new String();
		LongueurDto longueurDto = new LongueurDto(v1, v2, v3, v4, v5, v6, th1, th2, th3, th4, th5, th6 );
		
		model.addAttribute("voie1", v1);
		model.addAttribute("voie2", v2);
		model.addAttribute("voie3", v3);
		model.addAttribute("voie4", v4);
		model.addAttribute("voie5", v5);
		model.addAttribute("voie6", v6);
		model.addAttribute("toutHauteur1", th1);
		model.addAttribute("toutHauteur2", th2);
		model.addAttribute("toutHauteur3", th3);
		model.addAttribute("toutHauteur4", th4);
		model.addAttribute("toutHauteur5", th5);
		model.addAttribute("toutHauteur6", th6);
		model.addAttribute("longueurDto", longueurDto);
		return "createsite/longueur";
	}
	
	@RequestMapping(value = "/saveLongueur", method = RequestMethod.POST)
	public String insertLongueurFormData(@ModelAttribute(value="longueurDto") LongueurDto longueurDto) {
		Site site = siteService.get(siteService.fetchLastIDSite());
		//Un site a 3 secteurs au max.
		List<Secteur> listSecteur = site.getlistSecteurs();
		
		//Voie 1 du Secteur 1
		List<Voie> listVoie1 = new ArrayList<>();
		for(Voie v: listSecteur.get(0).getListVoie()) {
			listVoie1.add(v);
		}
		
		Voie voie1 = listVoie1.get(0);
		Voie voie2 = new Voie();
		Voie voie3 = new Voie();
		Voie voie4 = new Voie();
		Voie voie5 = new Voie();
		Voie voie6 = new Voie();
		
		List<Integer> ArrLongueur1 = this.convertirStrToArrInt(longueurDto.getToutHauteur1());
		List<Longueur> listLongueur1 = new ArrayList<>();
		for(int i=0; i<ArrLongueur1.size(); i++) {
			listLongueur1.add(new Longueur());
			listLongueur1.get(i).setHauteur(ArrLongueur1.get(i));
			listLongueur1.get(i).setVoie(voie1);
			longueurService.save(listLongueur1.get(i));
		}
		
		//Voie 2 du Secteur 1 
		if(listVoie1.size()==2) {
			voie2 = listVoie1.get(1);
			List<Integer> ArrLongueur2 = this.convertirStrToArrInt(longueurDto.getToutHauteur2());
			List<Longueur> listLongueur2 = new ArrayList<>();
			for(int i=0; i<ArrLongueur2.size(); i++) {
				listLongueur2.add(new Longueur());
				listLongueur2.get(i).setHauteur(ArrLongueur2.get(i));
				listLongueur2.get(i).setVoie(voie2);
				longueurService.save(listLongueur2.get(i));
			}	
		}
		
		//Voie 3 du Secteur 2
		if(listSecteur.size() ==2) {
			List<Voie> listVoie2 = new ArrayList<>();
			for(Voie v: listSecteur.get(1).getListVoie()) {
				listVoie2.add(v);
			}
			voie3 = listVoie2.get(0);
			
			List<Integer> ArrLongueur3 = this.convertirStrToArrInt(longueurDto.getToutHauteur3());
			//test
			System.out.println("Input du secteur 2 pour voie 3 est ------" + longueurDto.getToutHauteur3());
			
			
			List<Longueur> listLongueur3 = new ArrayList<>();
			for(int i=0; i<ArrLongueur3.size(); i++) {
				listLongueur3.add(new Longueur());
				listLongueur3.get(i).setHauteur(ArrLongueur3.get(i));
				listLongueur3.get(i).setVoie(voie3);
				longueurService.save(listLongueur3.get(i));
			}
			
			//Voie 4 du Secteur 2
			if(listVoie2.size() ==2) {
				voie4 = listVoie2.get(1);
				
				List<Integer> ArrLongueur4 = this.convertirStrToArrInt(longueurDto.getToutHauteur4());
				List<Longueur> listLongueur4 = new ArrayList<>();
				for(int i=0; i<ArrLongueur4.size(); i++) {
					listLongueur4.add(new Longueur());
					listLongueur4.get(i).setHauteur(ArrLongueur4.get(i));
					listLongueur4.get(i).setVoie(voie4);
					longueurService.save(listLongueur4.get(i));
				}
			}
		}
		//Si le secteur 3 existe, on aura le secteur 2 + 3
		if(listSecteur.size()==3) {
			
			//Voie 3 du Secteur 2
			List<Voie> listVoie2 = new ArrayList<>();
			for(Voie v: listSecteur.get(1).getListVoie()) {
				listVoie2.add(v);
			}
			voie3 = listVoie2.get(0);
			
			List<Integer> ArrLongueur3 = this.convertirStrToArrInt(longueurDto.getToutHauteur3());
			List<Longueur> listLongueur3 = new ArrayList<>();
			for(int i=0; i<ArrLongueur3.size(); i++) {
				listLongueur3.add(new Longueur());
				listLongueur3.get(i).setHauteur(ArrLongueur3.get(i));
				listLongueur3.get(i).setVoie(voie3);
				longueurService.save(listLongueur3.get(i));
			}
			
			//Voie 4 du Secteur 2
			if(listVoie2.size() ==2) {
				voie4 = listVoie2.get(1);
				
				List<Integer> ArrLongueur4 = this.convertirStrToArrInt(longueurDto.getToutHauteur4());
				List<Longueur> listLongueur4 = new ArrayList<>();
				for(int i=0; i<ArrLongueur4.size(); i++) {
					listLongueur4.add(new Longueur());
					listLongueur4.get(i).setHauteur(ArrLongueur4.get(i));
					listLongueur4.get(i).setVoie(voie4);
					longueurService.save(listLongueur4.get(i));
				}
			}
			
			//Voie 5 du Secteur 3
			List<Voie> listVoie3 = new ArrayList<>();
			for(Voie v: listSecteur.get(2).getListVoie()) {
				listVoie3.add(v);
			}
			voie5 = listVoie3.get(0);
			
			List<Integer> ArrLongueur5 = this.convertirStrToArrInt(longueurDto.getToutHauteur5());
			List<Longueur> listLongueur5 = new ArrayList<>();
			for(int i=0; i<ArrLongueur5.size(); i++) {
				listLongueur5.add(new Longueur());
				listLongueur5.get(i).setHauteur(ArrLongueur5.get(i));
				listLongueur5.get(i).setVoie(voie5);
				longueurService.save(listLongueur5.get(i));
			}
			
			//Voie 6 du Secteur 3
			if(listVoie3.size() ==2) {
				voie6 = listVoie3.get(1);
				
				List<Integer> ArrLongueur6 = this.convertirStrToArrInt(longueurDto.getToutHauteur6());
				List<Longueur> listLongueur6 = new ArrayList<>();
				for(int i=0; i<ArrLongueur6.size(); i++) {
					listLongueur6.add(new Longueur());
					listLongueur6.get(i).setHauteur(ArrLongueur6.get(i));
					listLongueur6.get(i).setVoie(voie6);
					longueurService.save(listLongueur6.get(i));
				}
			}
		}		
		
		return "redirect:/index?successSite";
	}
	
	//Methode utilisé par la function: insertLongueurFormData()
	public List<Integer> convertirStrToArrInt(String str) {
		String[] arrStr = str.split(",");
		List<Integer> arrInt = new ArrayList<>();
		for(String s: arrStr) {
			arrInt.add(Integer.parseInt(s));
		}
		return arrInt;
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
	
	@RequestMapping("/reserver/{idOwner}")
	public String reserverTopo(
			@PathVariable(name="idOwner") int idOwner,
			Model model) {
		Utilisateur owner =userService.get(idOwner);
		System.out.println("------owner = " + owner);
		model.addAttribute("owner", owner);
		return "contact";
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



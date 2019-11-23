package com.oc.sitejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.oc.sitejava.entity.Site;
import com.oc.sitejava.service.SiteService;


@Controller
public class SiteController {
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/listsites")
	public String viewHomePage(Model model) {
		List<Site> listSites =siteService.listAll();
		model.addAttribute("listSites", listSites);
		
		return "listsites";
	}
	

}

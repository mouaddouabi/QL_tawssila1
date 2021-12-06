package org.sid.web;

import java.security.Principal;


import org.sid.Services.ProfilService;
import org.sid.dao.UserRepository;
import org.sid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccueilController {


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfilService profilService;

	
	@RequestMapping(value="/")
	public String accueil(Model model, Principal user)
	{
		
		if(user != null)
		{
			Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String username;
			if(principal instanceof UserDetails)
			{
				  username = ((UserDetails)principal).getUsername();
				
			}else {
				 username = principal.toString();
			}
			
			User utilisateur = userRepository.findByEmail(username);
			
			model.addAttribute("prenom", utilisateur.getPrenom());
			model.addAttribute("nom",utilisateur.getNom());
		}
		
		return "accueil";
	}
	
	@RequestMapping(value="/contact")
	public String contact(Model model , Principal user)
	{
		
		if(user != null)
		{
			Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String username;
			if(principal instanceof UserDetails)
			{
				  username = ((UserDetails)principal).getUsername();
				
			}else {
				 username = principal.toString();
			}
			
			User utilisateur = userRepository.findByEmail(username);
			
			model.addAttribute("prenom", utilisateur.getPrenom());
			model.addAttribute("nom",utilisateur.getNom());
		}
		
		return "contact";
	}
	
	@RequestMapping(value="/mentions-legales")
	public String mention(Model model , Principal user)
	{
		
		if(user != null)
		{
			Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String username;
			if(principal instanceof UserDetails)
			{
				  username = ((UserDetails)principal).getUsername();
				
			}else {
				 username = principal.toString();
			}
			
			User utilisateur = userRepository.findByEmail(username);
			
			model.addAttribute("prenom", utilisateur.getPrenom());
			model.addAttribute("nom",utilisateur.getNom());
		}
		
		return "mentions";
	}
	
	@RequestMapping(value="/a_propos")
	public String propos(Model model , Principal user)
	{
		
		if(user != null)
		{
			Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String username;
			if(principal instanceof UserDetails)
			{
				  username = ((UserDetails)principal).getUsername();
				
			}else {
				 username = principal.toString();
			}
			
			User utilisateur = userRepository.findByEmail(username);
			
			model.addAttribute("prenom", utilisateur.getPrenom());
			model.addAttribute("nom",utilisateur.getNom());
		}
		
		return "a_propos";
	}

	@RequestMapping(value="/profil")
	public String profil(Model model)
	{
		Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username;
		if(principal instanceof UserDetails)
		{
			  username = ((UserDetails)principal).getUsername();
			
		}else {
			 username = principal.toString();
		}
		
		User utilisateur = userRepository.findByEmail(username);
		
		model.addAttribute("user", utilisateur);
		
		
		
		return profilService.monProfil(model);
	}
	
	
	
	
	@RequestMapping(value="/mesAnnonces")
	public String mesAnnonces(Model model , Principal user)
	{
		

		if(user != null)
		{
			Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String username;
			if(principal instanceof UserDetails)
			{
				  username = ((UserDetails)principal).getUsername();
				
			}else {
				 username = principal.toString();
			}
			
			User utilisateur = userRepository.findByEmail(username);
			
			model.addAttribute("prenom", utilisateur.getPrenom());
			model.addAttribute("nom",utilisateur.getNom());
		}
		
		
		
		
		Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username;
		if(principal instanceof UserDetails)
		{
			  username = ((UserDetails)principal).getUsername();
			
		}else {
			 username = principal.toString();
		}
		
		User utilisateur = userRepository.findByEmail(username);
		
		model.addAttribute("user", utilisateur);
		
		
		
		return profilService.mesAnnonces(model);
	}


	@RequestMapping(value="/login")
	public String login()
	{
		return "login";
	}

	@RequestMapping(value="/commentcamarche" ,method = RequestMethod.GET)
	public String comment(Model model , Principal user)
	{

		if(user != null)
		{
			Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String username;
			if(principal instanceof UserDetails)
			{
				  username = ((UserDetails)principal).getUsername();
				
			}else {
				 username = principal.toString();
			}
			
			User utilisateur = userRepository.findByEmail(username);
			
			model.addAttribute("prenom", utilisateur.getPrenom());
			model.addAttribute("nom",utilisateur.getNom());
		}
		
		
		return "commentcamarche";
	}
	
	@RequestMapping(value="/logout")
	public String logout()
	{
		return "logout";
	}
	
	
	@RequestMapping(value="/quartierPassager")
	public String quartierPassager()
	{
		return "annoncesQuartierPassager";
	}
	
	@RequestMapping(value="/quartierConducteur")
	public String quartierConducteur()
	{
		return "annoncesQuartierConducteur";
	}
	
	@RequestMapping(value="/villePassager")
	public String villePassager()
	{
		return "annoncesVillePassager";
	}
	
	@RequestMapping(value="/villeConducteur")
	public String villeConducteur()
	{
		
		
		
		return "annoncesVilleConducteur";
	}
	
	@RequestMapping(value="/annoncePassager")
	public String annoncePassager()
	{
		return "annoncePassager";
	}
	
	@RequestMapping(value="/annonceConducteur")
	public String annonceConducteur()
	{
		return "annonceConducteur";
	}
	
}

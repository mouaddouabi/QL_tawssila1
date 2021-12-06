package org.sid.Services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.sid.dao.AnnonceRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Annonce;
import org.sid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AnnonceService {

	@Autowired
	private AnnonceRepository annonceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private User userAnnonceGet;
	
	public AnnonceService() {
		// TODO Auto-generated constructor stub
	}
	public String showForm(Model model) {
		
		User utilisateur;
		Annonce annonce = new Annonce();
		List <Annonce> annonces = annonceRepository.findAll();
		model.addAttribute("annonce",annonce);
		
		model.addAttribute("List",annonces);
		String username;
		
		Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails)
		{
			  username = ((UserDetails)principal).getUsername();
			
		}else {
			 username = principal.toString();
		}
		
		utilisateur = userRepository.findByEmail(username);
		
		model.addAttribute("prenom",utilisateur.getNom());
		model.addAttribute("nom",utilisateur.getPrenom());
		
		
		//Long id_user = utilisateur.getId();
		
		model.addAttribute("user" , utilisateur);
		
		return "annonceConducteur";
	
	}
	public String Submit(@ModelAttribute("annonce") Annonce annonce , Model model )
	{ 	annonce.setType("conducteur");
		annonce.setCommentaire(annonce.getCommentaire());
		annonce.setRating(annonce.getRating());
		annonce.setSexe(annonce.getSexe());
		
		Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username;
		if(principal instanceof UserDetails)
		{
			  username = ((UserDetails)principal).getUsername();
			
		}else {
			 username = principal.toString();
		}
		
		User utilisateur = userRepository.findByEmail(username);
		
		model.addAttribute("prenom",utilisateur.getNom());
		model.addAttribute("nom",utilisateur.getPrenom());
		
		annonce.setUser(utilisateur);
		annonceRepository.save(annonce); 
		List<Annonce> list= annonceRepository.findAll();
		Collections.reverse(list);
	    model.addAttribute("List", list);
		
		
		if(annonce.isAuSeinVille()==true)
		{
			 return "redirect:/QuartierConducteur?success";
		}
		else 
		{
			return "redirect:/VilleConducteur?success";
		}
	 
	}
public String showFormPassager(Model model) {
		
		User utilisateur;
		Annonce annonce = new Annonce();
		List <Annonce> annonces = annonceRepository.findAll();
		
		model.addAttribute("annonce",annonce);
		model.addAttribute("List",annonces);
		String username;
		
		Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails)
		{
			  username = ((UserDetails)principal).getUsername();
			
		}else {
			 username = principal.toString();
		}
		
		utilisateur = userRepository.findByEmail(username);
		
		model.addAttribute("prenom",utilisateur.getNom());
		model.addAttribute("nom",utilisateur.getPrenom());
		
		
		//Long id_user = utilisateur.getId();
		
		model.addAttribute("user" , utilisateur);
		
		return "annoncePassager";
	
	}
	public String SubmitPassager(@ModelAttribute("annonce") Annonce annonce , Model model )
	{ 	annonce.setType("passager");
		annonce.setCommentaire(annonce.getCommentaire());
		annonce.setRating(annonce.getRating());
		annonce.setSexe(annonce.getSexe());
		
		Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username;
		if(principal instanceof UserDetails)
		{
			  username = ((UserDetails)principal).getUsername();
			
		}else {
			 username = principal.toString();
		}
		
		User utilisateur = userRepository.findByEmail(username);
		annonce.setUser(utilisateur);
		annonceRepository.save(annonce); 
		
		
		
		if(annonce.isAuSeinVille()==true )
		{	List<Annonce> list= annonceRepository.findbyType("Passager",true);
		Collections.reverse(list);
		    model.addAttribute("List",list);
			 return "redirect:/QuartierPassager?success";
		}
		else 
		{	List<Annonce> list= annonceRepository.findbyType("Passager",false);
		Collections.reverse(list);
		     model.addAttribute("List",list);
			return "redirect:/VillePassager?success";
		}
		
	
		}
			public String showFormPassagerQuartier(Model model)
			{ 	 
				List<Annonce> list= annonceRepository.findbyType("Passager",true);
				Collections.reverse(list);
				model.addAttribute("List",list);
				return "annoncesQuartierPassager";
			}
			public String showFormPassagerVille(Model model) {
				
				List<Annonce> list= annonceRepository.findbyType("Passager",false);
				
				Collections.reverse(list);
				model.addAttribute("List",list);
				
				return "annoncesVillePassager";
			}
			public String showFormConducteurQuartier(Model model)
			{
				List<Annonce> list= annonceRepository.findbyType("Conducteur",true);
				Collections.reverse(list);
				model.addAttribute("List",list);
				return "annoncesQuartierConducteur";
			}
			
			
			public String showFormConducteurVille(Model model)
			{
				List<Annonce> list= annonceRepository.findbyType("Conducteur",false);
				Collections.reverse(list);
				model.addAttribute("List",list);
				return "annoncesVilleConducteur";
			}
			
			
			
			
			
			
			
			public String profilAnnonceur(Model model, @RequestParam String userAnnonce)
			{
				Long id = Long.parseLong(userAnnonce);
				
				Optional<User> user = userRepository.findById(id);
				
				User utilisateur;
				
				utilisateur = user.get();
				
				model.addAttribute("user",utilisateur);
				return "profilAnnonceur";
			}
			
			
			//modifierAnnonce
			public String modiferAnnonce(Model model)
			{
				Optional<Annonce> annonce ;
				Long idAnnonce = Long.parseLong( (String) model.getAttribute("idAnnonce") );
				
				annonce =  annonceRepository.findById(idAnnonce);
				
				Annonce monAnnonce;
				
				monAnnonce= annonce.get();
				
				model.addAttribute("annonce",monAnnonce);
				if(monAnnonce.getType().equals("conducteur")) {
					
					if(monAnnonce.isAuSeinVille())
					{
						
						return "modifierAnnonceConducteurQuartier";
					}
					else
					{
						return "modifierAnnonceConducteurVille";
					}
					
				}
				
				else {
					
					if(monAnnonce.isAuSeinVille())
					{
						return "modifierAnnoncePassagerQuartier";
					}
					else
					{
						return "modifierAnnoncePassagerVille";
					}
					
				}
				

			}
			public String UpdateAnnonce(@ModelAttribute("annonce")Annonce annonce , Model model ,Principal user )
			{
				
				
				annonceRepository.updateAnnonce( annonce.getTelephone(),annonce.isAutoroute() , annonce.isAuSeinVille() , annonce.getDescription() , annonce.getDate() , annonce.isFumeur()
						, annonce.getLieuArrive() , annonce.getLieuDepart() , annonce.isMusic() , annonce.getNbPlaces() , annonce.getPrix() , annonce.getSexe() ,  annonce.getTailleBagage()
						, annonce.getType() , annonce.getVehicule() ,annonce.getId());
				

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
					
					model.addAttribute("prenom",utilisateur.getNom());
					model.addAttribute("nom",utilisateur.getPrenom());
					
				Collection<Annonce> annonces ;
				
				
				annonces = annonceRepository.findByUser(utilisateur);
				
				Collections.reverse((List<?>) annonces);
				
				model.addAttribute("mesAnnonces",annonces);
				
				
				
				}
				
				return "redirect:/mesAnnonces?success";
				
			}
			
			
			//supprimerrAnnonce
			public String supprimerAnnonce(Model model, Principal user)
			{
				Optional<Annonce> annonce ;
				Long idAnnonce = Long.parseLong( (String) model.getAttribute("idAnnonce") );
				
				annonce =  annonceRepository.findById(idAnnonce);
				
				Annonce monAnnonce;
				
				monAnnonce= annonce.get();
				
				model.addAttribute("annonce",monAnnonce);
				
				annonceRepository.delete(monAnnonce);
				
				 /////////////////////////////////
				
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

				Collection<Annonce> annonces ;
				
				
				annonces = annonceRepository.findByUser(utilisateur);
				
				
				model.addAttribute("mesAnnonces",annonces);
				
				
				
				}
				
				return "redirect:/mesAnnonces?succeseDelete";

			}
			
			
			//detailsAnnonce
			public String detailsAnnonce(Model model)
			{
				Optional<Annonce> annonce ;
				Long idAnnonce = Long.parseLong( (String) model.getAttribute("idAnnonce") );
				
				annonce =  annonceRepository.findById(idAnnonce);
				
				Annonce monAnnonce;
				
				monAnnonce= annonce.get();
				
				model.addAttribute("annonce",monAnnonce);
				if(monAnnonce.getType().equals("conducteur")) {
					
					if(monAnnonce.isAuSeinVille())
					{
						
						return "detailsAnnonceConducteurQuartier";
					}
					else
					{
						return "detailsAnnonceConducteurVille";
					}
					
				}
				
				else {
					
					if(monAnnonce.isAuSeinVille())
					{
						return "detailsAnnoncePassagerQuartier";
					}
					else
					{
						return "detailsAnnoncePassagerVille";
					}
					
				}
				

			}
			
			
			//rating annonce
			public String ratingAnnonce(Model model)
			{
				Optional<Annonce> annonce ;
				Long idAnnonce = Long.parseLong( (String) model.getAttribute("idAnnonce") );
				
				annonce =  annonceRepository.findById(idAnnonce);
				
				Annonce monAnnonce;
				
				monAnnonce= annonce.get();
				
				model.addAttribute("annonce",monAnnonce);
				
				model.addAttribute("codeRating", monAnnonce.getRating());
				
				double rating = (double) model.getAttribute("rating");
				
				annonceRepository.updateAnnonceRating(rating, idAnnonce);
				
				if(monAnnonce.getType().equals("conducteur")) {
					
					if(monAnnonce.isAuSeinVille())
					{
						
						return "redirect:/QuartierConducteur?successRating";
					}
					else
					{
						return "redirect:/VilleConducteur?successRating";
					}
					
				}
				
				else {
					
					if(monAnnonce.isAuSeinVille())
					{
						return "redirect:/QuartierPassager?successRating";
					}
					else
					{
						return "redirect:/VillePassager?successRating";
					}
					
				}
				

			}
			
			
	}
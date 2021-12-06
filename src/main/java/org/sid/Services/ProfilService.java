package org.sid.Services;

import java.util.Collection;
import java.util.List;

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

@Service
public class ProfilService {
	
	@Autowired
	private AnnonceRepository annonceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public ProfilService() {
		super();
		// TODO Auto-generated constructor stub
	}

	//monProfil
	public String monProfil(Model model)
	{
		
		Collection<Annonce> annonces ;
		User user =  (User)model.getAttribute("user");
		
		annonces = annonceRepository.findByUser(user);
		
		model.addAttribute("nom", user.getNom());
		model.addAttribute("prenom",user.getPrenom());
		model.addAttribute("age", user.getAge());
		model.addAttribute("password", user.getPassword());
		model.addAttribute("annonces", user.getAnnonces());
		model.addAttribute("biographie", user.getBiographie());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("image", user.getImage());
		
		model.addAttribute("sexe", user.getSexe());
		
		double rating = user.getRating();
		
		
		
		
		
		int sizeAnnonces = annonces.size();
		
		for (Annonce annonce : annonces) {
			rating = rating + annonce.getRating();
		}
		
		if(sizeAnnonces != 0.0)
		{
			rating = Math.ceil((double)rating/(double)sizeAnnonces);
		}
		else
			rating = 0.0;
		
		user.setRating(rating);
		
		
		model.addAttribute("rating", user.getRating());
		
		
		model.addAttribute("sizeAnnonces",sizeAnnonces);
		
		
		
		return "profil";
		
	}
	
	//mesAnnonces
	
	public String mesAnnonces(Model model)
	{
		
		Collection<Annonce> annonces ;
		User user =  (User)model.getAttribute("user");
		
		annonces = annonceRepository.findByUser(user);
		
		
		model.addAttribute("mesAnnonces",annonces);
		
		return "mesAnnonces";
		
	}
	
	public String Submit(@ModelAttribute("user") Annonce annonce , Model model )
	{ 	annonce.setType("conducteur");
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
		List<Annonce> list= annonceRepository.findAll();
	    model.addAttribute("List", list);
		
		
		if(annonce.isAuSeinVille()==true)
		{
			 return "annoncesQuartierConducteur";
		}
		else 
		{
			return "annoncesVilleConducteur";
		}
	 
	}
	
}

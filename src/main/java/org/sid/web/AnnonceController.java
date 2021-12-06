package org.sid.web;

import java.io.IOException;


import org.springframework.util.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.sid.Services.AnnonceService;
import org.sid.dao.AnnonceRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Annonce;
import org.sid.entities.User;
import org.sid.metier.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class AnnonceController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private AnnonceRepository annonceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private User userAnnonceGet;
	private Annonce monAnnonce;
	private User myUser;
	private Collection<Annonce> annoncesProfilAnnonceur;
	
	
	@Autowired
	private AnnonceService annonceService ; 
	
	@GetMapping(value="/DeposerAnnonceConducteur")
	public String showForm(Model model) {
		 return annonceService.showForm(model) ; 
	}
	
	@PostMapping(value="/DeposerAnnonceConducteur")
	public String Submit(@ModelAttribute("annonce") Annonce annonce , Model model )
	{
		
		 return annonceService.Submit(annonce,model); 
	}
	
	
	@GetMapping(value="/pourquoi")
	public String pq(Model model , Principal user) {
		
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
		
		 return "pourquoi" ; 
	}
	
	
	@GetMapping(value="/DeposerAnnoncePassager")
	public String showFormPassager(Model model) {
		 return annonceService.showFormPassager(model) ; 
	}
	@PostMapping(value="/DeposerAnnoncePassager")
	public String SubmitPassager(@ModelAttribute("annonce") Annonce annonce , Model model , Principal user )
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
		
		
		 return annonceService.SubmitPassager(annonce,model); 
	}
	@GetMapping(value="/QuartierPassager")
	public String showFormPassagerQuartier(Model model, Principal user) {
		
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

		
		 return annonceService.showFormPassagerQuartier(model) ; 
	}
	@GetMapping(value="/VillePassager")
	public String showFormPassagerVille(Model model , Principal user) {
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

		
		 return annonceService.showFormPassagerVille(model) ; 
	}
	@GetMapping(value="/QuartierConducteur")
	public String showFormConducteurQuartier(Model model , Principal user) {
		
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
		
		 return annonceService.showFormConducteurQuartier(model) ; 
	}
	@GetMapping(value="/VilleConducteur")
	public String showFormConducteurVille(Model model , Principal user) {
		
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
		
		 return annonceService.showFormConducteurVille(model) ; 
	}
	
	
	
	
	
	@PostMapping(value="/profilAnnonceur")
	public String profilAnnonceur(Model model, @RequestParam String userAnnonce, Principal user) {
		
		Long id_connecte = null;
		
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
			
			 id_connecte = utilisateur.getId();
			
			model.addAttribute("prenom", utilisateur.getPrenom());
			model.addAttribute("nom",utilisateur.getNom());
		}
		
		System.out.println(userAnnonce);
		Long id = Long.parseLong(userAnnonce);
		
		Optional<User> users = userRepository.findById(id);
		
		User utilisateur;
		
		utilisateur = users.get();
		
		userAnnonceGet = utilisateur;
		
		
		
		Collection<Annonce> annonces ;
		annonces = annonceRepository.findByUser(utilisateur);
		
		double rating = utilisateur.getRating();
		
		int sizeAnnonces = annonces.size();
				
				for (Annonce annonce : annonces) {
					rating = rating + annonce.getRating();
				}
				
				if(sizeAnnonces != 0.0)
				{
					rating = Math.ceil(((double)rating/(double)sizeAnnonces));
				}
				else
					rating = 0.0;
				
		utilisateur.setRating(rating);
		
		annoncesProfilAnnonceur = annonces;
		
		model.addAttribute("user",utilisateur);
		model.addAttribute("sizeAnnonces", annonces.size());
		
		if(id_connecte==id)
		{
			return "redirect:/profil";
		}
		
		else
			return "profilAnnonceur";
		
		 
	}
	
	@GetMapping(value="/profilAnnonceur")
	public String profilAnnonceur2(Model model, Principal user) {
		Long id_connecte = null;
		
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
			
			id_connecte = utilisateur.getId();
			
			model.addAttribute("prenom", utilisateur.getPrenom());
			model.addAttribute("nom",utilisateur.getNom());
		}
		model.addAttribute("sizeAnnonces", annoncesProfilAnnonceur.size());
		 model.addAttribute("user",userAnnonceGet);
		 Long id = userAnnonceGet.getId();
		 
		 if(id_connecte==id)
			{
				return "profil";
			}
			
			else
				return "profilAnnonceur";
	}
	
	
	@PostMapping(value="/UpdateAnnonce")
	public String UpdateAnnonce(@ModelAttribute("annonce") Annonce annonce , Model model ,Principal user ) {
		
		return annonceService.UpdateAnnonce(annonce , model , user);
		
		 
	}
	
	@PostMapping(value="/modifierAnnonce")
	public String modifierAnnonce(Model model, @RequestParam String idAnnonce , Principal user) {
		

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
			myUser = utilisateur;
			model.addAttribute("prenom", utilisateur.getPrenom());
			model.addAttribute("nom",utilisateur.getNom());
		}
		
		
		model.addAttribute("idAnnonce",idAnnonce);
		
			String url =  annonceService.modiferAnnonce(model);
		
			monAnnonce = (Annonce) model.getAttribute("annonce");
			
			return url;
		 
	}
	

	@GetMapping(value="/modifierAnnonce")
	public String modiferAnnonce(Model model , Principal user) {
		

		if(myUser != null)
		{
			model.addAttribute("nom",myUser.getNom());
			model.addAttribute("prenom",myUser.getPrenom());
		}
		
		
		model.addAttribute("annonce", monAnnonce);
		
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

	@PostMapping(value="/supprimerAnnonce")
	public String supprimerAnnonce(Model model, @RequestParam String idAnnonce , Principal user) {
		

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
		
		
		model.addAttribute("idAnnonce",idAnnonce);
		
			String url =  annonceService.supprimerAnnonce(model,user);
		
			
			return url;
		 
	}
	

	@PostMapping(value="/detailsAnnonce")
	public String detailsAnnonce(Model model, @RequestParam String idAnnonce , Principal user) {
		

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
			
			myUser = utilisateur;
		}
		
		
		
		
		model.addAttribute("idAnnonce",idAnnonce);
		
			String url =  annonceService.detailsAnnonce(model);
		
			monAnnonce = (Annonce) model.getAttribute("annonce");
			
			return url;
		 
	}
	
	@GetMapping(value="/detailsAnnonce")
	public String detailsAnnonce(Model model , Principal user) {
		if(myUser != null)
		{
			model.addAttribute("nom",myUser.getNom());
			model.addAttribute("prenom",myUser.getPrenom());
		}
		
		
		
		model.addAttribute("annonce", monAnnonce);
		
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
	
	
	

	@PostMapping(value="/ratingAnnonce")
	public String ratingAnnonce(Model model, @RequestParam String idAnnonce ,@RequestParam String rating, Principal user) {
		

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
			
			myUser = utilisateur;
		}
		
		
		
		
		model.addAttribute("idAnnonce",idAnnonce);
		double ratingAnnonce = Double.parseDouble(rating);
		model.addAttribute("rating", ratingAnnonce);
		
			String url =  annonceService.ratingAnnonce(model);
		
			
			return url;
		 
	}
	@PostMapping(value="/UpdatePhoto")
	 public String saveUser(Model model, @RequestParam("image") MultipartFile multipartFile ,Principal user) throws IOException {
	
		User utilisateur = null;
		
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
			
			utilisateur = userRepository.findByEmail(username);
			
			model.addAttribute("prenom", utilisateur.getPrenom());
			model.addAttribute("nom",utilisateur.getNom());
			
			myUser = utilisateur;
		}
		
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	       
	      utilisateur.setImage(fileName);
	         
	       userRepository.updatePhoto(utilisateur.getImage(),utilisateur.getId());
	 
	       
	        return "profil";
	    }
	
	@GetMapping(value="/UpdateProfil")
	public String getUpdateProfil()
	{
		return "redirect:/profil";
	}
	
	@PostMapping(value="/UpdateProfil")
	public String UpdateProfil(Model model, Principal user , @ModelAttribute("user")User user_1) {
		

		if(user != null)
		{
			Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String username;
			if(principal instanceof UserDetails)
			{
				  username = ((UserDetails)principal).getUsername();
				
			}
		else {
				 username = principal.toString();
			}
			
			
			User utilisateur = userRepository.findByEmail(username);
			userRepository.updateProfil(user_1.getNom(), user_1.getPrenom(), user_1.getSexe(), user_1.getPassword(), user_1.getEmail(), user_1.getBiographie(), user_1.getId());
			
		}
		
		
		   
			
			return "redirect:/profil?successProfil" ;
		 
	}
	
	@GetMapping(value="/changePassword")
		public String getChangePassword()
		{
		return "redirect:/profil";
		}
	
	 
	  @PostMapping(value="/changePassword")
		public String changePassword(Model model ,Principal user , @RequestParam String newPassword,@RequestParam String oldPassword , @ModelAttribute("user") User user1 ) {

			

			if(user != null)
			{
				Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				String username;
				String Successmessage ;
				String Errormessage ;
				if(principal instanceof UserDetails)
				{
					  username = ((UserDetails)principal).getUsername();
					
				}
				
			else 
			{
					 username = principal.toString();
			}
				
				
				User utilisateur = userRepository.findByEmail(username);
				
				model.addAttribute("prenom", utilisateur.getPrenom());
				model.addAttribute("nom",utilisateur.getNom());
				
				
				String password = utilisateur.getPassword();
				
				
				System.out.println(password);
				
				
				
				
				if(UserServiceImpl.verifyPassword(oldPassword,utilisateur.getHashed() , utilisateur.getSalt())==false)
				{	
					System.out.println(utilisateur.getHashed());
					System.out.println(utilisateur.getSalt());
					Errormessage = "Wrong password ! ";
					
					model.addAttribute("Errormessage", Errormessage);
					return "redirect:/profil?error" ;
				}
				else 
				{
					String salt = UserServiceImpl.generateSalt(512).get();

					String hashed= UserServiceImpl.hashPassword(newPassword, salt).get();
					
					Successmessage = "Password changed ! ";
					String hashedp = passwordEncoder.encode(newPassword);
					userRepository.updatePassword(hashedp , hashed, salt,utilisateur.getId() );
					model.addAttribute("Successmessage",Successmessage);
					return "redirect:/profil?success" ;
				}
				
				
			}
			
			return "redirect:/profil" ;
			   
				
				
			 
		}
	
	 

	
}

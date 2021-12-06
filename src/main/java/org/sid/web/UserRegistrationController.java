package org.sid.web;

import org.sid.metier.UserService;
import org.sid.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;
	
	
	
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto)
	{
		
		if(userRegistrationDto.getSexe().equals("F"))
		{
			System.out.println(userRegistrationDto.getSexe());
			userRegistrationDto.setImage("https://i.ibb.co/jZ3hThm/profil-Femme.png");
		}
		else {
			userRegistrationDto.setImage("https://i.ibb.co/bWbjhnk/profil-Homme.png");
		}
		
		
		
		userService.save(userRegistrationDto);
		
		return "redirect:/registration?success";
	}
	
	
	@GetMapping
	public String showResgistrationForm(Model model)
	{
		model.addAttribute("user",new UserRegistrationDto());
		return "registration";
	}

}

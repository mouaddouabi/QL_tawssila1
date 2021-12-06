package org.sid.web.dto;

public class UserRegistrationDto {

	private String nom;
	private String prenom;
	private String sexe;
	private String password;
	private String email;
	private String age;
	private String image;
	private String salt ; 
	private String hashed ; 


	
	
	public String getHashed() {
		return hashed;
	}
	public void setHashed(String hashed) {
		this.hashed = hashed;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public UserRegistrationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public UserRegistrationDto(String nom, String prenom, String sexe, String password, String email,
			String age , String image , String salt  , String hashed ) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.password = password;
		this.email = email;
		this.age = age;
		this.image = image;
		this.salt=salt ; 
		this.hashed=hashed ; 
		
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	
}

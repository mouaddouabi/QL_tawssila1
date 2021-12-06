package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String sexe;
	private String password;
	private String email;
	private String age;
	private String biographie;
	private String image;
	private double rating;
	@Type(type = "text")
	private String salt ; 
	private String hashed ; 

	

	
	

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	private Collection<Annonce> annonces;
	
	
	
	
	
	public User(Long id, String nom, String prenom, String sexe, String password, String email,
			String age, String biographie, String image, double rating, String salt, String hashed ,  Collection<Annonce> annonces) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.password = password;
		this.email = email;
		this.age = age;
		this.biographie = biographie;
		this.image = image;
		this.rating = rating;
		this.annonces = annonces;
		this.salt=salt ; 
		this.hashed=hashed ;
		
	}

	public User(String nom, String prenom, String sexe, String password, String email, String age,
			String biographie, String image, double rating , String salt  , String hashed ) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.password = password;
		this.email = email;
		this.age = age;
		this.biographie = biographie;
		this.image = image;
		this.rating = rating;
		this.salt=salt ; 
		this.hashed=hashed ; 
		

		
	}



	
	public String getHashed() {
		return hashed;
	}

	public void setHashed(String hashed) {
		this.hashed = hashed;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Annonce> getAnnonces() {
		return annonces;
	}
	public void setAnnonces(Collection<Annonce> annonces) {
		this.annonces = annonces;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getBiographie() {
		return biographie;
	}
	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

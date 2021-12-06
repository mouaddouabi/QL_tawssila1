package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Annonce implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private String telephone;
	private String lieuDepart;
	private String lieuArrive;
	private Long nbPlaces;
	private String vehicule;
	private String date;
	private String prix;
	private String sexe;
	private boolean autoroute;
	private String tailleBagage;
	private boolean fumeur;
	private boolean music;
	private String description;
	private String  commentaire;
	private String codeRating;
	private double rating;
	private boolean AuSeinVille ;
	
	
	@ManyToOne
	private User user;
	
	
	
	
	
	
	public boolean isAuSeinVille() {
		return AuSeinVille;
	}
	public void setAuSeinVille(boolean auSeinVille) {
		AuSeinVille = auSeinVille;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLieuDepart() {
		return lieuDepart;
	}
	public void setLieuDepart(String lieuDepart) {
		this.lieuDepart = lieuDepart;
	}
	public String getLieuArrive() {
		return lieuArrive;
	}
	public void setLieuArrive(String lieuArrive) {
		this.lieuArrive = lieuArrive;
	}
	public Long getNbPlaces() {
		return nbPlaces;
	}
	public void setNbPlaces(Long nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	public String getVehicule() {
		return vehicule;
	}
	public void setVehicule(String vehicule) {
		this.vehicule = vehicule;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public boolean isAutoroute() {
		return autoroute;
	}
	public void setAutoroute(boolean autoroute) {
		this.autoroute = autoroute;
	}
	public String getTailleBagage() {
		return tailleBagage;
	}
	public void setTailleBagage(String tailleBagage) {
		this.tailleBagage = tailleBagage;
	}
	public boolean isFumeur() {
		return fumeur;
	}
	public void setFumeur(boolean fumeur) {
		this.fumeur = fumeur;
	}
	public boolean isMusic() {
		return music;
	}
	public void setMusic(boolean music) {
		this.music = music;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public String getCodeRating() {
		return codeRating;
	}
	public void setCodeRating(String codeRating) {
		this.codeRating = codeRating;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Annonce(String type, String telephone, String lieuDepart, String lieuArrive, Long nbPlaces, String vehicule,
			String date, String prix, String sexe, boolean autoroute, String tailleBagage, boolean fumeur,
			boolean music, String description, String commentaire, String codeRating, double rating,
			boolean auSeinVille, User user) {
		super();
		this.type = type;
		this.telephone = telephone;
		this.lieuDepart = lieuDepart;
		this.lieuArrive = lieuArrive;
		this.nbPlaces = nbPlaces;
		this.vehicule = vehicule;
		this.date = date;
		this.prix = prix;
		this.sexe = sexe;
		this.autoroute = autoroute;
		this.tailleBagage = tailleBagage;
		this.fumeur = fumeur;
		this.music = music;
		this.description = description;
		this.commentaire = commentaire;
		this.codeRating = codeRating;
		this.rating = rating;
		AuSeinVille = auSeinVille;
		this.user = user;
	}
	public Annonce() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
}

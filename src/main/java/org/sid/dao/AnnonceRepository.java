package org.sid.dao;

import java.util.Collection;

import java.util.List;


import org.sid.entities.Annonce;
import org.sid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity ;
import javax.transaction.Transactional;
@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
	
	@Query("SELECT a FROM Annonce a where type = ?1 and au_sein_ville=?2") 
    List<Annonce> findbyType(String type , boolean au_sein_ville);
	
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE  Annonce a  SET a.telephone = :telephone, a.autoroute = :autoroute ,  a.AuSeinVille = :au_sein_ville, a.description = :description , a.date = :date , a.fumeur = :fumeur , a.lieuArrive = :lieu_arrive , a.lieuDepart = :lieu_depart , a.music = :music , a.nbPlaces = :nb_places , a.prix = :prix , a.sexe = :sexe , a.tailleBagage = :taille_bagage  , a.type = :type , a.vehicule = :vehicule WHERE a.id = :id")
	void updateAnnonce( @Param("telephone")String telephone , @Param("autoroute")boolean autoroute  ,
    		@Param("au_sein_ville") boolean au_sein_ville,@Param("description") String description,@Param("date") String date,@Param("fumeur") boolean fumeur,
    		@Param("lieu_arrive") String lieu_arrive,@Param("lieu_depart") String lieu_depart,@Param("music") boolean music,@Param("nb_places") Long nb_places,@Param("prix") String prix,
    		@Param("sexe") String sexe,@Param("taille_bagage") String taille_bagage,@Param("type") String type,@Param("vehicule") String vehicule,@Param("id") Long id );
	
	
	//pour Rating
	

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE  Annonce a  SET a.rating = :rating WHERE a.id = :id")
	void updateAnnonceRating(@Param("rating") double vehicule,@Param("id") Long id );
	
	
	
	
	Collection<Annonce> findByUser(User user);




}

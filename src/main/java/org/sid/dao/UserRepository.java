package org.sid.dao;

import javax.transaction.Transactional;


import org.sid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE  User u  SET u.image = :image WHERE u.id = :id")
	void updatePhoto(@Param("image") String image,@Param("id") Long id );
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE  User u  SET u.nom = :nom  , u.prenom = :prenom ,u.sexe = :sexe,u.password = :password,u.email = :email , u.biographie = :biographie WHERE u.id = :id")
	void updateProfil(@Param("nom") String nom,@Param("prenom") String prenom,@Param("sexe") String sexe,@Param("password") String password,@Param("email") String email, @Param("biographie") String biographie, @Param("id") Long id );
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE  User u  SET u.password = :password , u.hashed = :hashed , u.salt = :salt WHERE u.id = :id")
	void updatePassword(@Param("password") String password, @Param("hashed") String hashed , @Param("salt") String salt, @Param("id") Long id );
	
}

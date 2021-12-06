package org.sid.metier;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.sid.dao.UserRepository;
import org.sid.entities.User;
import org.sid.web.AnnonceController;
import org.sid.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User save(UserRegistrationDto userRegistrationDto) {
		
		String nom = userRegistrationDto.getNom();
		String prenom = userRegistrationDto.getPrenom();
		String sexe = userRegistrationDto.getSexe();
		String password= passwordEncoder.encode(userRegistrationDto.getPassword());
		String salt = generateSalt(512).get();

		String hashed= hashPassword(userRegistrationDto.getPassword(), salt).get();
		
		String email = userRegistrationDto.getEmail();
		String age = userRegistrationDto.getAge();
		String biographie = "";
		String  image = userRegistrationDto.getImage() ; 
		double rating = 0;
		
		User user = new User(nom, prenom, sexe,password, email, age, biographie, image, rating , salt , hashed);	
		
		return userRepository.save(user);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user  = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("username or password invalide");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword() , new ArrayList<>() );
		
	}
	 private static final SecureRandom RAND = new SecureRandom();

	  public static Optional<String> generateSalt (final int length) {

	    if (length < 1) {
	      System.err.println("error in generateSalt: length must be > 0");
	      return Optional.empty();
	    }

	    byte[] salt = new byte[length];
	    RAND.nextBytes(salt);

	    return Optional.of(Base64.getEncoder().encodeToString(salt));
	  }
	  
	  private static final int ITERATIONS = 65536;
	  private static final int KEY_LENGTH = 512;
	  private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

	  public static Optional<String> hashPassword (String password, String salt) {

	    char[] chars = password.toCharArray();
	    byte[] bytes = salt.getBytes();

	    PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

	    Arrays.fill(chars, Character.MIN_VALUE);

	    try {
	      SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
	      byte[] securePassword = fac.generateSecret(spec).getEncoded();
	      return Optional.of(Base64.getEncoder().encodeToString(securePassword));

	    }
	    catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
	      System.err.println("Exception encountered in hashPassword()");
	      return Optional.empty();

	    } 
	    
	    finally {
	      spec.clearPassword();
	    }
	  }
	  public static boolean verifyPassword (String password, String key, String salt) {
		    Optional<String> optEncrypted = hashPassword(password, salt);
		    if (!optEncrypted.isPresent()) return false;
		    return optEncrypted.get().equals(key);
		  }
	
	
}

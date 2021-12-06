package org.sid.metier;

import org.sid.entities.User;
import org.sid.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto userRegistrationDto);
}

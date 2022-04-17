package com.CaridadMichael.WallPaperWorld.config;


import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.CaridadMichael.WallPaperWorld.model.AppUser;
import com.CaridadMichael.WallPaperWorld.repository.UserRepository;




@Service
public class CustomUserDetailsService implements UserDetailsService {
	

    @Autowired
	private  UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		Optional<AppUser> user = userRepo.findByUsername(username);
		AppUser u = user.orElseThrow(()-> new UsernameNotFoundException("Could not find user with username: " + username));
			
		return new User(u.getUsername(),u.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	}

}

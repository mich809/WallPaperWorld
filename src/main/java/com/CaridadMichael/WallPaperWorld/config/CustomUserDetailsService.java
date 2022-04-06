package com.CaridadMichael.WallPaperWorld.config;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.CaridadMichael.WallPaperWorld.model.AppUser;
import com.CaridadMichael.WallPaperWorld.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
	

    @Autowired
	private  UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		Optional<AppUser> user = userRepo.findByUsername(username);
		AppUser u = user.orElseThrow(()-> new UsernameNotFoundException("custom user detail : 	User not found in database"));
		log.info("User {} found in database",username);		
		return new User(u.getUsername(),u.getPassword(), new ArrayList<>());
	}

}

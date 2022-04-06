package com.CaridadMichael.WallPaperWorld.Service;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CaridadMichael.WallPaperWorld.config.CustomUserDetailsService;
import com.CaridadMichael.WallPaperWorld.dto.UserDTO;
import com.CaridadMichael.WallPaperWorld.model.AppUser;
import com.CaridadMichael.WallPaperWorld.repository.UserRepository;
import com.CaridadMichael.WallPaperWorld.utils.AuthenticationResponse;
import com.CaridadMichael.WallPaperWorld.utils.JwtUtil;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService  {

    @Autowired
	private  UserRepository userRepo;	
	@Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
	
	

	
	
		public ResponseEntity<String> RegisterUser(UserDTO user) {				
			if (userRepo.findByUsername(user.getUsername()).isPresent()) {
				log.info("Service : user {} already in database" , user.getUsername());
				return new ResponseEntity<String>("User "+ user.getUsername() + " already Exist",HttpStatus.CONFLICT);
				
			}
			log.info("Service : Saving user {} to database" , user.getUsername());
			AppUser newUser = new AppUser();
			newUser.setUsername(user.getUsername());
			newUser.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepo.save(newUser);
			return  new ResponseEntity<String>("User "+ newUser.getUsername() + " registered", HttpStatus.CREATED);
		}
		
		private AppUser getCurrentUser() {
			String username =  Security
		}
		
		
		
		
		
		
		
		
		
		
		  public AuthenticationResponse createJwtToken(UserDTO user) throws Exception {		       
		        authenticate(user);

		        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
		        String newGeneratedToken = jwtUtil.generateToken(userDetails);
		        log.info("generated token for user {}" , user.getUsername());
		        
		        return new AuthenticationResponse(user.getUsername(),newGeneratedToken);
		    }
		
		
		
		
		private void authenticate(UserDTO user) throws Exception {
		       try {
		            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		        } 
		       catch (BadCredentialsException e) {
		           throw new Exception("INVALID_CREDENTIALS", e);
		        }
		    }
	
	
	

}

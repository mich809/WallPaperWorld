package com.CaridadMichael.WallPaperWorld.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CaridadMichael.WallPaperWorld.config.CustomUserDetailsService;
import com.CaridadMichael.WallPaperWorld.dto.UserDTO;
import com.CaridadMichael.WallPaperWorld.model.AppUser;
import com.CaridadMichael.WallPaperWorld.model.Picture;
import com.CaridadMichael.WallPaperWorld.model.Role;
import com.CaridadMichael.WallPaperWorld.repository.PictureRepository;
import com.CaridadMichael.WallPaperWorld.repository.RoleRepo;
import com.CaridadMichael.WallPaperWorld.repository.UserRepository;
import com.CaridadMichael.WallPaperWorld.utils.AuthenticationResponse;
import com.CaridadMichael.WallPaperWorld.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PictureRepository pictureRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	public ResponseEntity<String> RegisterUser(UserDTO user) {
		if (userRepo.findByUsername(user.getUsername()).isPresent()) {
			return new ResponseEntity<String>("User " + user.getUsername() + " already Exist", HttpStatus.CONFLICT);

		}
		Role role = roleRepo.findById("User").get();
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);
		AppUser newUser = new AppUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setRole(userRoles);
		userRepo.save(newUser);
		return new ResponseEntity<String>("User " + newUser.getUsername() + " registered", HttpStatus.CREATED);
	}

	public AuthenticationResponse createJwtToken(UserDTO user) throws Exception {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					user.getUsername(), user.getPassword());
			authenticationManager.authenticate(authInputToken);

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
			String token = jwtUtil.generateToken(userDetails);
			AppUser u = getUserByUsername(user.getUsername());

			return new AuthenticationResponse(user.getUsername(), token, u.getRole());
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Invalid Login Credentials");
		}

	}

	public void addToFavorites(String username, String pictureName) {
		log.info("about to add {} ", pictureName);
		AppUser appUser = getUserByUsername(username);
		Picture picture = getPictureByName(pictureName);
		picture.setFavorites(picture.getFavorites() + 1);
		appUser.addPicture(picture);
		userRepo.save(appUser);
		pictureRepo.save(picture);

	}

	public void removeFromFavorites(String username, String pictureName) {
		log.info("about to remove {} ", pictureName);
		AppUser appUser = getUserByUsername(username);
		Picture picture = getPictureByName(pictureName);
		picture.setFavorites(picture.getFavorites() - 1);
		appUser.removePicture(picture);
		userRepo.save(appUser);
		pictureRepo.save(picture);
	}

	public Iterable<Picture> getFavorites(String username) {
		return getUserByUsername(username).getFavoritePictures();

	}

	private AppUser getUserByUsername(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find username: " + username));
	}

	private Picture getPictureByName(String pictureName) {
		log.info("inside getPictureByName method , adding picture {} to favorites ", pictureName);
		return pictureRepo.getPictureByName(pictureName)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find username: " + pictureName));

	}

	public UserDTO getUserInfo(String name) {
		UserDTO user = new UserDTO();
		AppUser appUser = getUserByUsername(name);
		user.setPassword("");
		user.setUsername(name);
		user.setFavoritePicsCount(appUser.getFavoritePictures().size());
		user.setUploadedPicsCount(appUser.getPictures().size());
		user.setFavoritePictures(appUser.getFavoritePictures());
		user.setPictures(appUser.getPictures());
		user.setDate(appUser.getDate());

		return user;
	}

}

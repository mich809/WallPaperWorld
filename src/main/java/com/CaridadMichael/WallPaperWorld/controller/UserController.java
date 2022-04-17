package com.CaridadMichael.WallPaperWorld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.CaridadMichael.WallPaperWorld.Service.UserService;
import com.CaridadMichael.WallPaperWorld.dto.UserDTO;
import com.CaridadMichael.WallPaperWorld.model.AppUser;
import com.CaridadMichael.WallPaperWorld.model.Picture;
import com.CaridadMichael.WallPaperWorld.utils.AuthenticationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> saveUser(@RequestBody UserDTO user) {
		return userService.RegisterUser(user);

	}

	@PostMapping("/login")
	public AuthenticationResponse authenticate(@RequestBody UserDTO user) throws Exception {
		return userService.createJwtToken(user);
	}

	@PutMapping("/AddToFavorites")
	public void addToFavorites(@AuthenticationPrincipal(expression = "username") String username,
			@RequestParam long pictureID) {
		userService.addToFavorites(username, pictureID);

	}

	@PutMapping("/removeFromFavorites")
	public void removeFromFavorites(@AuthenticationPrincipal(expression = "username") String username,
			@RequestParam long pictureID) {
		userService.removeFromFavorites(username, pictureID);

	}

	@GetMapping("/getUser")
	public AppUser getUser(@RequestParam String username) {
		return userService.getUserByUsername(username);

	}

	@GetMapping("/getFavorites")
	public @ResponseBody Iterable<Picture> getFavorites(@RequestParam String username) {
		return userService.getFavorites(username);

	}

}

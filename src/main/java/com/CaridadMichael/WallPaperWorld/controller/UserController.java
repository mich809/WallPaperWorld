package com.CaridadMichael.WallPaperWorld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CaridadMichael.WallPaperWorld.Service.UserService;
import com.CaridadMichael.WallPaperWorld.dto.UserDTO;

import com.CaridadMichael.WallPaperWorld.utils.AuthenticationResponse;

import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody UserDTO user) {
	
		return userService.RegisterUser(user);
		
	}
	
	@PostMapping({"/authenticate"})
	public AuthenticationResponse authenticate(@RequestBody UserDTO user) throws Exception {
	       return userService.createJwtToken(user);
	   }
	
}

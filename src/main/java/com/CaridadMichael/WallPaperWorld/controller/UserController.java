package com.CaridadMichael.WallPaperWorld.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CaridadMichael.WallPaperWorld.utils.AuthenticationRequest;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	@GetMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest ) throws Exception{
		return null;
	}
	
}

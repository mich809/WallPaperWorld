package com.CaridadMichael.WallPaperWorld.utils;

import java.util.Set;

import com.CaridadMichael.WallPaperWorld.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
	private final String username;
	private final String jwt;
	private Set<Role> role;

}

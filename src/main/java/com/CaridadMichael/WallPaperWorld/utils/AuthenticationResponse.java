package com.CaridadMichael.WallPaperWorld.utils;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AuthenticationResponse {
	private final String username;
	private final String jwt;



}

package com.CaridadMichael.WallPaperWorld.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pictures")
public class PictureController {
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void uploadPicture(@RequestParam("file") MultipartFile file) {
		
	}

}

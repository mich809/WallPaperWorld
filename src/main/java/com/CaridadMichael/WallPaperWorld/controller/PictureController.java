package com.CaridadMichael.WallPaperWorld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CaridadMichael.WallPaperWorld.Service.PictureService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pictures")
@RequiredArgsConstructor
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	
	@PostMapping("/addPicture")
	@ResponseStatus(HttpStatus.CREATED)
	public void uploadPicture(@RequestParam("file") MultipartFile file) {
		pictureService.uploadPicture(file);
		
	}

}

package com.CaridadMichael.WallPaperWorld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CaridadMichael.WallPaperWorld.Service.PictureService;
import com.CaridadMichael.WallPaperWorld.dto.PictureDTO;
import com.CaridadMichael.WallPaperWorld.dto.uploadPictureResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/picture")
@RequiredArgsConstructor
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	
	@PostMapping("/addPicture")
	@ResponseStatus(HttpStatus.CREATED)
	public uploadPictureResponse uploadPicture(@RequestParam("fileName") String fileName,@RequestParam("file") MultipartFile file) {
		return pictureService.uploadPicture(fileName, file);
		
	}
	
	@PutMapping("/updatePicture")
	@ResponseStatus(HttpStatus.OK)
	public PictureDTO editPicture(@RequestBody 	PictureDTO pictureDto) {
		return pictureService.editPicture(pictureDto);
		
	}
	
	@GetMapping("/getPictureDetails")
	@ResponseStatus(HttpStatus.OK)
	public PictureDTO getPictureDetails(@RequestParam long pictureId) {
		return pictureService.getPictureDetails(pictureId);
	}

}

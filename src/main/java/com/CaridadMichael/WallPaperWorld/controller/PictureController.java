package com.CaridadMichael.WallPaperWorld.controller;



import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CaridadMichael.WallPaperWorld.Service.PictureService;
import com.CaridadMichael.WallPaperWorld.dto.PictureDTO;

import com.CaridadMichael.WallPaperWorld.model.Picture;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/picture")
@RequiredArgsConstructor
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	
	@PostMapping("/addPicture")
	@ResponseStatus(HttpStatus.CREATED)
	public Picture uploadPicture(@RequestParam("picture name") String pictureName,@RequestParam("file") MultipartFile file, @CurrentSecurityContext(expression="authentication.name")String username,@RequestParam("tags") Set<String> pictureTags ) {
		return pictureService.uploadPicture(pictureName, file,username,pictureTags);
		
	}	

	
	@GetMapping("/getPictureDetails")
	@ResponseStatus(HttpStatus.OK)
	public PictureDTO getPictureDetails(@RequestParam long pictureId) {
		return pictureService.getPictureDetails(pictureId);
	}
	
	@GetMapping("/getPictures")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Iterable<Picture> getPictures() {
	   return pictureService.getAllPictures();
	}
	
	@PutMapping("/increaseViewCount")
	@ResponseStatus(HttpStatus.OK)
	public void increaseViewCount (@RequestParam long pictureId) {
	   pictureService.increaseViewCount(pictureId);
	}
	
	@GetMapping("/getPicturesByTag")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Iterable<Picture> getPicturesByTag(@RequestParam Set<String> tags) {
	   return pictureService.searchByTag(tags);
	}
	
	
	
	
	


}

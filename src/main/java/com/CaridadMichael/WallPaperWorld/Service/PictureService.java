package com.CaridadMichael.WallPaperWorld.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CaridadMichael.WallPaperWorld.model.Picture;
import com.CaridadMichael.WallPaperWorld.repository.PictureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PictureService {
	
	private final S3Service s3service;
	private final PictureRepository pictureRepository;
	
	public void uploadPicture(MultipartFile multipartFile) {
		String pictureURL = s3service.uploadFile(multipartFile);
		
		var picture = new Picture();
		picture.setUrl(pictureURL);
		
		pictureRepository.save(picture);
		
	}

}

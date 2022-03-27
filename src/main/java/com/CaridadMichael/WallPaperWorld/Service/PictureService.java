package com.CaridadMichael.WallPaperWorld.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CaridadMichael.WallPaperWorld.dto.PictureDTO;
import com.CaridadMichael.WallPaperWorld.dto.uploadPictureResponse;
import com.CaridadMichael.WallPaperWorld.model.Picture;
import com.CaridadMichael.WallPaperWorld.repository.PictureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PictureService {
	
	private final S3StorageService s3Service;
	private final PictureRepository pictureRepository;
	
	public uploadPictureResponse uploadPicture(String fileName,MultipartFile multipartFile) {
		String pictureURL = s3Service.uploadFile(fileName,multipartFile);
		
		var picture = new Picture();
		picture.setPictureUrl(pictureURL);
		
		var savedPicture = pictureRepository.save(picture);
		return new uploadPictureResponse(savedPicture.getId(),savedPicture.getPictureUrl());
		
	}

	public PictureDTO editPicture(PictureDTO pictureDto) {
		// TODO Auto-generated method stub
		Picture savedPicture = pictureRepository.findById(pictureDto.getId())
		.orElseThrow(()-> new IllegalArgumentException("Cannot find video by id: "+ pictureDto.getId()));
		
		savedPicture.setTags(pictureDto.getTags());
		savedPicture.setFavorites(pictureDto.getFavorites());
		savedPicture.setViewCount(pictureDto.getViewCount());
		pictureRepository.save(savedPicture);
		
		return pictureDto;
		
		
	}
	
	private Picture getPictureByID(long id) {
		return pictureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find video by id: "+ id));
	}

	public PictureDTO getPictureDetails(long id) {
		
		Picture savedPicture = getPictureByID(id);
		
		PictureDTO pictureDTO = new PictureDTO();
		
		pictureDTO.setUrl(savedPicture.getPictureUrl());
		pictureDTO.setTags(savedPicture.getTags());
		pictureDTO.setFavorites(savedPicture.getFavorites());
		pictureDTO.setUserId(savedPicture.getUserId());
		pictureDTO.setViewCount(savedPicture.getViewCount());
		
		return pictureDTO;
		
	}

}

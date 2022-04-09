package com.CaridadMichael.WallPaperWorld.Service;







import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.CaridadMichael.WallPaperWorld.dto.PictureDTO;

import com.CaridadMichael.WallPaperWorld.model.Picture;
import com.CaridadMichael.WallPaperWorld.repository.PictureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PictureService {
	
	private final S3StorageService s3Service;
	private final PictureRepository pictureRepository;
	
	public Picture uploadPicture(String pictureName,MultipartFile multipartFile,String username, Set<String> pictureTags) {
		
		
		var picture = new Picture();
		picture.setPictureUrl(s3Service.uploadPicture(pictureName,multipartFile));
		picture.setPictureName(pictureName);		
		picture.setUploadedBy(username);
		picture.setFavorites(0);
		picture.setViewCount(0);
		
		picture.setTags(pictureTags);
		pictureRepository.save(picture);
		
		
		return picture;
		
	}

//	public PictureDTO editPicture(PictureDTO pictureDto) {
//		// TODO Auto-generated method stub
//		Picture savedPicture = pictureRepository.findById(pictureDto.getId())
//		.orElseThrow(()-> new IllegalArgumentException("Cannot find video by id: "+ pictureDto.getId()));
//		
//		savedPicture.setTags(pictureDto.getTags());
//		savedPicture.setFavorites(pictureDto.getFavorites());
//		savedPicture.setViewCount(pictureDto.getViewCount());
//		pictureRepository.save(savedPicture);
//		
//		return pictureDto;
//		
//		
//	}
	
	private Picture getPictureByID(long id) {
		return pictureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find picture by id: "+ id));
	}

	public PictureDTO getPictureDetails(long id) {		
		Picture savedPicture = getPictureByID(id);		
		PictureDTO pictureDTO = new PictureDTO();		
		pictureDTO.setUrl(savedPicture.getPictureUrl());
		pictureDTO.setTags(savedPicture.getTags());
		pictureDTO.setFavorites(savedPicture.getFavorites());
		pictureDTO.setUploadedBy(savedPicture.getUploadedBy());
		pictureDTO.setViewCount(savedPicture.getViewCount());
		
		return pictureDTO;
		
	}
	
	public Iterable<Picture> getAllPictures(){
		 return pictureRepository.findAll();
	}
	
	public void increaseViewCount(long id) {
		pictureRepository.increaseViewCount(id);;
		
	}
	
	public List<Picture> searchByTag(Set<String> tags){
		return pictureRepository.findByTagsIn(tags);
	}
	
	
	
	
	

}

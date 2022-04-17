package com.CaridadMichael.WallPaperWorld.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.CaridadMichael.WallPaperWorld.dto.PictureDTO;
import com.CaridadMichael.WallPaperWorld.model.AppUser;
import com.CaridadMichael.WallPaperWorld.model.Picture;
import com.CaridadMichael.WallPaperWorld.repository.PictureRepository;
import com.CaridadMichael.WallPaperWorld.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PictureService {
	@Autowired
	private final UserRepository userRepo;

	@Autowired
	private final S3StorageService s3Service;

	@Autowired
	private final PictureRepository pictureRepository;

	public Picture uploadPicture(String pictureName, MultipartFile multipartFile, String username,
			Set<String> pictureTags) {

		AppUser user = getUserByUsername(username);
		var picture = new Picture();
		picture.setPictureUrl(s3Service.uploadPicture(pictureName, multipartFile));
		picture.setPictureName(pictureName);
		picture.setUploadedBy(username);
		picture.setFavorites(0);
		picture.setViewCount(0);
		picture.setTags(pictureTags);

		picture.setUser(user);
		user.uploadedPictures(picture);

		pictureRepository.save(picture);
		return picture;

	}

	private Picture getPictureByID(long id) {
		return pictureRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find picture by id: " + id));
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

	public void increaseViewCount(long id) {
		pictureRepository.increaseViewCount(id);
		;

	}

	public List<Picture> searchByTag(Set<String> tags) {
		return pictureRepository.findByTagsIn(tags);
	}

	public List<Picture> getRandomPictures() {
		return pictureRepository.getRandomPictures();
	}

	private AppUser getUserByUsername(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find username: " + username));
	}

}

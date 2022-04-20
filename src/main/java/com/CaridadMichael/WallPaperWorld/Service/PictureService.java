package com.CaridadMichael.WallPaperWorld.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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

	public Picture uploadPicture(MultipartFile multipartFile, String username, Set<String> pictureTags) {

		String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
		var key = String.format("%s.%s", UUID.randomUUID(), extension);

		AppUser user = getUserByUsername(username);
		var picture = new Picture();
		picture.setPictureUrl(s3Service.uploadPicture(key, multipartFile));
		picture.setPictureName(key.substring(0, 8));
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

	public Page<Picture> searchByTag(Set<String> tags, Pageable pageable) {
		return pictureRepository.findByTagsIn(tags, pageable);
	}

	public Page<Picture> getRandomPictures(Pageable pageable) {
		return pictureRepository.getRandomPictures(pageable);
	}

	private AppUser getUserByUsername(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find username: " + username));
	}

	public Page<Picture> getPicturesByDate(Pageable pageable) {
		return pictureRepository.findAllByOrderByDateDesc(pageable);
	}

	public List<Picture> getFrontPagePictures() {
		return pictureRepository.getFrontPagePictures();
	}

}

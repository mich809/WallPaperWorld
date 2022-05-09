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
	private final PictureRepository pictureRepo;

	public Picture uploadPicture(MultipartFile multipartFile, String username, Set<String> pictureTags) {

		String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
		var key = String.format("%s.%s", UUID.randomUUID(), extension);

		AppUser user = getUserByUsername(username);
		var picture = new Picture();
		picture.setPictureUrl(s3Service.uploadPicture(key, multipartFile));
		picture.setName(key.substring(0, 8));
		picture.setUploadedBy(username);
		picture.setFavorites(0);
		picture.setViewCount(0);
		picture.setTags(pictureTags);
		picture.setUser(user);
		user.uploadedPictures(picture);
		pictureRepo.save(picture);

		return picture;

	}

	public PictureDTO getPictureDetails(String title) {
		increaseViewCount(title);
		Picture picture = pictureRepo.getPictureByName(title)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find picture: " + title));
		PictureDTO pictureDTO = new PictureDTO();
		pictureDTO.setUrl(picture.getPictureUrl());
		pictureDTO.setTags(picture.getTags());
		pictureDTO.setFavorites(picture.getFavorites());
		pictureDTO.setUploadedBy(picture.getUploadedBy());
		pictureDTO.setViewCount(picture.getViewCount());
//		pictureDTO.setFavoritedByUser(true);

		return pictureDTO;
	}

	private void increaseViewCount(String title) {
		pictureRepo.increaseViewCount(title);

	}

	public Page<Picture> searchByTag(Set<String> tags, Pageable pageable) {
		return pictureRepo.findByTagsIn(tags, pageable);
	}

	public Page<Picture> getRandomPictures(Pageable pageable) {
		return pictureRepo.getRandomPictures(pageable);
	}

	private AppUser getUserByUsername(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find username: " + username));
	}

	public Page<Picture> getPicturesByDate(Pageable pageable) {
		return pictureRepo.findAllByOrderByDateDesc(pageable);
	}

	public List<Picture> getFrontPagePictures() {
		return pictureRepo.getFrontPagePictures();
	}

	public Page<Picture> getPicturesByFavorite(Pageable pageable) {
		return pictureRepo.findAllByfavorites(pageable);
	}

}

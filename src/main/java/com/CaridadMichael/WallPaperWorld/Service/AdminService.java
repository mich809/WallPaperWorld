package com.CaridadMichael.WallPaperWorld.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CaridadMichael.WallPaperWorld.model.AppUser;
import com.CaridadMichael.WallPaperWorld.model.Picture;
import com.CaridadMichael.WallPaperWorld.repository.PictureRepository;
import com.CaridadMichael.WallPaperWorld.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
	@Autowired
	private final UserRepository userRepo;

	@Autowired
	private final PictureRepository pictureRepo;

	public void deleteUser(String userName) {
		userRepo.deleteByUsername(userName);

	}

	public void approveImage(String picName) {
		Picture pic = pictureRepo.getPictureByName(picName)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find picture: " + picName));
		pic.setApproved(true);

		pictureRepo.save(pic);
	}

	public void deleteImage(String username, String pictureName) {
		AppUser user = userRepo.findByUsername(username).orElseThrow();
		Picture pic = pictureRepo.getPictureByName(pictureName)
				.orElseThrow(() -> new IllegalArgumentException("Cannot find picture: " + pictureName));

		user.getPictures().remove(pic);
		userRepo.save(user);

	}

	public Page<Picture> getPicturesNotYetApproved(Pageable pageable) {
		return pictureRepo.getPicturesNotApproved(pageable);
	}

}

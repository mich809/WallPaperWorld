package com.CaridadMichael.WallPaperWorld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.CaridadMichael.WallPaperWorld.Service.AdminService;
import com.CaridadMichael.WallPaperWorld.model.Picture;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	private AdminService adminService;

	@DeleteMapping("/deleteUser")
	public void deleteUser(@RequestParam String username) {
		adminService.deleteUser(username);
	}

	@DeleteMapping("/deleteImage")
	public void deleteImage(@RequestParam String pictureName, @RequestParam String username) {
		adminService.deleteImage(username, pictureName);
	}

	@PutMapping("/approveImage")
	public void approveImage(@RequestParam String pictureName) {
		adminService.approveImage(pictureName);

	}

	@GetMapping("/NeedPictureApproval")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Page<Picture> getPicturesNotYetApproved(@RequestParam int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 20);
		return adminService.getPicturesNotYetApproved(pageable);
	}

}

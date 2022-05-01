package com.CaridadMichael.WallPaperWorld.dto;

import java.util.Set;

import com.CaridadMichael.WallPaperWorld.model.Picture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String username;
	private String password;
	private int uploadedPicsCount;
	private int favoritePicsCount;
	private Set<Picture> favoritePictures;
	private Set<Picture> pictures;
}

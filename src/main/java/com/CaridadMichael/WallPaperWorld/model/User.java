package com.CaridadMichael.WallPaperWorld.model;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	private long id;	
	private String username;
	private String password;
	private List<String> pictureHistory;
	private Set<String> likedPictures;
 
}

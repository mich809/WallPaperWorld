package com.CaridadMichael.WallPaperWorld.model;

import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;	
	private String username;
	private String password;
	@ElementCollection(targetClass=String.class)
	private Set<String> likedPictures;
 
}

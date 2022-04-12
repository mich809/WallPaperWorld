package com.CaridadMichael.WallPaperWorld.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;



import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(unique=true)	
	private String username;
	
	private String password;

	@ElementCollection(targetClass=Picture.class)
	private Set<Picture> favoritePictures;
	
	@JsonIgnore
	  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
      cascade = CascadeType.ALL)
	private Set<Picture> pictures;
	
	
	
	
	
	 public void addPicture(Picture picture){
	        favoritePictures.add(picture);
	 }
	 
	 public void removePicture(Picture picture){
	        favoritePictures.remove(picture);
	 }
	

 
}

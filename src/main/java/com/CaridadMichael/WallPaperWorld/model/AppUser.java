package com.CaridadMichael.WallPaperWorld.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "AppUser_fk")
	private Set<Picture> favoritePictures;
 
}

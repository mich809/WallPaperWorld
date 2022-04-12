package com.CaridadMichael.WallPaperWorld.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Picture {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;		
	
	private String pictureName;
	private String uploadedBy;	
	
	private Integer favorites;
	private Integer viewCount;	
	
	@ElementCollection(targetClass=String.class)
	private Set<String> tags;
	
	private String pictureUrl;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private AppUser user;
	

	

}

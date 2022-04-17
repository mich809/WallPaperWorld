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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String pictureName;
	private String uploadedBy;

	private Integer favorites;
	private Integer viewCount;
	private String pictureUrl;

	@ElementCollection(targetClass = String.class)
	private Set<String> tags;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private AppUser user;

}

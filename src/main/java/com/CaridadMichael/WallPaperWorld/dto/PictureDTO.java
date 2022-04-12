package com.CaridadMichael.WallPaperWorld.dto;

import java.util.Set;

import javax.persistence.ElementCollection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureDTO {
	private String pictureName;
	private String url;
	private String uploadedBy;
	private Integer favorites;
	private Integer viewCount;
	@ElementCollection(targetClass=String.class)
	private Set<String> tags;

}

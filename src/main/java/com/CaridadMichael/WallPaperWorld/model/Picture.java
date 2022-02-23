package com.CaridadMichael.WallPaperWorld.model;

import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
	@Id
	private String id;	
	private String userId;
	private Integer favorites;
	private Integer viewCount;
	private Set<String> tags;
	private String url;
	private String category;
	
	

}

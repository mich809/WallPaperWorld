package com.CaridadMichael.WallPaperWorld.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageInfoDTO {
	private int userCount;
	private int viewCount;
	private int pictureCount;
	private int tagCount;

}

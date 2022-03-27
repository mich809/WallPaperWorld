package com.CaridadMichael.WallPaperWorld.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class uploadPictureResponse {
	private long pictureId;
	private String pictureUrl;

}

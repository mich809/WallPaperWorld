package com.CaridadMichael.WallPaperWorld.Service;


import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	String uploadFile(MultipartFile file);

}

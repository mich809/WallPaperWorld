package com.CaridadMichael.WallPaperWorld.Service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService {
	
	private final AmazonS3Client awsS3Client;
	public  final String bucketName = "wallpaperworld";
	
	
	
	@Override
	public String uploadFile(MultipartFile file) {	
		
		var fileNameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		var key = UUID.randomUUID().toString() + fileNameExtension;
		var metadata = new ObjectMetadata();
		
		metadata.setContentLength(file.getSize());
		metadata.setContentType(file.getContentType());
		
		
		try {
			awsS3Client.putObject(bucketName,key,file.getInputStream(),metadata);

		}catch(IOException ioException) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Exception occured while uploading file");
		}
		
		
		awsS3Client.setObjectAcl(bucketName,key,CannedAccessControlList.PublicRead);
		
		return awsS3Client.getResourceUrl(bucketName, key);
		
		
		
		
	}

}

package com.CaridadMichael.WallPaperWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


@SpringBootApplication
@EnableEncryptableProperties
public class WallPaperWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(WallPaperWorldApplication.class, args);
	}
	
	

}

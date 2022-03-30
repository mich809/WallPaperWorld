package com.CaridadMichael.WallPaperWorld.repository;

import org.springframework.data.repository.CrudRepository;

import com.CaridadMichael.WallPaperWorld.model.AppUser;



public interface UserRepository extends CrudRepository<AppUser, Long>{
	
	AppUser findByUsername(String username);

}

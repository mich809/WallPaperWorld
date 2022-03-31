package com.CaridadMichael.WallPaperWorld.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CaridadMichael.WallPaperWorld.model.AppUser;


@Repository
public interface UserRepository extends CrudRepository<AppUser, Long>{
	
	AppUser findByUsername(String username);

}

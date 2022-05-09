package com.CaridadMichael.WallPaperWorld.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CaridadMichael.WallPaperWorld.model.AppUser;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {

	Optional<AppUser> findByUsername(String username);

	void deleteByUsername(String username);

}

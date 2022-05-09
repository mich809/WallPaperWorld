package com.CaridadMichael.WallPaperWorld.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CaridadMichael.WallPaperWorld.model.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, String> {

}
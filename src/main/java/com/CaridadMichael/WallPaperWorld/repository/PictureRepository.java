package com.CaridadMichael.WallPaperWorld.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CaridadMichael.WallPaperWorld.model.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {

}

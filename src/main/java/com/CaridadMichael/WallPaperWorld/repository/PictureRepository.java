package com.CaridadMichael.WallPaperWorld.repository;






import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CaridadMichael.WallPaperWorld.model.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
	
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE Picture  set view_count = view_count + 1 WHERE id = :id")
	void increaseViewCount(@Param("id") Long id );
	
	@Modifying
	@Query(nativeQuery = true, value ="UPDATE Picture  set favorites = favorites + 1 WHERE id = :id")
	void increaseFavoriteCount(@Param("id") Long id );
	
	@Modifying
	@Query(nativeQuery = true, value ="UPDATE Picture  set favorites = favorites - 1 WHERE id = :id")	
	void decreaseFavoriteCount(@Param("id") Long id );
	
	
	List<Picture> findByTagsIn(Set<String> tags);


}

package com.CaridadMichael.WallPaperWorld.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CaridadMichael.WallPaperWorld.model.Picture;

@Repository
public interface PictureRepository extends PagingAndSortingRepository<Picture, Long> {

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE Picture  set view_count = view_count + 1 WHERE id = :id")
	void increaseViewCount(@Param("id") Long id);

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE Picture  set favorites = favorites + 1 WHERE id = :id")
	void increaseFavoriteCount(@Param("id") Long id);

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE Picture  set favorites = favorites - 1 WHERE id = :id")
	void decreaseFavoriteCount(@Param("id") Long id);

	Page<Picture> findByTagsIn(Set<String> tags, Pageable pageable);

	Page<Picture> findAllByOrderByDateDesc(Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM Picture ORDER BY random()")
	Page<Picture> getRandomPictures(Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM Picture Limit 12")
	List<Picture> getFrontPagePictures();
}

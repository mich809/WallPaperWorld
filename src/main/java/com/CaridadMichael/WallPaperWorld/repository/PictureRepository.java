package com.CaridadMichael.WallPaperWorld.repository;

import java.util.List;
import java.util.Optional;
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
	@Query(nativeQuery = true, value = "UPDATE Picture  set view_count = view_count + 1 WHERE name = :pictureName")
	void increaseViewCount(@Param("pictureName") String title);

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE Picture  set favorites = favorites + 1 WHERE name = :pictureName")
	void increaseFavoriteCount(@Param("pictureName") String title);

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE Picture  set favorites = favorites - 1 WHERE name = :pictureName")
	void decreaseFavoriteCount(@Param("pictureName") String title);

	Optional<Picture> getPictureByName(String name);

	Page<Picture> findByTagsIn(@Param("tags") Set<String> tags, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM Picture WHERE approved = true ORDER BY date desc ")
	Page<Picture> findAllByOrderByDateDesc(Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM Picture WHERE approved = true ORDER BY favorites desc")
	Page<Picture> findAllByfavorites(Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM Picture WHERE approved = true ORDER BY random() ")
	Page<Picture> getRandomPictures(Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM Picture WHERE approved = false")
	Page<Picture> getPicturesNotApproved(Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM Picture WHERE approved = true Limit 12")
	List<Picture> getFrontPagePictures();

	@Modifying
	void deleteByName(String name);
}
